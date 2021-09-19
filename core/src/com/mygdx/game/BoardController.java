package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;
import java.util.function.Function;

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
        this.playerUpdateTime = .3f; //Will update player movement every 3 seconds of game delta time
        this.playerMovementTimer = 0;
    }

    /**
     * Creates players at RANDOM locations on the board using Player objects and stores them in an array
     */
    public void createPlayersRandomLocations()
    {
        Random aRan = new Random();
        int currentPlayersAdded = 0;

        //This loop will add random players and makes sure that it doesn't add on the same location
        while (currentPlayersAdded < this.numberOfPlayers)
        {
            int xArrayLocation = aRan.nextInt(numberOfColumns);
            int yArrayLocation = aRan.nextInt(numberOfRows);

            if (playerBoard.boardArray[yArrayLocation][xArrayLocation] == null)
            {
                Player aPlayer = new Player(xArrayLocation, yArrayLocation, Color.FIREBRICK, pixelBlockWidth, pixelBlockHeight, playerNames[currentPlayersAdded]);
                aPlayers[currentPlayersAdded] = aPlayer;
                playerBoard.boardArray[yArrayLocation][xArrayLocation] = aPlayer;
                currentPlayersAdded++;
            }
        }
    }

    /**
     * Creates players at default locations. Which are the corners of the board.
     */
    public void createPlayersDefaultLocation()throws IllegalArgumentException
    {
        if (numberOfPlayers > 4)
        {
            throw new IllegalArgumentException("Too many players and not enough corners, for default corner locations");
        }

        int currentPlayersAdded = 0;
        int xVector = numberOfColumns - 1; //To eliminate index out of bounds exception, subtract 1
        int yVector = numberOfRows - 1;
        int xArrayLocation = 0;
        int yArrayLocation = 0;

        //This loop will add random players and makes sure that it doesn't add on the same location
        //This default player creation will create players starting at the Northwest corner
        while (currentPlayersAdded < this.numberOfPlayers)
        {
            Player aPlayer = new Player(xArrayLocation, yArrayLocation, Color.FIREBRICK, pixelBlockWidth, pixelBlockHeight, playerNames[currentPlayersAdded]);
            aPlayers[currentPlayersAdded] = aPlayer;
            playerBoard.boardArray[yArrayLocation][xArrayLocation] = aPlayer;
            currentPlayersAdded++;

            //SouthEast Corner
            if (xArrayLocation == 0 && yArrayLocation == 0)
            {
                xArrayLocation += xVector;
                yArrayLocation += yVector;
            }
            //SouthWest Corner
            else if (xArrayLocation == xVector && yArrayLocation == yVector)
            {
                xArrayLocation -= xVector;
            }
            //NorthEast Corner
            else
            {
                xArrayLocation += xVector;
                yArrayLocation -= yVector;
            }
        }
    }

    private void playerAdder(randomCreatePlayer createPlayerFunc, randomizer randomdizerFunc)
    {
        Random aRan = new Random();
        int currentPlayersAdded = 0;


        //This loop will add random players and makes sure that it doesn't add on the same location
        while (currentPlayersAdded < this.numberOfPlayers)
        {
            int xArrayLocation = randomdizerFunc.process(aRan, numberOfColumns);
            int yArrayLocation = randomdizerFunc.process(aRan, numberOfRows);

            if (createPlayerFunc.process(playerBoard.boardArray, yArrayLocation, xArrayLocation))
            {
                Player aPlayer = new Player(xArrayLocation, yArrayLocation, Color.FIREBRICK, pixelBlockWidth, pixelBlockHeight, playerNames[currentPlayersAdded]);
                aPlayers[currentPlayersAdded] = aPlayer;
                playerBoard.boardArray[yArrayLocation][xArrayLocation] = aPlayer;
                currentPlayersAdded++;
            }
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

    public void decreaseSpeed()
    {
        playerUpdateTime += 0.01f;
    }

    public void increaseSpeed()
    {
        if ((playerUpdateTime - 0.01f) >= 0.0f)
        {
            playerUpdateTime -= 0.01f;
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
                assert somePlayer.yArrayLocation < numberOfRows;
            }
            totalPlayerMovements++;
            playerMovementTimer = 0;
        }
    }
}

interface randomCreatePlayer
{
    boolean process(Object[][] anOb, int row, int column);
}

interface randomizer
{
    int process(Random aRan, int maxNumber);
}