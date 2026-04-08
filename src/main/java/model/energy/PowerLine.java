package model.energy;

import model.GridObject;

import java.awt.*;

public class PowerLine extends GridObject {
    private final GridObject start, end;

    public PowerLine(GridObject start, GridObject end) throws Exception {
        super(-1,-1);
        if (end.getY() - start.getY() > 2) {
            throw new EnergyException("Y Line too long: " + (end.getY() - start.getY()));
        }
        else if (end.getX() - start.getX() > 2) {
            throw new EnergyException("X Line too long: " + (end.getX() - start.getX()));
        }
        else if (end.getY() - start.getY() < -2) {
            throw new EnergyException("Y Line too long: " + (end.getY() - start.getY()));
        }
        else if (end.getX() - start.getX() < -2) {
            throw new EnergyException("X Line too long: " + (end.getX() - start.getX()));
        }
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
}