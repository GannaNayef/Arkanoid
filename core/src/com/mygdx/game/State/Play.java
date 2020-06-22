package com.mygdx.game.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Arkanoid;
import com.mygdx.game.Sprite.*;


import java.util.ArrayList;

public class Play extends State
{
    private Texture bg;
    private int lives = 2;
    public static ArrayList<Ball> balls;
    private ArrayList<powerUps> powerups;
    private Vaus vaus;
    private Texture Lives;
    private ArrayList<ArrayList<Tiles> > level;
    private Texture[] txt = new Texture[5];
    private int lvl;
    private int resetTime;

    public Play(GameStateManager gsm, int lvl)
    {
        super(gsm);
        this.lvl = lvl;
        bg = new Texture("bg.jpg");
        balls = new ArrayList<Ball>();
        balls.add(new Ball());
        powerups = new ArrayList<powerUps>();
        vaus = new Vaus();
        Lives = new Texture("lives.png");
        level = new ArrayList<ArrayList<Tiles> >(generateLevel.getLevel(lvl));
        txt[1] = new Texture("redTile.png");
        txt[2] = new Texture("blueTile.png");
        txt[3] = new Texture("goldTile.png");
        txt[4] = new Texture("blackTile.png");
    }

    @Override
    protected void handleInput()
    {
        Gdx.input.setCursorCatched(true);
        Gdx.input.setCursorPosition(Math.max(0, Math.min(Gdx.input.getX(), Arkanoid.WIDTH - vaus.getWidth())), Gdx.input.getY());
        vaus.setPos(Gdx.input.getX());
        for(int ii = 0 ; ii < balls.size() ; ii++)
        {
            Ball ball = balls.get(ii);
            if(ball.moving)
            {
                if(ball.getPosY() < 60)
                {
                    if(balls.size() > 1)
                    {
                        balls.remove(ii);
                        ii--;
                    }
                    else
                    {
                        if(lives > 0)
                        {
                            lives--;
                            balls.get(ii).reset(vaus.getPosX() + vaus.getWidth()/2, vaus.getPosY() + Vaus.Height);
                            return;
                        }
                        else
                        {
                            gsm.setState(new Menu(gsm));
                        }
                    }
                    continue;
                }
                else if(ball.getPosX() + Ball.Width/2 >= vaus.getPosX() && ball.getPosX() + Ball.Width/2 <= vaus.getPosX() + vaus.getWidth() && ball.getPosY() <= vaus.getPosY() + Vaus.Height)
                {
                    ball.collideWithVaus(ball.getPosX() - vaus.getPosX() + Ball.Width/2, vaus.getWidth());
                }
                else if(ball.getPosY() + Ball.Height >= Arkanoid.HEIGHT)
                {
                    ball.changeDir('U');
                }
                else if(ball.getPosX() <= 0)
                {
                    ball.setPosX(0);
                    ball.changeDir('L');
                }
                else if(ball.getPosX() + Ball.Width >= Arkanoid.WIDTH)
                {
                    ball.setPosX(Arkanoid.WIDTH - Ball.Width);
                    ball.changeDir('R');
                }
                else
                {
                    int cnt = 0;
                    for(int i = 0 ; i < level.size() ; i++)
                    {
                        for(int j = 0 ; j < level.get(i).size() ; j++)
                        {
                            if(level.get(i).get(j).getHp() > 0)
                            {
                                if(level.get(i).get(j).getCol() < 4) cnt++;
                                float tileX = Tiles.Width*j + 12;
                                float tileY = Tiles.Height*i + 500;
                                float x = ball.getPosX() + Ball.Width, y = ball.getPosY() + Ball.Height;
                                float closeX = Math.max(Math.min(tileX + Tiles.Width, x), tileX);
                                float closeY = Math.max(Math.min(tileY + Tiles.Height, y), tileY);
                                if(Math.sqrt((closeX - x)*(closeX - x) + (closeY - y)*(closeY - y)) > Ball.Width/2) continue;
                                if(closeX == tileX) ball.changeDir('L');
                                else if(closeY == tileY) ball.changeDir('U');
                                else if(closeX == tileX + Tiles.Width) ball.changeDir('R');
                                else ball.changeDir('D');
                                level.get(i).get(j).hit();
                                if(level.get(i).get(j).getHp() == 0)
                                {
                                    if(powerUps.dropChance(level.get(i).get(j).getCol()))
                                    {
                                        powerups.add(new powerUps(Tiles.Width*j + 12 + Tiles.Width/2 - powerUps.Width/2, Tiles.Height*i + 500));
                                    }
                                }
                            }
                        }
                    }
                    if(cnt == 0)
                    {
                        if(lvl < 2) gsm.setState(new Play(gsm, lvl+1));
                        else gsm.setState(new Menu(gsm));
                    }
                }
                ball.updatePos();
            }
            else
            {
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                {
                    ball.moving = true;
                }
                else
                {
                    ball.setPosX(vaus.getPosX() + vaus.getWidth()/2 - Ball.Width/2);
                    ball.setPosY(vaus.getPosY() + Vaus.Height);
                }
            }
        }
        for(int i = 0 ; i < powerups.size() ; i++)
        {
            powerUps p = powerups.get(i);
            if(p.getPosY() <= -powerUps.Height)
            {
                powerups.remove(i);
                i--;
            }
            else if(p.getPosX() + powerUps.Width > vaus.getPosX() && p.getPosX() < vaus.getPosX() + vaus.getWidth() && vaus.getPosY() + Vaus.Height >= p.getPosY() && vaus.getPosY() <= p.getPosY() + powerUps.Height)
            {
                p.activatePowerup();
                resetTime = 500;
                powerups.remove(i);
                i--;
            }
            else p.updatePos();
        }
    }

    @Override
    protected void update(float dt)
    {
        if(resetTime > -1) resetTime--;
        if(resetTime == 0)
        {
            powerUps.reset();
        }
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(bg, 0, 0);
        for (Ball ball : balls) sb.draw(ball.getTexture(), ball.getPosX(), ball.getPosY(), Ball.Width, Ball.Height);
        for (powerUps powerup : powerups) sb.draw(powerup.getTexture(), powerup.getPosX(), powerup.getPosY(), powerup.Width, powerup.Height);
        sb.draw(vaus.getTexture(), vaus.getPosX(), vaus.getPosY(), vaus.getWidth(), Vaus.Height);
        for(int i = 0 ; i <= lives ; i++)
        {
            sb.draw(Lives, Arkanoid.WIDTH - 140 + i*(40), Arkanoid.HEIGHT - 100, 40, 40);
        }
        for(int i = 0 ; i < level.size() ; i++)
        {
            for(int j = 0; j < level.get(i).size() ; j++)
            {
                if(level.get(i).get(j).getHp() > 0)
                {
                    sb.draw(txt[level.get(i).get(j).getCol()], Tiles.Width*j + 12, Tiles.Height*i + 500, Tiles.Width, Tiles.Height);
                }
            }
        }
        sb.end();
    }

    @Override
    protected void dispose()
    {
        bg.dispose();
        vaus.dispose();
        Lives.dispose();
    }
}
