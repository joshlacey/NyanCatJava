package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Bodies.Floor;
import com.mygdx.game.Bodies.Walls;
import com.mygdx.game.Bodies.NyanCat;
import com.mygdx.game.Bodies.Listener;
import com.mygdx.game.Bodies.Planet;

public class GameScreen implements Screen {

    private Stage stage;
    private Game game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private RandomXS128 randOffset;
    private RandomXS128 randRadius;
    private RandomXS128 randPlanet;
    private int renders = 0;
    private float pos_x = Gdx.graphics.getWidth();
    private float pos_y = Gdx.graphics.getHeight();
    private Music music;

    public GameScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -1), true);
        randOffset = new RandomXS128();
        randRadius = new RandomXS128();
        randPlanet = new RandomXS128();
        music = Gdx.audio.newMusic(Gdx.files.internal("nyan-cat.mp3"));


        // Create a body in the world using our definition
        final Body nyanBody = createBody(new Vector2(pos_x/2, pos_y), BodyType.DynamicBody);
        final NyanCat nyanCat = new NyanCat(nyanBody, pos_x/2, pos_y);
        stage.addActor(nyanCat);

        stage.addActor(new Floor(world,0,0,pos_x,pos_y/10,0));


        stage.addListener(new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keycode) {
            if(keycode == Keys.SPACE) {

              nyanCat.applyLinearImpulse();

            }
            return true;
          }
        });
        world.setContactListener(new Listener() {
          @Override
          public void beginContact(Contact contact) {
            System.out.println("Game Over");
            music.stop();
            game.setScreen(new GameOverScreen(game, renders));

          }
        });
        music.play();
    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
        Gdx.input.setInputProcessor(stage);
    }

    private Body createBody(Vector2 position, BodyType type) {
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = type;
      // Set its world position
      bodyDef.position.set(position);
      // Create a body from the defintion and add it to the world
      return world.createBody(bodyDef);
    }

    private Vector2 getPlanetStartPosition(float radius) {
      float offset = randOffset.nextFloat();
      float floorHeight = pos_y/10;
      float padding = 60.0f; // the padding on the top and bottom
      float window = pos_y - padding - padding - floorHeight; // make it so that the center is always calculated within 30 pixels of the top and bottom.
      float center = ( window * offset ) + floorHeight + padding;
      return new Vector2(pos_x + radius, center);
    }

    @Override
    public void render(float delta) {
        //java 8
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        //debugRenderer.render(world, stage.getCamera().combined);
        renders++;
        if(renders % 100 == 0) {
          float radius = 20.0f + (randRadius.nextFloat() * 50.0f);
          Vector2 position = getPlanetStartPosition(radius);
          Body body = createBody(position, BodyType.KinematicBody);
          int planetNum = randPlanet.nextInt(11) + 1;
          Planet planet = new Planet(body, position, radius, planetNum);
          stage.addActor(planet);
        };
        world.step(1/3f, 6, 2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}
