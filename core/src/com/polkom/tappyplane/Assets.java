package com.polkom.tappyplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by polkom21 on 2014-09-27 13:22.
 * Package: com.polkom.tappyplane
 * Project: TappyPlane
 */
public class Assets {
    public static Texture background;
    public static Sprite bg;
    public static BitmapFont font;
    public static TextureAtlas elements;
    public static TextureAtlas planes;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }
    public static void load() {
        Texture fontTexture = loadTexture("kenvector_future.png");
        fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font = new BitmapFont(Gdx.files.internal("kenvector_future.fnt"), new TextureRegion(fontTexture), false);
        font.setColor(Color.BLACK);

        elements = new TextureAtlas("elements.pack");
        planes = new TextureAtlas("planes.pack");

        background = loadTexture("background.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bg = new Sprite(background, 0, 0, background.getWidth(), background.getHeight());
    }
}
