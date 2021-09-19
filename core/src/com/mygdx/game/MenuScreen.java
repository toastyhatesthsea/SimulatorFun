package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen
{
    Woods aGame;

    OrthographicCamera camera;
    Board aBoard;
    ShapeRenderer aShape;
    int rows;
    int columns;

    public MenuScreen(Woods aGame)
    {
        this.aGame = aGame;
        this.aShape = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.columns = 20;
        this.rows = 20;
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        aGame.batch.setProjectionMatrix(camera.combined);

        aGame.batch.begin();
        aGame.font.draw(aGame.batch, "Welcome to Random Movement Simulator", 100, 150);
        aGame.font.draw(aGame.batch, "Press anywhere to begin", 100, 100);
        aGame.batch.end();

        this.update();

        //aBoard.draw(this.aShape);

    }

    public void update()
    {
        Input anInput = Gdx.input;

        if (anInput.isTouched() || anInput.isKeyPressed(Input.Keys.ENTER))
        {
            aGame.setScreen(new BoardScreen(aGame, this, rows, columns));
        }
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

    }

    @Override
    public void dispose()
    {

    }
}
