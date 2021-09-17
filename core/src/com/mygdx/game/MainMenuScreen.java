package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen
{

    Drop game;

    OrthographicCamera theCamera;

    public MainMenuScreen(Drop game)
    {
        this.game = game;

        theCamera = new OrthographicCamera();
        theCamera.setToOrtho(false, 800, 480);

    }


    @Override
    public void show()
    {

    }

    public void update()
    {
        Input anInput = Gdx.input;

        if (anInput.isTouched())
        {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        theCamera.update();
        game.batch.setProjectionMatrix(theCamera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to meow", 100, 150);
        game.font.draw(game.batch, "Press anywhere to begin", 100, 100);
        game.batch.end();

        update();
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
