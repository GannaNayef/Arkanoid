package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.State.GameStateManager;
import com.mygdx.game.State.Menu;

public class Arkanoid extends ApplicationAdapter
{
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 1024;

	public static final String TITLE = "Arkanoid";

	private Music music;
	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void dispose()
	{
		super.dispose();
		music.dispose();
	}

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		music = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		music.setLooping(true);
		music.play();
		gsm.push(new Menu(gsm));
	}

	@Override
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
