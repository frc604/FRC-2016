package com._604robotics.robot2016.modules;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements ITableListener {
	
	public void run(){
	NetworkTable table = NetworkTable.getTable("GRIP/HorizontalGoal");
	
	table.addTableListener(this);
	
		try {
			Thread.sleep(10000);
		}catch (InterruptedException changes){
			Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, changes);
		}
	}
	public void ValChanged(ITable itable, String string, Object o, Boolean bl){
		//System.out.println("Values: "+ itable, o , bl);
	}
	@Override
	public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
		// TODO Auto-generated method stub
		
	}
}