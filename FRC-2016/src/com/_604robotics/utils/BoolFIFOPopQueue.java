package com._604robotics.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class BoolFIFOPopQueue
{
    private Vector content;
    public final int maxsize;
    public final double fraction;

    public BoolFIFOPopQueue(int maxsize, double fraction)
    {
        this.content=new Vector(maxsize);
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
        return content.size()==0;
    }
    
    public boolean isFull()
    {
        return content.size()==size();
    }
    
    public void remove()
    {
        // TODO Auto-generated method stub
        this.content.remove(content.size()-1);
    }
    
    public void removeAll()
    {
        // TODO Auto-generated method stub
        this.content.removeAllElements();
    }
    
    public void clear()
    {
        this.removeAll();
    }

    public void add(boolean element)
    {
        // TODO Auto-generated method stub
        boolean[] elarray={element};
        if (this.size()<=this.maxsize)
        {
            this.content.add(0,element);
        }
        else
        {
            this.remove();
            this.content.add(0,element);
        }
    }
    
    public boolean passThreshold()
    {
        int count=0;
        Iterator countloop=this.content.iterator();
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
