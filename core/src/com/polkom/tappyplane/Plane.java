package com.polkom.tappyplane;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by polkom21 on 2014-09-28 17:28.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class Plane {
    private BodyDef bodyDef;
    public Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;
    private PolygonShape shape;
    private TextureRegion texture;
    public Sprite sprite;

    private float MAX_VELOCITY = 2.f;

    public Plane() {
        texture = Assets.planes.findRegion("planeYellow1");
        sprite = new Sprite(texture);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(WorldManager.ConvertToBox(0f), WorldManager.ConvertToBox(300f));
        bodyDef.fixedRotation = true;
        body = WorldManager.world.createBody(bodyDef);

        shape = new PolygonShape();
        shape.setAsBox(WorldManager.ConvertToBox(88.f / 2f), WorldManager.ConvertToBox(73.f / 2f));

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void update() {
        body.setLinearVelocity(3.f, body.getLinearVelocity().y);
        this.sprite.setPosition(getPosition().x, getPosition().y);
        this.sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

    public Vector2 getPosition() {
        //this.sprite.setRotation(body.getAngle());
        return new Vector2(WorldManager.ConvertToWorld(body.getPosition().x) - sprite.getWidth()/2, WorldManager.ConvertToWorld(body.getPosition().y) - sprite.getHeight()/2);
    }

    public void force() {
        //if(MAX_VELOCITY > body.getLinearVelocity().y) body.applyLinearImpulse(0.f, 1.5f, getPosition().x, getPosition().y, true);
        //body.applyLinearImpulse(0.f, .1f, getPosition().x, getPosition().y, true);
        body.applyForce(0.f, 15.f, getPosition().x, getPosition().y, true);
    }
}
