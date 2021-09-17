package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/*
    Main class for Desktop launcher
 */
public class Woods extends Game
{

    SpriteBatch batch;
    Texture img;
    BitmapFont font;

    @Override
    public void create()
    {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        batch.dispose();
        font.dispose();
    }
}
