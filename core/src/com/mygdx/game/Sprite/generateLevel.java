package com.mygdx.game.Sprite;

import java.util.ArrayList;
import java.util.Collections;

public class generateLevel
{
    public static ArrayList<ArrayList<Tiles> > getLevel(int level)
    {
        ArrayList<ArrayList<Tiles> > ret = new ArrayList<ArrayList<Tiles>>();
        if(level == 1)
        {
            int level1[][] =
            {
                {0, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                {2, 0, 4, 0, 0, 0, 0, 4, 0, 2},
                {1, 3, 0, 0, 2, 4, 0, 0, 3, 1},
                {3, 1, 4, 0, 4, 2, 0, 4, 1, 3},
                {3, 3, 0, 0, 1, 1, 0, 0, 3, 3},
                {3, 4, 4, 0, 0, 0, 0, 4, 4, 3}
            };
            for(int i = 0 ; i < 6 ; i ++)
            {
                ArrayList<Tiles> row = new ArrayList<Tiles>();
                for(int j = 0 ; j < 10 ; j++)
                {
                    row.add(new Tiles(level1[i][j]));
                }
                ret.add(row);
            }
        }
        else if(level == 2)
        {
            int level2[][] =
            {
                {4, 3, 4, 3, 4, 3, 4, 3, 4, 3},
                {0, 3, 3, 3, 3, 3, 3, 3, 3, 0},
                {1, 0, 2, 2, 2, 2, 2, 2, 0, 1},
                {1, 1, 0, 2, 2, 2, 2, 0, 1, 1},
                {4, 4, 4, 0, 1, 1, 0, 4, 4, 4}
            };
            for(int i = 0 ; i < 5 ; i ++)
            {
                ArrayList<Tiles> row = new ArrayList<Tiles>();
                for(int j = 0 ; j < 10 ; j++)
                {
                    row.add(new Tiles(level2[i][j]));
                }
                ret.add(row);
            }
        }
        Collections.reverse(ret);
        return ret;
    }
}
