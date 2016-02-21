package com._604robotics.robotnik;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.ModeManager;
import com._604robotics.robotnik.coordinator.SystemManager;
import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.logging.TimeSampler;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.module.ModuleReference;

import edu.wpi.first.wpilibj.SampleRobot;

public abstract class Robot extends SampleRobot {
    private final TimeSampler loopTime = new TimeSampler("Loop", 1D);
    
    private final ModuleManager modules;
    private final SystemManager systems;
    private final ModeManager modes;
    
    /**
     * Instantiates a new robot (with exception protection enabled by default).
     */
    public Robot () {
        this(Safety.ENABLED);
    }
    
    /**
     * Instantiates a new robot.
     *
     * @param safety Whether or not exception protection is enabled. Only
     *               disable this if you know what you're doing! And don't run
     *               with safety disabled in competition.
     */
    public Robot (Safety safety) {
        modules = new ModuleManager(IndexedTable.getTable("robotnik").getSubTable("modules"), safety);
        systems = new SystemManager();
        modes = new ModeManager();

        if (safety.disabled()) {
            Logger.warn("Exception protection has been disabled. Make sure you know what you're doing!");
        }
    }
    
    protected ModuleReference addModule (String name, Module module) {
        return modules.addModule(name, module);
    }
    
    protected void addSystem (Coordinator system) {
        systems.addSystem(system);
    }
    
    protected void setAutonomousMode (Coordinator autonomousMode) {
        modes.setAutonomousMode(autonomousMode);
    }
    
    protected void setTeleopMode (Coordinator autonomousMode) {
        modes.setTeleopMode(autonomousMode);
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#autonomous()
     */
    public void autonomous () {
        modeLoop(GameMode.AUTONOMOUS);
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#operatorControl()
     */
    public void operatorControl () {
        modeLoop(GameMode.TELEOP);
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#disabled()
     */
    public void disabled () {
        modeLoop(GameMode.DISABLED);
    }
    
    private void modeLoop (GameMode gameMode) {
        Logger.log(" -- " + gameMode.prettyName() + " mode begin.");

        if (gameMode != GameMode.DISABLED) {
            modules.start();

            loopTime.start();
        }

        while (gameMode.active()) {
            modules.update();
            systems.update();

            if (gameMode != GameMode.DISABLED) {
                modes.update(gameMode);
                modules.execute();

                loopTime.sample();
            }
        }

        if (gameMode != GameMode.DISABLED) {
            loopTime.stop();

            modules.stop();
            modes.stop(gameMode);
        }

        Logger.log(" -- " + gameMode.prettyName() + " mode end.");
    }
}
