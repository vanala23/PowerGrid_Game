package model;

import java.awt.*;

public class PowerPole extends GridObject{
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
}