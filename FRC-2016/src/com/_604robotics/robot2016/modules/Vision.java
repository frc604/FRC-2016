package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;

import com._604robotics.utils.ArrayMath;
import com._604robotics.utils.BoolFIFOPopQueue;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class Vision extends Module
{
    NetworkTable GRIPtableH=NetworkTable.getTable("GRIP/HorizontalGoal");
    NetworkTable GRIPtableV=NetworkTable.getTable("GRIP/VerticalGoal");
    NetworkTable GRIPrun=NetworkTable.getTable("GRIP");
    
    private class GRIPupdate implements ITableListener
    {
        private boolean run;

        public GRIPupdate()
        {
            this.setRun(false);
        }
        
        @Override
        public void valueChanged(ITable source, String key, Object value,
                boolean isNew)
        {
            if (source==GRIPrun)
            {
                this.setRun(true);
            }
            try
            {
                Thread.sleep(10);
                this.setRun(false);
            }
            catch(Exception e)
            {
                this.setRun(false);
            }
        }

        public boolean getRun()
        {
            return run;
        }

        public void setRun(boolean run)
        {
            this.run = run;
        }
        
    }
    private boolean ready=false;
    private boolean inview=false;
    
    GRIPupdate runAction=new GRIPupdate();

    public Vision()
    {
        Timer shootTimer=new Timer();
        GRIPrun.addTableListenerEx(runAction, NetworkTable.NOTIFY_UPDATE);
        BoolFIFOPopQueue readystack=new BoolFIFOPopQueue(10,0.7);

        this.set(new TriggerMap()
        {{
            add("On Target", () -> ready);
            add("In View", () -> inview);
        }});
        
        this.set(new ElasticController()
        {{
            addDefault("VisionProcess", new Action(new FieldMap(){{
                define("Charged", false);
                define("Vision Timer", 0);
            }})
            {
                boolean wasCharged=false;
                boolean isCharged=false;
                
                public void begin(ActionData data)
                {
                    ready=false;
                    inview=false;
                    readystack.flush();//Make sure that stack starts full of false
                    shootTimer.reset();
                    GRIPrun.putBoolean("run", true);
                }
                public void run(ActionData data)
                {
                    if (runAction.getRun())
                    {
                        boolean addToReady=false;

                        isCharged=data.is("Charged");

                        //Grab latest data from GRIP
                        double[] GRIPV_x1=GRIPtableV.getNumberArray("x1", new double[0]);
                        double[] GRIPV_x2=GRIPtableV.getNumberArray("x2", new double[0]);
                        double[] GRIPH_y1=GRIPtableH.getNumberArray("y1", new double[0]);
                        double[] GRIPH_y2=GRIPtableH.getNumberArray("y2", new double[0]);
                        //Make sure that new data has come in
                        if (shootTimer.get()>data.get("Vision Timer"))
                        {
                            //Ensure that only one goal is in view
                            if (GRIPV_x1.length==4 && GRIPV_x2.length==4 && 
                                    GRIPH_y1.length==2 && GRIPH_y2.length==2)
                            {
                                inview=true;

                                //Flush queue if just shot
                                if (wasCharged && !(isCharged))
                                {
                                    readystack.flush();
                                    shootTimer.reset();
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

                                    double maxHy1=ArrayMath.ArrayMax(GRIPH_y1);
                                    double maxHy2=ArrayMath.ArrayMax(GRIPH_y2);

                                    if (Math.min(x1Width, x2Width)>Calibration.VISION_DIST)
                                    {
                                        if (Math.max(maxHy1,maxHy2)<Calibration.VISION_BOTTOM)
                                        {
                                            if (Calibration.VISION_LEFTMID<x1Mid && x1Mid<Calibration.VISION_RIGHTMID &&
                                                    Calibration.VISION_LEFTMID<x2Mid && x2Mid<Calibration.VISION_RIGHTMID)
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
                        }
                        //Make previous ones current
                        wasCharged=isCharged;
                        runAction.setRun(false);
                    }
                };
                public void end(ActionData data)
                {
                    ready=false;
                    inview=false;
                    readystack.flush();//Flush at end of match
                    shootTimer.reset();
                    shootTimer.stop();
                    GRIPrun.putBoolean("run", false);
                };
            });
            add("FlushReset", new Action()
            {
                public void begin(ActionData ddata)
                {
                    readystack.flush();
                    shootTimer.reset();
                }
            });
        }});
    }

}
