package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.image.Kernel;

public class BoardScreen implements Screen
{

    OrthographicCamera theCamera;
    //Board aBoard;
    BoardController aBoardController;
    Woods game;
    ShapeRenderer aShape;
    int rows;
    int columns;
    Screen aScreen;

    public BoardScreen(Woods aGame, Screen aScreen, int rows, int columns)
    {
        this.aScreen = aScreen;
        this.game = aGame;
        this.rows = rows;
        this.columns = columns;
        this.aShape = new ShapeRenderer();
        theCamera = new OrthographicCamera();
        theCamera.setToOrtho(true, 800, 480);

        int rightSideBuffer = 0;
        int bottomEdgeBuffer = 0;
        //Subtracting the rightSideBuffer from theCamera.viewportWidth or height will leave blank space on the right side or bottom side
        aBoardController = new BoardController(rows, columns, (theCamera.viewportWidth - rightSideBuffer) / columns, (theCamera.viewportHeight - bottomEdgeBuffer) / rows, 4);
        aBoardController.createArray();
        aBoardController.createPlayersDefaultLocation();

        //this.aBoard = new Board(rows, columns, (theCamera.viewportWidth-30)/columns, theCamera.viewportHeight/rows);

        //aBoard.draw(aShape);
    }

    @Override

    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        theCamera.update();
        aShape.setProjectionMatrix(theCamera.combined);
        aBoardController.drawBoard(aShape);
        aBoardController.drawPlayers(aShape);
        game.batch.begin();
        game.font.draw(game.batch, "Total Moves -- " + aBoardController.totalPlayerMovements, 100, 150);
        game.batch.end();
        /*aShape.begin(ShapeRenderer.ShapeType.Filled);
        aShape.setColor(Color.FOREST);
        aShape.circle(0, 0, 30);
        aShape.end();*/
        update();
    }

    public void update()
    {
        Input anInput = Gdx.input;

        if (anInput.isTouched() || anInput.isKeyPressed(Input.Keys.ESCAPE))
        {
            //ScreenUtils.clear(0, 0, 02.f, 1);
            this.game.setScreen(aScreen);
            //hide();
        }
        if (anInput.isKeyPressed(Input.Keys.RIGHT))
        {
            aBoardController.increaseSpeed();
        }
        if (anInput.isKeyPressed(Input.Keys.LEFT))
        {
            aBoardController.decreaseSpeed();
        }
        aBoardController.updatePlayers();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {
        this.dispose();
    }

    @Override
    public void dispose()
    {
        aShape.dispose();
        aScreen.dispose();
    }
}
