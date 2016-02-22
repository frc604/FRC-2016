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

/**
 * The base of robot code.
 */
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
        printBanner();

        modules = new ModuleManager(IndexedTable.getTable("robotnik").getSubTable("modules"), safety);
        systems = new SystemManager();
        modes = new ModeManager();

        if (safety.disabled()) {
            Logger.warn("Exception protection has been disabled. Make sure you know what you're doing!");
        }
    }
    
    /**
     * Adds a module.
     * @param name Name of the module.
     * @param module Module to add.
     */
    protected ModuleReference addModule (String name, Module module) {
        return modules.addModule(name, module);
    }
    
    /**
     * Adds a system.
     * @param system System coordinator to add.
     */
    protected void addSystem (Coordinator system) {
        systems.addSystem(system);
    }
    
    /**
     * Sets the robot's autonomous mode.
     * @param autonomousMode Autonomous mode coordinator to set.
     */
    protected void setAutonomousMode (Coordinator autonomousMode) {
        modes.setAutonomousMode(autonomousMode);
    }
    
    /**
     * Sets the robot's teleop mode.
     * @param autonomousMode Teleop mode coordinator to set.
     */
    protected void setTeleopMode (Coordinator autonomousMode) {
        modes.setTeleopMode(autonomousMode);
    }
    
    @Override
    public void autonomous () {
        modeLoop(GameMode.AUTONOMOUS);
    }
    
    @Override
    public void operatorControl () {
        modeLoop(GameMode.TELEOP);
    }
    
    @Override
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
    
    private void printBanner () {
        System.out.println(
            "\n\n\n" +
            "                       ..            .`\n" +
            "                   .:oyhho.        .ohhy+:\n" +
            "                 +yhhhhhhhhoossssoohhhhhhhhy/\n" +
            "                 ohhhhhhhhhhhhhhhhhhhhhhhhhh+\n" +
            "      :/.        :hhhhhhhhhhhhhhhhhhhhhhhhhh-        ./-\n" +
            "       yhy+-    /hhhhhhhhhhhhhhhhhhhhhhhhhhhy:    -+yhs\n" +
            "       .hhhhho:shhhhhy/.``./yhhhhy/.``./yhhhhho:ohhhhy`\n" +
            "     `.-shhhhhhhhhhhh`      .hhhh`      .hhhhhhhhhhhho-.`\n" +
            "  /shhhhhhhhhhhhhhhhy       `hhhy       `hhhhhhhhhhhhhhhhys/\n" +
            "   ./yhhhhhhhhhhhhhhhs-    -shhhhs-    -shhhhhhhhhhhhhhhy/`\n" +
            "      :hhhhhhhhhhhhhhhhhyyhhhhhhhhhhyyhhhhhhhhhhhhhhhhh:\n" +
            "     /hhhhhhhhhhhhhhhhyyhyhhhhhhhhhhyhyyhhhhhhhhhhhhhhhy:\n" +
            "   -yhhhyysshhh+  ho`  `ho:hhhhhhhh-sh   `sh  +hhhosyyhhhs-\n" +
            " .  ``      shhy- h+   `ho .shhhhs. sh    oh -hhh+      ``.\n" +
            "            :hhhhoh+   `ho   `ys`   sh    ohohhhh.\n" +
            "             +hhhhhs`  `ho    ys    sh   .shhhhh:\n" +
            "              +hhhhhhs/:ho    ys    sh-/shhhhhh/\n" +
            "               :yhhhhhhhhhs+/:yy:/+shhhhhhhhhy-\n" +
            "                `+hhhhhhhhhhhhhhhhhhhhhhhhhy/\n" +
            "                  `:shhhhhhhhyyyhhhhhhhhho-\n" +
            "                     `:+syhhh:  /hhhyo/-\n" +
            "                          `.-`  `..\n" +
            "\n" +
            "                      _           _         _ _\n" +
            "                     | |         | |       (_) |\n" +
            "            _ __ ___ | |__   ___ | |_ _ __  _| | __\n" +
            "           | '__/ _ \\| '_ \\ / _ \\| __| '_ \\| | |/ /\n" +
            "           | | | (_) | |_) | (_) | |_| | | | |   <\n" +
            "           |_|  \\___/|_.__/ \\___/ \\__|_| |_|_|_|\\_\\\n" +
            "           framework\n" +
            "\n\n"
        );
    }
}
