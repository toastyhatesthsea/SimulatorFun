package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen
{

    Drop game;

    Texture dropImage;
    Texture bucketImage;
    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;


    public GameScreen(Drop game)
    {
        this.game = game;

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);

        // create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = camera.viewportWidth / 2 - bucketImage.getWidth() / 2;
        bucket.y = 20;

        bucket.width = bucketImage.getWidth();
        bucket.height = bucketImage.getHeight();

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<>();
        spawnRaindrop();

    }

    private void spawnRaindrop()
    {
        Rectangle rainRect = new Rectangle();
        rainRect.x = MathUtils.random(0, camera.viewportWidth - dropImage.getWidth());
        rainRect.y = 480;
        rainRect.height = dropImage.getHeight();
        rainRect.width = dropImage.getWidth();
        raindrops.add(rainRect);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void show()
    {
        rainMusic.play();
    }

    public void update()
    {
        Input anInput = Gdx.input;

        if (anInput.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(anInput.getX(), anInput.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - bucket.width / 2;
        }
        if (anInput.isKeyPressed(Input.Keys.LEFT))
        {
            bucket.x -= 350 * Gdx.graphics.getDeltaTime();
        }
        if (anInput.isKeyPressed(Input.Keys.RIGHT))
        {
            bucket.x += 350 * Gdx.graphics.getDeltaTime();
        }

        if (bucket.x  < 64)
        {
            bucket.x = 64;
        }
        if (bucket.x > camera.viewportWidth - bucket.width)
        {
            bucket.x = camera.viewportWidth - 64;
        }

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
        {
            spawnRaindrop();
        }

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the
        // value our drops counter and add a sound effect.
        for (Rectangle aDrop : raindrops)
        {
            aDrop.y = aDrop.y -  200 * Gdx.graphics.getDeltaTime();
            if (aDrop.y + aDrop.height < 0)
            {
                raindrops.removeValue(aDrop, true);
            }
            if (bucket.overlaps(aDrop))
            {
                dropsGathered++;
                dropSound.play();
                raindrops.removeValue(aDrop, true);
            }
        }

    }

    @Override
    public void render(float delta)
    {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, camera.viewportHeight);
        game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);

        for (Rectangle aRain : raindrops)
        {
            game.batch.draw(dropImage, aRain.x, aRain.y);
        }
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
        dropSound.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
