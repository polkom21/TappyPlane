package com.polkom.tappyplane;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by polkom21 on 2014-10-02 21:07.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class BodyContactListener implements ContactListener {

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureB().getBody().getType() == BodyDef.BodyType.DynamicBody || contact.getFixtureA().getBody().getType() == BodyDef.BodyType.DynamicBody){
            //GameScreen.gameOver();
            GameScreen.gameOver = true;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
