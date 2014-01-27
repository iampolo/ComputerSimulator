package edu.gwu.cs6461.sim.ui;

import java.awt.Panel;

import org.apache.log4j.Logger;


/*
 * 
 *  
 */
public class SimUILauncher {
	private final static Logger logger = Logger.getLogger(SimUILauncher.class);

	
	
	private Panel createControlPanel(){
		
		logger.debug("     .......... test");
		
		
		return new Panel();
	}
	
	
	public static void main(String[] args) {
		new SimUILauncher().createControlPanel();
	}
}
