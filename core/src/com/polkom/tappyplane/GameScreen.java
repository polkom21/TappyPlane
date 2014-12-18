package com.polkom.tappyplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.ArrayList;

/**
 * Created by polkom21 on 2014-09-27 19:41.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class GameScreen extends ScreenAdapter {
    private static TappyPlane game;
    private OrthographicCamera camera;
    private Vector2 cameraPosition;
    private WorldManager worldManager;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;

    public static boolean gameOver = false;

    private Plane plane;
    private Object ground;
    private ArrayList<Object> borders = new ArrayList<Object>();

    public GameScreen(TappyPlane game){
        this.game = game;
        cameraPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        camera = new OrthographicCamera();
        camera.position.set(cameraPosition, 0);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldManager = new WorldManager(new Vector2(0, -10), true);
        plane = new Plane();
        debugMatrix = new Matrix4(camera.combined);
        debugMatrix.scale(WorldManager.BOX_WORLD_TO, WorldManager.BOX_WORLD_TO, 1f);
        debugRenderer = new Box2DDebugRenderer();
        //debugRenderer.setDrawAABBs(true);
        //debugRenderer.setDrawBodies(true);
        debugRenderer.setDrawContacts(true);
        //debugRenderer.setDrawInactiveBodies(true);
        //debugRenderer.setDrawJoints(true);
        //debugRenderer.setDrawVelocities(true);
        ground = new Object("groundGrass", new Vector2(0, 0));
        for(int i = 0; i < 10; i++){
            Object temp = new Object("groundGrass", new Vector2(808 * i, 0));
            borders.add(temp);
        }
    }

    public void update(float deltaTime) {
        //float deltaTime = Gdx.graphics.getDeltaTime();
        if(!this.gameOver){
            //System.out.println("Delta: " + deltaTime);
            worldManager.doPhysicsStep(deltaTime);
            plane.update();
            if(plane.getPosition().y > Gdx.graphics.getHeight()) this.gameOver = true;
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plane.force();
            }
            if(plane.getPosition().x >= 200) {
                camera.position.set(plane.getPosition().x +200, cameraPosition.y, 0);
                debugMatrix = new Matrix4(camera.combined);
                debugMatrix.scale(WorldManager.BOX_WORLD_TO, WorldManager.BOX_WORLD_TO, 1f);
                Assets.bg.setPosition(plane.getPosition().x - 200, 0);
                Assets.bg.scroll(1.f * deltaTime, 0);
            }
        }
        //Assets.bg.scroll(0.5f * deltaTime, 0);
        //Assets.bg.scroll(plane.body.getLinearVelocity().x / 2 * deltaTime, 0);
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batcher.setProjectionMatrix(camera.combined);
        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.bg, Assets.bg.getX(), Assets.bg.getY());
        plane.sprite.draw(game.batcher);
        //game.batcher.draw(ground.texture, ground.getPosition().x, ground.getPosition().y);
        for(int i = 0; i < borders.size(); i++){
            game.batcher.draw(borders.get(i).texture, borders.get(i).getPosition().x, borders.get(i).getPosition().y);
        }
        game.batcher.end();
        debugRenderer.render(worldManager.world, debugMatrix);
    }

    @Override
    public void render(float deltaTime) {
        update(deltaTime);
        draw();
    }

    /*public static void gameOver() {
        game.setScreen(new MainMenuScreen(game));
    }*/
}
