package controller;

import lombok.extern.slf4j.Slf4j;
import model.*;
import model.energy.PowerPlant;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

@Slf4j
public class Game extends BaseGame{
    private final int gridWidth = 20, gridHeight = 20;
    private final int tileSize = 32;

    private Grid grid;

    private enum BuildMode { POWER_PLANT, TRANSFORMER, HOUSE, POWER_POLE, POWER_LINE }
    private BuildMode buildMode = BuildMode.POWER_POLE;
    private GridObject firstSelectedObject = null;

    private Random rand = new Random();

    public Game(){
        super();
        grid = new Grid(gridWidth, gridHeight, tileSize);

        grid.addObject(new PowerPlant(1, 1, 100));
        grid.addObject(new House(5, 5));
        grid.addObject(new House(6, 5));
    }

    @Override
    public void update(){
        grid.update();
        grid.updatePower();
    }

    @Override
    public void draw(Graphics2D g2d){
        for(int y = 0; y < gridHeight; y++){
            for(int x = 0; x < gridWidth; x++){
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }

        grid.drawAll(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int gx = e.getX() / tileSize;
        int gy = e.getY() / tileSize;
        if(gx < 0 || gy < 0 || gx >= gridWidth || gy >= gridHeight) return;

        GridObject objAtPos = grid.getObjectAt(gx, gy);

        switch(buildMode){
            case POWER_POLE:
                if(objAtPos == null) grid.addObject(new PowerPole(gx, gy));
                break;

            case POWER_LINE:
                if(objAtPos != null){
                    if(firstSelectedObject == null){
                        firstSelectedObject = objAtPos;
                    }else{
                        if(firstSelectedObject != objAtPos){
                            grid.addObject(new PowerLine(firstSelectedObject, objAtPos));
                        }
                        firstSelectedObject = null;
                    }
                }
                break;

            case POWER_PLANT: if(objAtPos == null) grid.addObject(new PowerPlant(gx, gy, 100));
            case TRANSFORMER: if(objAtPos == null) grid.addObject(new Transformer(gx, gy));
            case HOUSE: if(objAtPos == null) grid.addObject(new House(gx, gy));
        }
    }

    @Override
    public void mouseDown(MouseEvent e){}

    @Override
    public void mouseRelease(MouseEvent e){}

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_1 -> {buildMode = BuildMode.POWER_POLE; log.info("Build Power Pole");}
            case KeyEvent.VK_2 -> {buildMode = BuildMode.POWER_LINE; log.info("Build Power Line");}
            case KeyEvent.VK_3 -> {buildMode = BuildMode.POWER_PLANT; log.info("Build Power Plant");}
            case KeyEvent.VK_4 -> {buildMode = BuildMode.TRANSFORMER; log.info("Build Transformer");}
            case KeyEvent.VK_5 -> {buildMode = BuildMode.HOUSE; log.info("Build House");}
        }
    }
}