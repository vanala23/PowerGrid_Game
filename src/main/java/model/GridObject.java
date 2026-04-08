package model;

import lombok.Getter;

import java.awt.*;

@Getter
public abstract class GridObject{
    protected int x;
    protected int y;
    protected boolean isAlive = true;

    public GridObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2d, int tileSize);
}