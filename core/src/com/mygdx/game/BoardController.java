package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class BoardController
{

    Board tileBoard;
    Board playerBoard;
    int numberOfRows;
    int numberOfColumns;
    float pixelBlockWidth;
    float pixelBlockHeight;
    int numberOfPlayers;
    Player[] aPlayers;
    String[] playerNames = {"Joel", "Chris", "Mary", "Lyra"};
    int totalPlayerMovements;

    float playerUpdateTime;
    float playerMovementTimer;

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

        this.tileBoard = new Board(numberOfRows, numberOfColumns, pixelBlockWidth, pixelBlockHeight);
        this.playerBoard = new Board(numberOfRows, numberOfColumns, pixelBlockWidth, pixelBlockHeight);
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.pixelBlockWidth = pixelBlockWidth;
        this.pixelBlockHeight = pixelBlockHeight;
        this.numberOfPlayers = numberOfPlayers;
        this.aPlayers = new Player[numberOfPlayers];
        this.totalPlayerMovements = 0;
        this.playerUpdateTime = 0; //Will update player movement every 3 seconds of game delta time
        this.playerMovementTimer = 0;
    }

    /**
     * Creates players using Player objects and stores them in an array
     */
    public void createPlayers()
    {
        boolean done = false;

        while (!done)
        {
            for (int i = 0; i < aPlayers.length; i++)
            {
                //TODO Use X and Y values before multiplied to add to Board array
                //TODO Make sure to change Board structure to use Pieces
                Random aRan = new Random();
                int xArrayLocation = aRan.nextInt(numberOfColumns);
                float xDrawLocation = xArrayLocation * pixelBlockWidth + pixelBlockWidth; //Default draw location will be slightly away from the edge, it's why pixelBlockwidth is added afterwards

                int yArrayLocation = aRan.nextInt(numberOfRows);
                float yDrawLocation = yArrayLocation * pixelBlockHeight + pixelBlockHeight;

                //Color aColor = new Color((int) x);
                aPlayers[i] = new Player(xArrayLocation, yArrayLocation, Color.FIREBRICK, pixelBlockWidth, pixelBlockHeight, playerNames[i]);
            }
            done = true;
        }
    }

    /**
     * Creates an array of GraphicsTiles objects. Using the i and j values in the loops for x/y tile location
     */
    public void createArray()
    {
        for (int i = 0; i < tileBoard.boardArray.length; i++)
        {
            for (int j = 0; j < tileBoard.boardArray[i].length; j++)
            {
                GraphicsTile aTile = new GraphicsTile(i, j, Color.GRAY, pixelBlockWidth, pixelBlockHeight);
                tileBoard.boardArray[i][j] = aTile;
            }
        }
    }

    public void drawBoard(ShapeRenderer renderer)
    {
        for (int i = 0; i < tileBoard.boardArray.length; i++)
        {
            for (int j = 0; j < tileBoard.boardArray[i].length; j++)
            {
                Block aBlock = tileBoard.boardArray[i][j];
                aBlock.draw(renderer);
            }
        }
    }

    public void drawPlayers(ShapeRenderer renderer)
    {
        for (Player somePlayer : aPlayers)
        {
            somePlayer.draw(renderer);
        }
    }

    public void updatePlayers()
    {
        playerMovementTimer += Gdx.graphics.getDeltaTime();

        if (playerMovementTimer >= playerUpdateTime) //Updates movement every 3 seconds
        {
            //TODO Update player movement
            for (Player somePlayer : aPlayers)
            {
                somePlayer.playerMovement(numberOfRows, numberOfColumns);
            }
            totalPlayerMovements++;
            playerMovementTimer = 0;
        }
    }
    /*
    public boolean checkPlayerCollision()
    {

        boolean answer = false;


    }*/


}