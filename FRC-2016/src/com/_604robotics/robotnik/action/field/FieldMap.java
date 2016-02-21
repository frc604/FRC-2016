package com._604robotics.robotnik.action.field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FieldMap implements Iterable<Field> {
    private final List<Field> fields = new ArrayList<Field>();

    public void define (String name, double value) {
        this.fields.add(new Field(name, value));
    }

    public void define (String name, boolean value) {
        this.fields.add(new Field(name, value ? 1 : 0));
    }

    @Override
    public Iterator<Field> iterator () {
        return this.fields.iterator();
    }
}
