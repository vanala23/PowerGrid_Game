package model;

import java.awt.*;

public class PowerLine extends GridObject{
    private GridObject start, end;

    public PowerLine(GridObject start, GridObject end){
        super(-1,-1);
        this.start = start;
        this.end = end;

        this.start.addConnection(end);
        this.end.addConnection(start);
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