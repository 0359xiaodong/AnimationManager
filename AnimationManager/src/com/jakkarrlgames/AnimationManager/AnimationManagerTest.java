package com.jakkarrlgames.AnimationManager;

/*
 *AnimationManager for libgdx
Copyright (C) 2014  Raghuram Iyer Ragzzy-R"

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
* 
* */

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimationManagerTest implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private AnimatableEntity player;
	private Animator animator;
	private Texture testTexture;
	private Sprite testSprite;
	Entity test;
	private Animation anim;
	float stateTime;

	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		animator = new Animator(2,2);
		player = new AnimatableEntity(1);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		batch = new SpriteBatch();
		test = new Entity();
		testTexture = new Texture(Gdx.files.internal("data/libgdx.png"));
		test.getRegionFromTexture(testTexture,0	,0,testTexture.getWidth(),testTexture.getHeight());
		testSprite = test.createSprite(new Vector2(300,300),new Vector2(100,100));
		animator.addFrameDimesion(0, 0, 0, 121, 130);
		animator.addFrameDimesion(1, 221, 0, 121, 130);
		animator.addFrameDimesion(2, 246, 0, 121, 130);
		animator.addFrameDimesion(3, 364, 0, 150, 130);
		anim = animator.createAnimation("data/anim.png",.08f);
		stateTime = 0;
		player.attachAnimator(animator, "walk");
		player.setCurrentAnimator("walk");
	}

	@Override
	public void dispose() {
		batch.dispose();
		//texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		 stateTime += Gdx.graphics.getDeltaTime();
		// currentFrame =anim.getKeyFrame(stateTime, true);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	//	batch.draw(currentFrame,100,100);
		//testSprite.draw(batch);
		player.render(batch,100,100,stateTime);
		batch.end();
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
}
