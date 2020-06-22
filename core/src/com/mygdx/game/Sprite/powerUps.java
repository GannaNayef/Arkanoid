package com.mygdx.game.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.State.Play;
import javafx.print.PageLayout;

import java.util.Random;

public class powerUps
{
    public static final int Height = 100;
    public static final int Width = 100;
    public static final int Timeout = 10;

    private static final int VelocityY = 5;

    private Texture[] txt = new Texture[5];

    private int posX, posY, id;

    public static boolean dropChance(int chances)
    {
        Random rand = new Random();
        return rand.nextInt(7) < chances;
    }

    public  powerUps(int x, int y)
    {
        this.id = new Random().nextInt(4);
        this.posX = x;
        this.posY = y;
        txt[0] = new Texture("c.png");
        txt[1] = new Texture("e.png");
        txt[2] = new Texture("s.png");
        txt[3] = new Texture("d.png");
    }

    public static void reset()
    {
        Ball.isSticky = Vaus.isExtended = Ball.isSlowed = false;
    }

    public void activatePowerup()
    {
        reset();
        if(id == 0)
        {
            Ball.isSticky = true;
        }
        else if(id == 1)
        {
            Vaus.isExtended = true;
        }
        else if(id == 2)
        {
            Ball.isSlowed = true;
        }
        else if(id == 3)
        {
            int sz = Play.balls.size();
            for(int i = 0 ; i < sz ; i++)
            {
                Play.balls.add(new Ball(Play.balls.get(i), 30));
                Play.balls.add(new Ball(Play.balls.get(i), -30));
            }
        }
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void updatePos()
    {
        posY -= VelocityY;
    }

    public Texture getTexture()
    {
        return txt[this.id];
    }
}
