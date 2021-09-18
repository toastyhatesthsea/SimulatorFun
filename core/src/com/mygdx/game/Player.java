package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class Player extends Block
{

    float width;
    float height;
    float radius;
    String name;
    Rectangle aRect;

    public Player(float x, float y, Color aColor, float width, float height, String name)
    {
        super(x, y, aColor);
        this.width = width;
        this.height = height;
        this.radius = width / 3;
        this.name = name;
        Rectangle aRect = new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public Rectangle getaRect()
    {
        return aRect;
    }

    public void setaRect(Rectangle aRect)
    {
        this.aRect = aRect;
    }

    @Override
    public void draw(ShapeRenderer aShape)
    {
        aShape.begin(ShapeRenderer.ShapeType.Filled);
        aShape.setColor(color);
        aShape.circle(x, y, radius);
        aShape.end();
        drawText();
    }

    /**
     *
     * Draws a text version of a player and returns the first letter in uppercase.
     * Ex. "Paul" -> "P"
     * @return String, first letter capatalized
     */
    public String drawText()
    {
        return name.substring(0, 1).toUpperCase();
    }
}
