package com._604robotics.robotnik.data.sources;

import com._604robotics.robotnik.data.Data;

import java.util.StringTokenizer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

// TODO: Auto-generated Javadoc
/**
 * The Class NetworkData.
 */
public class NetworkDataArray extends Data {
    
    /** The table. */
    private final ITable table;
    
    /** The key. */
    private final String key;
    
    /** The default value. */
    private final double defaultValue;
    
    /** The index. */
    private int index;
    
    /**
     * Instantiates a new network data.
     *
     * @param namespace the namespace
     * @param key the key
     * @param defaultValue the default value
     * @param index the index
     */
    public NetworkDataArray (String namespace, String key, double defaultValue, int index) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.index = index;

        final StringTokenizer tokens = new StringTokenizer(namespace, ".");
        
        ITable workingTable = NetworkTable.getTable(tokens.nextToken());
        while (tokens.hasMoreTokens()) workingTable = workingTable.getSubTable(tokens.nextToken());
        
        this.table = workingTable;
    }
    
    /**
     * Gets the current index.
     * @return int index the index
     */
    public int getindex()
    {
        return this.index;
    }

    /**
     * Sets a new index.
     * @param newindex the new index
     */
    public void setindex(int newindex)
    {
        this.index=newindex;
    }
    /**
     * Returns the length of the array
     * @return int length the length
     */
    public int getlength()
    {
        return this.table.getNumberArray(this.key, new double[0]).length;
    }
    /**
     * Returns the entire array
     * @return double[] array the array
     */
    public double[] getwhole()
    {
        return this.table.getNumberArray(this.key, new double[0]);
    }
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.Data#run()
     */
    /**
     * Sets the index and retrieves the element at that index
     * @return double element the element
     */
    public double getatindex(int newindex)
    {
        this.setindex(newindex);
        return this.run();
    }
    /**
     * Returns an element from the array
     * @return double element the element
     */
    public double run () {//Make currentArray and currentindex, make currentindex -1 for new array
        double[] returnTryToEliminate=this.table.getNumberArray(this.key, new double[0]);
        return returnTryToEliminate[this.index];
    }
}