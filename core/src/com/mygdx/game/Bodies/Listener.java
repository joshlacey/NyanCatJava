package com.mygdx.game.Bodies;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;


public class Listener implements ContactListener {
		@Override
		public void endContact(Contact contact) {

		}

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

		@Override
		public void beginContact(Contact contact) {
			System.out.println("Game Over");
		}
	};
