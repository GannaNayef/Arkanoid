package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Arkanoid;

public class Menu extends State
{
    private Texture backGround;
    BitmapFont font = new BitmapFont();
    public Menu(GameStateManager gsm)
    {
        super(gsm);
        backGround = new Texture("bg.jpg");
    }

    @Override
    public void handleInput()
    {
        Gdx.input.setCursorCatched(false);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
        {
            gsm.setState(new Play(gsm, 1));
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.E))
        {
            Gdx.app.exit();
        }
    }

    @Override
    protected void update(float dt)
    {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(backGround, 0, 0);
        font.getData().setScale(4.5f);
        font.draw(sb,"     Welcome !!  \n"+"Press Enter to Play .."+"\n"+"And Press 'E' to Exit ! ",(Arkanoid.WIDTH/4),(Arkanoid.HEIGHT/2+45));
        sb.end();
    }

    @Override
    protected void dispose()
    {
        backGround.dispose();
    }
}
