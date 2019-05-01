package com.mygdx.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Wall extends Image {
  private Body body;

  public Wall(Body aBody, Vector2 size){
      super(new Texture(Gdx.files.internal("image.jpg")));
      this.setSize(size.x,size.y);
      body = aBody;

      // Create a polygon shape
      PolygonShape wall = new PolygonShape();
      // Set the polygon shape as a box which is twice the size of our view port and 20 high

      // (setAsBox takes half-width and half-height as arguments)
      wall.setAsBox(this.getWidth()/2, this.getHeight()/2, new Vector2(0,0), 0.0f);

      // Create a fixture from our polygon shape and add it to our ground body
      body.createFixture(wall, 0.0f);
      body.setLinearVelocity(-4.0f, 0.0f);
      // Clean up after ourselves
      wall.dispose();
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
