package com.polkom.tappyplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by polkom21 on 2014-09-27 10:15.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class MainMenuScreen extends ScreenAdapter {
    TappyPlane game;
    OrthographicCamera guiCam;

    Rectangle exitBounds;
    Vector3 touchPoint;
    Vector2 camPosition;

    public MainMenuScreen(TappyPlane game) {
        this.game = game;

        guiCam = new OrthographicCamera();
        camPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        guiCam.position.set(camPosition, 0);
        guiCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        exitBounds = new Rectangle(100,  (Gdx.graphics.getHeight() / 2) + 100, 200, 50);
        touchPoint = new Vector3();
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        camPosition.x += 100.f * deltaTime;
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            game.setScreen(new GameScreen(this.game));
        }

        //Assets.bg.setSize(Assets.bg.getWidth() + camPosition.x, Assets.bg.getHeight());
        //Assets.bg.scroll(0.2f * deltaTime, 0);
        //updateloop
        /*if(Gdx.input.justTouched()){
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(exitBounds.contains(touchPoint.x, touchPoint.y)){
                Gdx.app.exit();
                return;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }*/
    }
    public void draw() {
        //draw elements
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //guiCam.position.set(camPosition, 0);
        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.bg, 0, 0);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.elements.findRegion("groundGrass"), 0, 0, 808, 71);
        Assets.font.draw(game.batcher, "tap to start", 100, 400);
        game.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
}
