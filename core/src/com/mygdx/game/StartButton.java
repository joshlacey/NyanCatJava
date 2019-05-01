package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class StartButton extends Button {
	public StartButton (int centerX, int centerY) {
		super(new Skin(Gdx.files.internal("skin/glassy-ui.json"))); // button has to be called with at least a skin?
		center(centerX, centerY);
	}

	public void center (int windowCenterX, int windowCenterY) {
		float buttonCenterX = windowCenterX - (this.getWidth()/2);
		float buttonCenterY = windowCenterY - (this.getHeight()/2);
		this.setPosition(buttonCenterX, buttonCenterY);
	}
}
