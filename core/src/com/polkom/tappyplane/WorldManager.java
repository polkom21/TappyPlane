package com.polkom.tappyplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by polkom21 on 2014-09-28 17:10.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class WorldManager {
    public static World world;
    public static float timeStep;
    public static int velocityIterations = 6;
    public static int positionIterations = 2;
    public static float accumulator = 0;
    public static final float WORLD_TO_BOX = 0.01f;
    public static final float BOX_WORLD_TO = 100f;

    public static BodyEditorLoader loader;
    public static BodyContactListener listener;

    public WorldManager(Vector2 gravity, boolean doSleep){
        timeStep = 1/60f;
        world = new World(new Vector2(gravity), doSleep);
        world.setContactListener(new BodyContactListener());
        //listener = new BodyContactListener(world);
        loader = new BodyEditorLoader(Gdx.files.internal("elements.json"));
    }

    public static void doPhysicsStep(float deltaTime) {
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while(accumulator >= timeStep) {
            world.step(timeStep, velocityIterations, positionIterations);
            accumulator -= timeStep;
        }
    }

    public static float ConvertToBox(float x){
        return x * WORLD_TO_BOX;
    }
    public static float ConvertToWorld(float x) {
        return x * BOX_WORLD_TO;
    }
}
