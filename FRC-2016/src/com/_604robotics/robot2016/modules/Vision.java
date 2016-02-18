package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.utils.*;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.Timer;

import java.lang.Math;

public class Vision extends Module
{
    private boolean ready=false;
    BoolFIFOPopQueue readystack=new BoolFIFOPopQueue(10,0.7);
    private boolean inview=false;
    
    NetworkTable GRIPtableH;
    NetworkTable GRIPtableV;
    NetworkTable GRIPrun;

    public Vision()
    {
        GRIPtableH=NetworkTable.getTable("GRIP/HorizontalGoal");
        GRIPtableV=NetworkTable.getTable("GRIP/VerticalGoal");
        GRIPrun=NetworkTable.getTable("GRIP");

        this.set(new TriggerMap()
        {{
            add("On Target", new Trigger()
            {
                public boolean run()
                {
                    return ready;
                };
            });
            add("In view", new Trigger()
            {
                public boolean run()
                {
                    return inview;
                }
            });
        }});
        
        this.set(new DataMap()
        {{
            add("StackFraction", new Data() {
                public double run () {
                    return readystack.currentFraction();
                }
            });
        }});
        
        this.set(new ElasticController()
        {{
            
            addDefault("VisionProcess", new Action(new FieldMap(){{
                define("Charged",false);
            }})
            {
                /*double[] prevV_x1=new double[0];
                double[] prevV_x2=new double[0];
                double[] prevH_y1=new double[0];
                double[] prevH_y2=new double[0];*/
                boolean wasCharged=false;
                boolean isCharged=false;
                
                public void begin(ActionData data)
                {
                    ready=false;
                    inview=false;
                    readystack.flush();//Make sure that stack starts full of false
                }
                public void run(ActionData data)
                {
                    //Initialize and set processing constants
                    /**min distance between two goals*/
                    double distThreshold=35;
                    /**Min height of goal, in pixels from top*/
                    double botThreshold=60;
                    /**Center must be in certain area*/
                    double leftMid=160-110;
                    double rightMid=210-160;
                    boolean addToReady=false;
                    
                    isCharged=data.is("Charged");

                    //Grab latest data from GRIP
                    double[] GRIPV_x1=GRIPtableV.getNumberArray("x1", new double[0]);
                    double[] GRIPV_x2=GRIPtableV.getNumberArray("x2", new double[0]);
                    double[] GRIPH_y1=GRIPtableH.getNumberArray("y1", new double[0]);
                    double[] GRIPH_y2=GRIPtableH.getNumberArray("y2", new double[0]);
                    //boolean blobCheck=GRIPrun.getBoolean("run",false);
                    //Make sure that new data has come in
                            //Ensure that only one goal is in view
                            if (GRIPV_x1.length==4 && GRIPV_x2.length==4 && 
                                    GRIPH_y1.length==2 && GRIPH_y2.length==2)
                            {
                                double maxHy1=Double.NEGATIVE_INFINITY;
                                double maxHy2=Double.NEGATIVE_INFINITY;
                                inview=true;

                                for (double element:GRIPH_y1)
                                {
                                    if (element>maxHy1)
                                    {
                                        maxHy1=element;
                                    }
                                }
                                for (double element:GRIPH_y2)
                                {
                                    if (element>maxHy2)
                                    {
                                        maxHy2=element;
                                    }
                                }
                                //Flush queue if just shot
                                if (wasCharged && !(isCharged))
                                {
                                    readystack.flush();
                                }
                                else
                                {
                                    /*Min distance between lines
                                     * Since the same elements are the max,
                                     * No need to calculate diffs for these
                                     */
                                    double x1Width = GRIPV_x1[2]-GRIPV_x1[1];
                                    double x2Width = GRIPV_x2[2]-GRIPV_x2[1];
                                    double x1Mid=(GRIPV_x1[2]+GRIPV_x1[1])/2;
                                    double x2Mid=(GRIPV_x2[2]+GRIPV_x2[1])/2;

                                    if (Math.min(x1Width, x2Width)>distThreshold)
                                    {
                                        if (Math.max(maxHy1,maxHy2)<botThreshold)
                                        {
                                            if (leftMid<x1Mid && x1Mid<rightMid &&
                                                    leftMid<x2Mid && x2Mid<rightMid)
                                            {
                                                addToReady=true;
                                            }
                                        }
                                    }
                                }
                            }
                            else
                            {
                                inview=false;
                            }
                            /*Update the stack
                             * If the stack was just flushed, addToReady would be false
                             * This would not introduce a true element there
                             */
                            readystack.add(addToReady);
                            ready=readystack.passThreshold();
                        
                    
                    //Make previous ones current
                    /*prevV_x1=GRIPV_x1;
                    prevV_x2=GRIPV_x2;
                    prevH_y1=GRIPH_y1;
                    prevH_y2=GRIPH_y2;*/
                    wasCharged=isCharged;
                };
                public void end(ActionData data)
                {
                    ready=false;
                    inview=false;
                    readystack.flush();//Flush at end of match
                };
            });
            add("Timer", new Action() {
                public void begin (ActionData data) {
                    readystack.flush();
                }
            });
        }});
    }

}
