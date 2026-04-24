package model.energy;

import model.GridObject;

import java.awt.*;

public class PowerLine extends GridObject{
    private final GridObject start, end;

    public PowerLine(GridObject start, GridObject end){
        super(-1,-1);
        this.start = start;
        this.end = end;
    }

    public GridObject getStart() { return start; }
    public GridObject getEnd() { return end; }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics2D g, int tileSize){
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(start.getX() * tileSize + tileSize/2, start.getY() * tileSize + tileSize/2, end.getX() * tileSize + tileSize/2, end.getY() * tileSize + tileSize/2);
    }

    @Override
    public String getInfoText(){
        return "";
    }

    @Override
    public String getTutorialText(){
        return """
               HOUSE

               A house is an electrical consumer.

               It needs electrical energy
               from the power grid.

               Formula:
               P = U * I

               Power = Voltage × Current
               """;
    }
}