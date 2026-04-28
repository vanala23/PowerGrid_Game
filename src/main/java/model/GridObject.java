package model;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class GridObject{
    protected int x;
    protected int y;

    protected List<GridObject> connections;
    protected double currentPower;

    protected boolean isAlive = true;


    public GridObject(int x, int y){
        this.x = x;
        this.y = y;
        this.connections = new ArrayList<>();
        this.currentPower = 0.0f;
    }

    public void addConnection(GridObject obj) {
        connections.add(obj);
    }

    public void setPower(double power){
        this.currentPower = power;
    }

    public double getPower(){
        return currentPower;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2d, int tileSize);

    public abstract String getInfoText();
    public abstract String getTutorialText();
}