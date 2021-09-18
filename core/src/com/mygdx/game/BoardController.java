package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class BoardController
{

    Board aBoard;
    int numberOfRows;
    int numberOfColumns;
    float pixelBlockWidth;
    float pixelBlockHeight;
    int numberOfPlayers;
    Player[] aPlayers;
    String[] playerNames = {"Joel", "Chris", "Mary"};


    public BoardController(int numberOfRows, int numberOfColumns, float pixelBlockWidth, float pixelBlockHeight, int numberOfPlayers)
    {
        if (numberOfRows < 10 || numberOfColumns < 10)
        {
            throw new IllegalArgumentException("Number of rows or number of columns must be above 9");
        }
        if (numberOfPlayers < 2)
        {
            throw new IllegalArgumentException("Number of players must be above 1");
        }

        this.aBoard = new Board(numberOfRows, numberOfColumns, pixelBlockWidth, pixelBlockHeight);
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.pixelBlockWidth = pixelBlockWidth;
        this.pixelBlockHeight = pixelBlockHeight;
        this.numberOfPlayers = numberOfPlayers;
        this.aPlayers = new Player[numberOfPlayers];
    }

    /**
     * Creates players using Player objects and stores them in an array
     */
    public void createPlayers()
    {
        //ArrayList<Vector> playerLocations = new ArrayList<>();
        boolean done = false;

        while (!done)
        {
            for (int i = 0; i < aPlayers.length; i++)
            {
                //Next int should start at one
                Random aRan = new Random();
                float x = aRan.nextInt(numberOfColumns) * pixelBlockWidth + pixelBlockWidth;
                float y = aRan.nextInt(numberOfRows) * pixelBlockHeight + pixelBlockHeight;

                Color aColor = new Color((int)x);
                aPlayers[i] = new Player(x, y, aColor, pixelBlockWidth, pixelBlockHeight, playerNames[i]);
            }
            done = true;
        }
    }

    public void createArray()
    {
        //TODO Create way to add Player Pieces

        float y = pixelBlockHeight;
        float x = pixelBlockWidth;
        for (int i = 0; i < aBoard.boardArray.length; i++)
        {
            for (int j = 0; j < aBoard.boardArray[i].length; j++)
            {
                //somePieces[i][j] = new GraphicsTile(x, y, Color.GRAY, blockPixelWidth, blockPixelHeight);
                GraphicsTile aTile = new GraphicsTile(x, y, Color.GRAY, pixelBlockWidth, pixelBlockHeight);
                aBoard.boardArray[i][j] = aTile;
                x += pixelBlockWidth;
            }
            x = pixelBlockWidth;
            y += pixelBlockHeight;
        }
    }

    public void draw(ShapeRenderer renderer)
    {
        for (int i = 0; i < aBoard.boardArray.length; i++)
        {
            for (int j = 0; j < aBoard.boardArray[i].length; j++)
            {
                Block aBlock = aBoard.boardArray[i][j];
                aBlock.draw(renderer);
            }
        }
    }


}