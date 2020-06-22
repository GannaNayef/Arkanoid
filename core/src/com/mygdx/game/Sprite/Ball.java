package com.mygdx.game.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Arkanoid;

public class Ball
{
    public static final int Width = 30;
    public static final int Height = 30;
    public static final int Velocity = 15;

    public static boolean isSticky = false;
    public static boolean isSlowed = false;

    private Sound collision;
    private double posX = (Arkanoid.WIDTH/2 - Vaus.Width/2), posY = 100;
    private Texture txt;
    private Vector2 velocity;
    public boolean moving;


    public Ball()
    {
        txt = new Texture("ball.png");
        velocity = new Vector2(0, 0);
        moving = false;
        collision = Gdx.audio.newSound(Gdx.files.internal("poop.wav"));
    }

    public Ball(Ball ball, float theta)
    {
        txt = new Texture("ball.png");
        collision = Gdx.audio.newSound(Gdx.files.internal("poop.wav"));
        posX = ball.posX;
        posY = ball.posY;
        float vx = (float)(ball.getVel().x * Math.cos(theta*Math.PI/180.0) - ball.getVel().y * Math.sin(theta*Math.PI/180.0));
        float vy = (float)(ball.getVel().x * Math.sin(theta*Math.PI/180.0) + ball.getVel().y * Math.cos(theta*Math.PI/180.0));
        velocity = new Vector2(vx, vy);
        moving = ball.moving;
    }

    public Vector2 getVel()
    {
        return velocity;
    }

    public Texture getTexture()
    {
        return txt;
    }

    public int getPosX()
    {
        return (int)posX;
    }

    public int getPosY()
    {
        return (int)posY;
    }

    public void changeDir(char dir)
    {
        collision.play();
        if(dir == 'U' || dir == 'D') velocity.y *= -1;
        else if(dir == 'L' || dir == 'R') velocity.x *= -1;
    }

    public void updatePos()
    {
        double temp = 1;
        if(isSlowed) temp = 2.0/3.0;
        posX += velocity.x * temp;
        posY += velocity.y * temp;
    }

    public void collideWithVaus(double dt, double vausWidth)
    {
        collision.play();
        if(isSticky) this.moving = false;
        double temp = 1;
        if(isSlowed) temp = 2.0/3.0;
        double theta = ((dt/vausWidth)*120.0 + 30.0) * Math.PI / 180.0;
        velocity.x = -(float)((Velocity * Math.cos(theta)) * temp);
        velocity.y = (float)((Velocity * Math.sin(theta)) * temp);
    }

    public void reset(double x, double y)
    {
        posX = x;
        posY = y;
        moving = false;
    }

    public void setPosX(double x)
    {
        this.posX = x;
    }

    public void setPosY(double y)
    {
        this.posY = y;
    }

    public void dispose()
    {
        txt.dispose();
    }
}
