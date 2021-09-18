package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.function.Function;

public class Board
{
    float blockPixelWidth;
    float blockPixelHeight;
    int numberOfColumns;
    int numberOfRows;

    Block[][] boardArray;

    public Board(int numberOfRows, int numberOfColumns, float blockPixelWidth, float blockPixelHeight)
    {
        this.blockPixelWidth = blockPixelWidth;
        this.blockPixelHeight = blockPixelHeight;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        boardArray = new Block[numberOfRows][numberOfColumns];
        //this.createArray();
    }

    public void draw(ShapeRenderer renderer)
    {
        for (int i = 0; i < boardArray.length; i++)
        {
            for (int j = 0; j < boardArray[i].length; j++)
            {
                //Block aBlock = somePieces[i][j];
                //aBlock.draw(renderer);
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
