package com.polkom.tappyplane.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.polkom.tappyplane.TappyPlane;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 480;
        config.fullscreen = false;
        config.resizable = false;
		new LwjglApplication(new TappyPlane(), config);
	}
}
