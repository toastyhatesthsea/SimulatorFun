package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Block
{

    float x, y;
    Color color;

    public Block(float x, float y, Color aColor)
    {
        this.x = x;
        this.y = y;
        this.color = aColor;
    }


    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public float getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public abstract void draw(ShapeRenderer aShape);

}
