package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GraphicsTile extends Block
{

    float width;
    float height;
    Rectangle rect;

    public GraphicsTile(float x, float y, Color aColor)
    {
        super(x, y, aColor);
    }

    public GraphicsTile(float x, float y, Color aColor, float width, float height)
    {
        super(x, y, aColor);
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, y);

    }

    @Override
    public void draw(ShapeRenderer aShape)
    {
        aShape.begin(ShapeRenderer.ShapeType.Line);
        aShape.setColor(color);
        aShape.rect(x, y, width, height);
        aShape.end();
    }

    public String drawText()
    {
        return "x";
    }
}
