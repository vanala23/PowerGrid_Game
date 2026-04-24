package model.energy;

import model.GridObject;

import java.awt.*;

public class Transformer extends GridObject{
    private double efficiency;
    private int level = 1;

    private final String text = "\nVoltage conversion\nLoss: 5%";

    public Transformer(int x, int y){
        super(x, y);
        this.efficiency = 0.95;
    }

    public double transform(double input){
        return input * efficiency;
    }

    public void upgrade(){
        level++;
        efficiency += 0.01;
        if(efficiency > 0.99) efficiency = 0.99;
    }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics2D g2d, int tileSize){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }

    @Override
    public String getInfoText(){
        return text;
    }

    @Override
    public String getTutorialText(){
        return """
               TRANSFORMER
                
               Transforms voltage levels.
               
               High voltage reduces current
               and therefore energy losses.
               
               Power loss:
               P_loss = I² * R
               """;
    }
}