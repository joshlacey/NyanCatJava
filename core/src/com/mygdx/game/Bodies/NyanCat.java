package com.mygdx.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class NyanCat extends Image  {

    private ParticleEffect effect;
    private Body body;

    public NyanCat(Body aBody, float pos_x,float pos_y){
        super(new Texture("nyan-cat.png"));
        this.setPosition(pos_x,pos_y);
        this.setSize(120.0f, 100.0f);
        body = aBody;
        // Now define the dimensions of the physics shape
        PolygonShape shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimensions as our sprite
        shape.setAsBox(this.getWidth()/3, this.getHeight()/4);
        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others we will see shortly
        // If you are wondering, density and area are used to calculate over all mass
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution= 0f;
        body.createFixture(fixtureDef);
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("rainbow2"), Gdx.files.internal("particles"));
        effect.scaleEffect(0.4f);
        effect.start();
        effect.setPosition(this.getX() + 30.0f,this.getHeight()/2+this.getY());
        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();
    }

    public void applyLinearImpulse() {
      float mass = body.getMass();
      float impulse = body.getMass() * 1000000;
      System.out.println(impulse);
      System.out.println(mass);
      body.applyLinearImpulse( new Vector2(0,impulse), body.getWorldCenter(), false );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
      effect.draw(batch);
      super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
      effect.setPosition(this.getX() + 30.0f,this.getHeight()/2+this.getY());
      effect.update(delta);
      super.act(delta);
      this.setRotation(body.getAngle()*  MathUtils.radiansToDegrees);
      this.setPosition(body.getPosition().x-this.getWidth()/2,body.getPosition().y-this.getHeight()/2);
    }

}
