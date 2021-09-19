package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Block
{

    int xArrayLocation, yArrayLocation;
    Color color;

    public Block(int xArrayLocation, int yArrayLocation, Color aColor)
    {
        this.xArrayLocation = xArrayLocation;
        this.yArrayLocation = yArrayLocation;
        this.color = aColor;
    }

    public int getxArrayLocation()
    {
        return xArrayLocation;
    }

    public void setxArrayLocation(int xArrayLocation)
    {
        this.xArrayLocation = xArrayLocation;
    }

    public int getyArrayLocation()
    {
        return yArrayLocation;
    }

    public void setyArrayLocation(int yArrayLocation)
    {
        this.yArrayLocation = yArrayLocation;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }


    public abstract void draw(ShapeRenderer aShape);

}
