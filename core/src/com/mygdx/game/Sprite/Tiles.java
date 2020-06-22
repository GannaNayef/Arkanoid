package com.mygdx.game.Sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Tiles
{
    public static final int Height = 50;
    public static final int Width = 100;

    private int col, hp;

    public Tiles(int HP)
    {
        col = hp = HP;
    }

    public int getHp()
    {
        return this.hp;
    }

    public int getCol()
    {
        return this.col;
    }

    public void hit()
    {
        if(this.col < 4) hp--;
    }
}
