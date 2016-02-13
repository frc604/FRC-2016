package com._604robotics.robotnik;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.coordinator.ModeMap;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.module.ModuleMap;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.logging.TimeSampler;
import com._604robotics.robotnik.Safety;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
    private final IndexedTable table = IndexedTable.getTable("robotnik");
    private final TimeSampler loopTime = new TimeSampler("Loop", 1D);
    
    private ModuleManager moduleManager = new ModuleManager(new ModuleMap(), this.table.getSubTable("modules"));
    private CoordinatorList coordinatorList = new CoordinatorList();
    private ModeMap modeMap = new ModeMap();
    
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
        if (safety.disabled()) {
            RobotProxy.disable();
            
            DataProxy.disable();
            TriggerProxy.disable();
            ActionProxy.disable();

            ConnectorProxy.disable();

            Logger.warn("Exception protection has been disabled. Make sure you know what you're doing!");
        }
    }
    
    /**
     * Sets the module map to use.
     *
     * @param moduleMap the module map
     */
    protected void set (ModuleMap moduleMap) {
        this.moduleManager = new ModuleManager(moduleMap, this.table.getSubTable("modules"));
    }
    
    /**
     * Sets the coordinator list to use.
     *
     * @param coordinatorList the coordinator list
     */
    protected void set (CoordinatorList coordinatorList) {
        this.coordinatorList = coordinatorList;
    }
    
    /**
     * Sets the mode map to use.
     *
     * @param modeMap the mode map
     */
    protected void set (ModeMap modeMap) {
        this.modeMap = modeMap;
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#robotInit()
     */
    public void robotInit () {
        this.coordinatorList.attach(this.moduleManager);
        this.modeMap.attach(this.moduleManager);
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#autonomous()
     */
    public void autonomous () {
        Logger.log(" -- Autonomous mode begin.");
        
        this.loopTime.start();
        RobotProxy.start(moduleManager);
        
        final Coordinator mode = this.modeMap.getAutonomousMode();

        while (this.isEnabled() && this.isAutonomous()) {
            RobotProxy.tick(mode, moduleManager, coordinatorList);
            this.loopTime.sample();
        }
        
        RobotProxy.end(mode, moduleManager);
        this.loopTime.stop();

        Logger.log(" -- Autonomous mode end.");
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#operatorControl()
     */
    public void operatorControl () {
        Logger.log(" -- Teleop mode begin.");
        this.loopTime.start();
        RobotProxy.start(moduleManager);
        
        final Coordinator mode = this.modeMap.getTeleopMode();

        while (this.isEnabled() && this.isOperatorControl()) {
            RobotProxy.tick(mode, moduleManager, coordinatorList);
            this.loopTime.sample();
        }
        
        RobotProxy.end(mode, moduleManager);
        this.loopTime.stop();
        
        Logger.log(" -- Teleop mode end.");
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.SampleRobot#disabled()
     */
    public void disabled () {
        Logger.log(" -- Disabled mode begin.");
        
        while (!this.isEnabled()) RobotProxy.update(moduleManager);
        
        Logger.log(" -- Disabled mode end.");
    }
}
