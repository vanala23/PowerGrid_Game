package model.energy;

import model.GridObject;

import java.awt.*;

public class PowerPlant extends GridObject {
    private double maxPower; // MegaWatt
    private double currentOutput;

    public PowerPlant(int x, int y, double maxPower){
        super(x, y);
        this.maxPower = maxPower;
        this.currentOutput = maxPower;
    }

    public double getPower(){
        return currentOutput;
    }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics2D g2d, int tileSize){
        g2d.setColor(Color.RED);
        g2d.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}