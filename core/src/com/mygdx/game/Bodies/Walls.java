package com.mygdx.game.Bodies;

import com.mygdx.game.Bodies.Wall;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Walls {
  World world;
  Vector2 dimensions;

  public Walls(World aWorld, Stage stage, Vector2 theDimensions, float offsetFromTop) {
    world = aWorld;
    dimensions = theDimensions;
    createWalls(stage, offsetFromTop);
  }

  private void createWalls(Stage stage, float offsetFromTop) {
    float floorHeight = dimensions.y/10;
    float padding = 60.0f; // the padding on the top and bottom
    float window = dimensions.y - padding - padding - floorHeight; // make it so that the center is always calculated within 30 pixels of the top and bottom.
    float center = ( window * offsetFromTop ) + floorHeight + padding;
    float opening = 120.0f;
    float top_height = dimensions.y - (center + opening/2);
    Wall top_wall = createWall(dimensions.y - top_height/2, top_height);
    float bottom_height = center - opening/2 - floorHeight ;
    Wall bottom_wall = createWall( bottom_height/2 + floorHeight, bottom_height);
    stage.addActor(top_wall);
    stage.addActor(bottom_wall);
  }

  private Wall createWall(float position_y, float height) {
    float width = 30.0f;
    return new Wall(createBody(new Vector2(dimensions.x + width, position_y)), new Vector2(width, height));
  }

  private Body createBody(Vector2 position) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.KinematicBody;
    // Set its world position
    bodyDef.position.set(position);
    // Create a body from the defintion and add it to the world
    return world.createBody(bodyDef);
  }
}
