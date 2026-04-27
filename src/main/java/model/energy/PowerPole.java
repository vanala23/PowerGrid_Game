package model.energy;

import model.GridObject;

import java.awt.*;

public class PowerPole extends GridObject{
    private final String text = "\nTransfers electricity";

    public PowerPole(int x, int y){
        super(x, y);
    }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics2D g, int tileSize){
        g.setColor(Color.ORANGE);
        g.fillRect(x * tileSize + tileSize/4, y * tileSize + tileSize/4, tileSize/2, tileSize/2);
    }

    @Override
    public String getInfoText(){
        return text;
    }

    @Override
    public String getTutorialText(){
        return """
               POWER POLE
                
               Transfers electricity
               across the power grid.
                
               Used to connect consumers
               and transformers.
               """;
    }
}