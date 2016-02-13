package com._604robotics.robotnik.action.field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FieldMap {
    private final List<Field> fields = new ArrayList<>();
    
    public void define (String name, double value) {
        fields.add(new Field(name, value));
    }

    public void define (String name, boolean value) {
        fields.add(new Field(name, value ? 1D : 0D));
    }
    
    public Stream<Field> stream () {
        return fields.stream();
    }
}
