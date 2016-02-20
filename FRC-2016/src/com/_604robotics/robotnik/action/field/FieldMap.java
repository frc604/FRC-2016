package com._604robotics.robotnik.action.field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class FieldMap.
 */
public class FieldMap implements Iterable<Field> {
    
    /** The fields. */
    private final List<Field> fields = new ArrayList<Field>();
    
    /**
     * Define.
     *
     * @param name the name
     * @param value the value
     */
    public void define (String name, double value) {
        this.fields.add(new Field(name, value));
    }
    
    /**
     * Define.
     *
     * @param name the name
     * @param value the value
     */
    public void define (String name, boolean value) {
        this.fields.add(new Field(name, value ? 1D : 0D));
    }
    
    /**
     * Iterate.
     *
     * @return the iterator
     */
    public Iterator<Field> iterator () {
        return this.fields.iterator();
    }
}
