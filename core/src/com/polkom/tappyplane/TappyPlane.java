package com.polkom.tappyplane;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TappyPlane extends Game {
	public SpriteBatch batcher;

	@Override
	public void create () {
        batcher = new SpriteBatch();
        Assets.load();
        setScreen(new MainMenuScreen(this));
	}

	@Override
    public void render () {
        super.render();
	}
}
