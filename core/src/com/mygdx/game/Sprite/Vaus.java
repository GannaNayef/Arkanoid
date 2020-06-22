package com.mygdx.game.Sprite;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Arkanoid;

public class Vaus
{
    public static final int Height = 50;
    public static final int Width = 200;
    public static final int extendedWidth = 400;

    public static boolean isExtended = false;

    private Texture txt, extendedTxt;
    private int posX = (Arkanoid.WIDTH/2 - Vaus.Width/2), posY = 60;

    public Vaus()
    {
        txt = new Texture("vaus.png");
        extendedTxt = new Texture("extendedVaus.png");
    }

    public void setPos(int x)
    {
        posX = Math.min(x, Arkanoid.WIDTH-Width);
    }

    public Texture getTexture()
    {
        if(isExtended) return extendedTxt;
        else return txt;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void dispose()
    {
        txt.dispose();
        extendedTxt.dispose();
    }

    public int getWidth()
    {
        if(isExtended) return extendedWidth;
        else return Width;
    }
}
