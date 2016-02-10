package com._604robotics.robotnik.meta;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Repackager.
 */
public abstract class Repackager<P, K, V> {
    
    /**
     * Wrap.
     *
     * @param key the key
     * @param value the value
     * @return the object
     */
    protected abstract P wrap (K key, V value);

    /**
     * Compute.
     *
     * @param i the i
     * @return the hashtable
     */
    private Map<K, P> compute (Iterator<Map.Entry<K, V>> i) {
        final Map<K, P> table = new HashMap<K, P>();
        while (i.hasNext()) {
        	Map.Entry<K, V> entry = i.next();
            table.put(entry.getKey(), this.wrap(entry.getKey(), entry.getValue()));
        }
        return table;
    }

    /**
     * Repackage.
     *
     * @param i the i
     * @param r the r
     * @return the hashtable
     */
    public static <P, K, V> Map<K, P> repackage (Iterator<Map.Entry<K, V>> i, Repackager<P, K, V> r) {
        return r.compute(i);
    }
}
