package model.energy;

import model.GridObject;

import java.awt.*;

public class PowerPlant extends GridObject{
    private double maxPower; // MegaWatt
    private double currentOutput;

    private final String text = "\nProduction: " + maxPower + " MW\nEfficiency: 100%";

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

    @Override
    public String getInfoText(){
        return text;
    }

    @Override
    public String getInfoTextBoxText(){
        return """
               POWER PLANT
               Output: 100 MW
               
               Converts energy into electricity.
               """;
    }

    @Override
    public String getTutorialText(){
        return """
               POWER PLANT
                
               Converts energy into electricity.
                
               Examples:
               Wind -> electrical
               Coal -> thermal -> electrical
               Nuclear -> heat -> electrical
               """;
    }
}