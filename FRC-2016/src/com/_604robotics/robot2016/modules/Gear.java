package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;

public class Gear extends Module {
    private double multiplier = 1;
    private double gear = 1;
    private double maxGear = 6;

    public Gear () {
        this.set(new DataMap() {{
            add("Current Multiplier", () -> Math.pow(multiplier, maxGear - gear));
            add("Base Multiplier", () -> multiplier);
            add("Gear", () -> gear);
        }});

        this.set(new ElasticController() {{
            addDefault("Not Shifting", new Action(new FieldMap () {{
                define("Multiplier", 1D);
            }}) {
                public void run (ActionData data) {
                    multiplier = data.get("Multiplier");
                }
            });

            add("Upshift", new Action() {
                public void begin (ActionData data) {
                    if(gear < maxGear) {
                        ++gear;
                    }
                }
            });

            add("Downshift", new Action() {
                public void begin (ActionData data) {
                    if(gear > 1) {
                        --gear;
                    }
                }
            });
        }});
    }
}
