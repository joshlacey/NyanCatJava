package com.mygdx.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Bodies.Wall;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Planet extends Image {
  Body body;
  float radius;

  public Planet(Body aBody, Vector2 startPosition, float theRadius, int planetNumber) {
    super(new Texture(Gdx.files.internal("planets/" + planetNumber + ".png")));
    body = aBody;
    radius = theRadius;
    float diameter = theRadius * 2;

    System.out.println("file: " + "planets/" + planetNumber + ".png");
    this.setSize(diameter,diameter);
    this.setPosition(startPosition.x, startPosition.y);
    CircleShape circle = new CircleShape();
    circle.setRadius(theRadius);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = circle;
    fixtureDef.density = 2f;
    fixtureDef.friction = 0f;
    fixtureDef.restitution= 0f;
    body.createFixture(fixtureDef);
    body.setLinearVelocity(-8.0f, 0.0f);
    // Clean up after ourselves
    circle.dispose();
  }

  @Override
  public void act(float delta) {
      super.act(delta);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);
      this.setPosition(body.getPosition().x-this.getWidth()/2,body.getPosition().y-this.getHeight()/2);
  }
}
