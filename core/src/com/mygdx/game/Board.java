package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.function.Function;

public class Board
{
    float blockPixelWidth;
    float blockPixelHeight;
    int numberOfColumns;
    int numberOfRows;
    Block[][] blocks;

    public Board(int numberOfRows, int numberOfColumns, float blockPixelWidth, float blockPixelHeight)
    {
        this.blockPixelWidth = blockPixelWidth;
        this.blockPixelHeight = blockPixelHeight;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        blocks = new Block[numberOfRows][numberOfColumns];
        this.createArray();
    }

    public void createArray()
    {
        float y = blockPixelHeight;
        float x = blockPixelWidth;
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks[i].length; j++)
            {
                blocks[i][j] = new GraphicsTile(x, y, Color.GRAY, blockPixelWidth, blockPixelHeight);
                x += blockPixelWidth;
            }
            x = blockPixelWidth;
            y += blockPixelHeight;
        }
    }

    public void draw(ShapeRenderer renderer)
    {
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks[i].length; j++)
            {
                Block aBlock = blocks[i][j];
                aBlock.draw(renderer);
            }
        }
    }

    public void processArray(Function aFunc)
    {

    }

    public abstract interface process
    {
        Block create(int i, int b);
    }

    public interface drawer
    {
        void Draw(Block aBlock);
    }


}
