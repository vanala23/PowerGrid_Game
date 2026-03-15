package controller;

import lombok.extern.java.Log;
import utils.TileType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Log
public class Game extends BaseGame{
    private final int gridWidth = 20, gridHeight = 20;
    private final int tileSize = 32;

    private TileType[][] grid = new TileType[gridHeight][gridWidth];
    private TileType buildMode = TileType.POWER_LINE;

    public Game(){
        super();

        for(int y = 0; y < gridHeight; y++){
            for(int x = 0; x < gridWidth; x++){
                grid[y][x] = TileType.EMPTY;
            }
        }
    }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics2D g2d){
        for(int y = 0; y < gridHeight; y++){
            for(int x = 0; x < gridWidth; x++){
                int px = x * tileSize;
                int py = y * tileSize;

                switch(grid[y][x]){
                    case HOUSE -> g2d.setColor(Color.YELLOW);
                    case POWER_PLANT -> g2d.setColor(Color.RED);
                    case POWER_LINE -> g2d.setColor(Color.LIGHT_GRAY);
                    case TRANSFORMER -> g2d.setColor(Color.BLUE);
                    default -> g2d.setColor(Color.DARK_GRAY);
                }

                g2d.fillRect(px, py, tileSize, tileSize);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(px, py, tileSize, tileSize);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int gx = e.getX() / tileSize;
        int gy = e.getY() / tileSize;

        if(gx < 0 || gy < 0 || gx >= gridWidth || gy >= gridHeight) return;
        grid[gy][gx] = buildMode;
    }

    @Override
    public void mouseDown(MouseEvent e){}

    @Override
    public void mouseRelease(MouseEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1) buildMode = TileType.POWER_LINE;
        if(e.getKeyCode() == KeyEvent.VK_2) buildMode = TileType.POWER_PLANT;
        if(e.getKeyCode() == KeyEvent.VK_3) buildMode = TileType.TRANSFORMER;
        if(e.getKeyCode() == KeyEvent.VK_4) buildMode = TileType.HOUSE;
    }
}