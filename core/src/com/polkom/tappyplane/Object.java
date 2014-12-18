package com.polkom.tappyplane;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by polkom21 on 2014-10-01 23:09.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class Object {
    private BodyDef bodyDef;
    public Body body;
    private FixtureDef fixtureDef;
    public TextureAtlas.AtlasRegion texture;
    private Vector2 size, position;

    public Object(String textureName, Vector2 position) {
        this.position = new Vector2(position);
        this.texture = Assets.elements.findRegion(textureName);
        this.size = new Vector2(texture.getRegionWidth(), texture.getRegionHeight());

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(WorldManager.ConvertToBox(this.position.x), WorldManager.ConvertToBox(this.position.y));

        fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        body = WorldManager.world.createBody(bodyDef);
        WorldManager.loader.attachFixture(body, "ground", fixtureDef, WorldManager.ConvertToBox(this.size.x));
    }

    public Vector2 getPosition() {
        return new Vector2(WorldManager.ConvertToWorld(body.getPosition().x), WorldManager.ConvertToWorld(body.getPosition().y));
    }
}
