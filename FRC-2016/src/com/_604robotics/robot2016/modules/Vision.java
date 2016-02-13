package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.utils.*;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import java.lang.Math;

public class Vision extends Module
{
    private boolean ready=false;
    
    NetworkTable GRIPtableH;
    NetworkTable GRIPtableV;

    public Vision()
    {
        GRIPtableH=NetworkTable.getTable("GRIP/HorizontalGoal");
        GRIPtableV=NetworkTable.getTable("GRIP/VerticalGoal");

        this.set(new TriggerMap()
        {{
            add("On Target", new Trigger()
            {
                public boolean run()
                {
                    return ready;
                };
            });
        }});
        
        this.set(new ElasticController()
        {{
            
            addDefault("VisionProcess", new Action()
            {
                double[] prevV_x1=new double[0];
                double[] prevV_x2=new double[0];
                double[] prevH_y1=new double[0];
                double[] prevH_y2=new double[0];
                
                BoolFIFOPopQueue readystack=new BoolFIFOPopQueue(10,0.7);
                
                public void begin(ActionData data)
                {
                    ready=false;
                    readystack.flush();//Make sure that stack starts full of false
                }
                public void run(ActionData data)
                {
                    //Initialize and set processing constants
                    //Also try to make the code less repetitive
                    double distThreshold=35;
                    double botThreshold=60;
                    boolean addToReady=false;

                    //Grab latest data from GRIP
                    double[] GRIPV_x1=GRIPtableV.getNumberArray("x1", new double[0]);
                    double[] GRIPV_x2=GRIPtableV.getNumberArray("x2", new double[0]);
                    double[] GRIPH_y1=GRIPtableH.getNumberArray("y1", new double[0]);
                    double[] GRIPH_y2=GRIPtableH.getNumberArray("y2", new double[0]);

                    if (!((GRIPV_x1==prevV_x1 && GRIPV_x2==prevV_x2
                            && GRIPH_y1==prevH_y1 && GRIPH_y2==prevH_y2)))
                    {
                        //Use ternary arrays to avoid attempting new double[-1]
                        double[] Vx1Diff=new double[GRIPV_x1.length==0?0:GRIPV_x1.length-1];
                        double[] Vx2Diff=new double[GRIPV_x1.length==0?0:GRIPV_x1.length-1];
                        //Ensure that only one goal is in view
                        if (GRIPV_x1.length==4 && GRIPV_x2.length==4 && GRIPH_y1.length==2 && GRIPH_y2.length==2)
                        {
                            for(int i=0; i<Vx1Diff.length; i++)
                            {
                                Vx1Diff[i]=GRIPV_x1[i++]-GRIPV_x1[i];
                            }
                            for(int i=0; i<Vx2Diff.length; i++)
                            {
                                Vx2Diff[i]=GRIPV_x2[i++]-GRIPV_x2[i];
                            }

                            double maxVx1=Double.NEGATIVE_INFINITY;
                            double maxVx2=Double.NEGATIVE_INFINITY;
                            double maxHy1=Double.NEGATIVE_INFINITY;
                            double maxHy2=Double.NEGATIVE_INFINITY;

                            for (double element:Vx1Diff)
                            {
                                if (element>maxVx1)
                                {
                                    maxVx1=element;
                                }
                            }
                            for (double element:Vx1Diff)
                            {
                                if (element>maxVx2)
                                {
                                    maxVx2=element;
                                }
                            }
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

                            if (Math.min(maxVx1,maxVx2)>distThreshold && Math.max(maxHy1,maxHy2)<botThreshold)
                            {
                                addToReady=true;
                            }
                        }
                        //Update the stack
                        readystack.add(addToReady);
                        ready=readystack.passThreshold();
                    }
                    //Make previous ones current
                    prevV_x1=GRIPV_x1;
                    prevV_x2=GRIPV_x2;
                    prevH_y1=GRIPH_y1;
                    prevH_y2=GRIPH_y2;
                };
                public void end(ActionData data)
                {
                    ready=false;
                    readystack.flush();//Flush at end of match
                };
            });
        }});
    }

}
