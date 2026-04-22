package model;

import java.awt.*;

public class House extends GridObject{
    private double powerDemand = 5; // MegaWatt
    private boolean powered = false;

    private final String text = "\nConsumption: 10 kWh\nStatus: Consumer";

    public House(int x, int y){
        super(x, y);
    }

    public double getDemand(){
        return powerDemand;
    }

    public void setPowered(boolean powered){ this.powered = powered; }
    public boolean isPowered(){ return powered; }

    @Override
    public void update(){
        powered = currentPower >= powerDemand;
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize){
        if(powered) g2d.setColor(Color.GREEN);
        else g2d.setColor(Color.GRAY);

        g2d.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }

    @Override
    public String getInfoText(){
        return text;
    }

    @Override
    public String getInfoTextBoxText(){
        return """
               HOUSE
               Power demand: 10 kWh
               Status: Consumer

               Electricity must be delivered
               through the power grid.
               """;
    }
}