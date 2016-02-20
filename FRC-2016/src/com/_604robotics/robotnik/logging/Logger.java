package com._604robotics.robotnik.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Logger.
 */
public class Logger {
	
	/** The Constant logDateFormat */
    private static final SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * Log.
     *
     * @param message the message
     */
    public static void log (String message) {
        record(System.out, "[INFO] " + message);
    }
    
    /**
     * Warn.
     *
     * @param message the message
     */
    public static void warn (String message) {
        record(System.err, "[WARN] " + message);
        trace(new Exception());
    }
    
    /**
     * Error.
     *
     * @param message the message
     * @param ex the ex
     */
    public static void error (String message, Exception ex) {
        record(System.err, "[ERROR] " + message + ": (" + ex.getClass().getName() + ") " + ex.getMessage());
        trace(ex);
    }
    
    /**
     * Record.
     *
     * @param std the std
     * @param message the message
     */
    private static void record (PrintStream std, String message) {
        final String line = "[" + logDateFormat.format(new Date()) + "] " + message;
        
        std.println(line);
        if (logFile != null) logFile.println(line);
    }
    
    /**
     * Trace.
     *
     * @param ex the ex
     */
    private static void trace (Exception ex) {
        ex.printStackTrace();
        if (logFile != null) logFile.println(ex.toString());
    }
    
    /** The Constant logFile. */
    private static PrintStream logFile = null;
    
    /** The Constant logFileDateFormat */
    private static final SimpleDateFormat logFileDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    
    static {
    	try {
    		String fileName = "robotnik_" + logFileDateFormat.format(new Date()) + ".log";
        	File file = new File(fileName);
        	if(!file.exists() && !file.createNewFile()) {
        		warn("Could not create log file.");
        	}
    		
    		logFile = new PrintStream(new FileOutputStream(file));
            log("Recording to log file \"" + fileName + "\" on cRIO");
    	} catch(IOException e) {
    		error("Could not open log file.", e);
    	}
    }
}
