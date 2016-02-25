package com._604robotics.utils;

public class ArrayMath
{
    private ArrayMath() {}
    
    public static double ArrayMax(double[] array)
    {
        double max=Double.NEGATIVE_INFINITY;
        for (double element:array)
        {
            if (element>max)
            {
                max=element;
            }
        }
        return max;
    }
}
