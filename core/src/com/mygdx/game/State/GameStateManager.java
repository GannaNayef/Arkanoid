package com.mygdx.game.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager
{
    private Stack<State> states;
    public GameStateManager()
    {
        states = new Stack<State>();
    }
    public void push(State state)
    {
        states.push(state);
    }
    public void pop()
    {
        states.pop().dispose();
    }
    public void setState(State state)
    {
        this.pop();
        this.push(state);
    }
    public void update(float dt)
    {
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
}
