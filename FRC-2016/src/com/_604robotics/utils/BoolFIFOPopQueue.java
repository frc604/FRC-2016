package com._604robotics.utils;

import java.util.ArrayDeque;
import java.util.Iterator;

public class BoolFIFOPopQueue
{
    private ArrayDeque<Boolean> content;
    public final int maxsize;
    public final double fraction;

    public BoolFIFOPopQueue(int maxsize, double fraction)
    {
        this.content=new ArrayDeque<Boolean>(maxsize);
        this.maxsize=maxsize;
        this.fraction=fraction;
    }
    public int size()
    {
        // TODO Auto-generated method stub
        return content.size();
    }

    public boolean isEmpty()
    {
        // TODO Auto-generated method stub
        return content.isEmpty();
    }
    
    public boolean isFull()
    {
        return content.size()==this.maxsize;
    }
    
    public void remove()
    {
        // TODO Auto-generated method stub
        content.removeLast();
    }

    public void add(boolean element)
    {
        // TODO Auto-generated method stub
        if (this.size()<=this.maxsize)
        {
            content.addFirst(element);
        }
        else
        {
            remove();
            content.addFirst(element);
        }
    }
    
    public boolean passThreshold()
    {
        int count=0;
        Iterator<Boolean> countloop=this.content.iterator();
        while(countloop.hasNext())
        {
            if ((boolean) countloop.next())
            {
                count++;
            }
        }
        return (double)count/(double)size()>=this.fraction;
    }
    
    public Object[] toArray()
    {
        // TODO Auto-generated method stub
        return this.content.toArray();
    }

}
