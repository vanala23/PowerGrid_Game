package controller;

import lombok.extern.slf4j.Slf4j;
import model.*;
import model.energy.PowerLine;
import model.energy.PowerPlant;
import model.energy.PowerPole;
import model.energy.Transformer;
import view.HoverTextBox;
import view.InfoTextBox;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

@Slf4j
public class Game extends BaseGame{
    private final int gridWidth = 20, gridHeight = 20;
    private final int tileSize = 32;

    private GridObject hoveredObject;
    private HoverTextBox hoverTextBox;
    private InfoTextBox infoTextBox;

    private Grid grid;

    private enum BuildMode { POWER_PLANT, TRANSFORMER, HOUSE, POWER_POLE, POWER_LINE, DELETE, NONE }
    private BuildMode buildMode = BuildMode.POWER_POLE;
    private GridObject firstSelectedObject = null;

    private Random rand = new Random();

    public Game(){
        super();
        grid = new Grid(gridWidth, gridHeight, tileSize);

        grid.addObject(new PowerPlant(1, 1, 100));
        grid.addObject(new House(5, 5));
        grid.addObject(new House(6, 5));

        hoverTextBox = new HoverTextBox("");
        hoverTextBox.setVisible(false);
        infoTextBox = new InfoTextBox("");
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
        if(hoverTextBox != null && !hoverTextBox.getClass().getSimpleName().equals("PowerLine"))
            hoverTextBox.draw(g2d);

        if(infoTextBox != null && (!infoTextBox.getClass().getSimpleName().equals("PowerLine") || !infoTextBox.getClass().getSimpleName().equals("PowerPole")))
            infoTextBox.draw(g2d);
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

                            try{
                                GridObject placed = new PowerLine(firstSelectedObject, objAtPos);
                                grid.addObject(placed);
                                showTutorialIfFirst(placed);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        firstSelectedObject = null;
                    }
                }
                break;

            case POWER_PLANT: if(objAtPos == null) grid.addObject(new PowerPlant(gx, gy, 100));
            case TRANSFORMER: if(objAtPos == null) grid.addObject(new Transformer(gx, gy));
            case HOUSE: if(objAtPos == null) grid.addObject(new House(gx, gy));
            case NONE:
                GridObject clickedObject = grid.getObjectAt(gx, gy);

                if(clickedObject != null){
                    infoTextBox.text = clickedObject.getInfoText();
                    if(infoTextBox.isVisible()){
                        infoTextBox.setVisible(false);
                    }else{
                        infoTextBox.setVisible(true);
                    }
            }

            case POWER_PLANT -> {
                if(objAtPos == null){
                    GridObject placed = new PowerPlant(gx, gy, 100);
                    grid.addObject(placed);
                    showTutorialIfFirst(placed);
                }
            }

            case TRANSFORMER -> {
                if(objAtPos == null){
                    GridObject placed = new Transformer(gx, gy);
                    grid.addObject(placed);
                    showTutorialIfFirst(placed);
                }
            }

            case HOUSE -> {
                if(objAtPos == null){
                    GridObject placed = new House(gx, gy);
                    grid.addObject(placed);
                    showTutorialIfFirst(placed);
                }
            }

            case DELETE ->{
                if(objAtPos != null) grid.deleteObjectAt(gx, gy);
            }


                case NONE -> {
                selectedObject = grid.getObjectAt(gx, gy);

                if(selectedObject != null && canShowInfo(selectedObject)){

                    infoTextBox.text = selectedObject.getInfoText();
                    infoTextBox.setVisible(!infoTextBox.isVisible());
                }else{
                    infoTextBox.setVisible(false);
                    selectedObject = null;
                }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        int gx = e.getX() / tileSize;
        int gy = e.getY() / tileSize;

        hoveredObject = grid.getObjectAt(gx, gy);

        if(hoveredObject != null){
            hoverTextBox.text = hoveredObject.getClass().getSimpleName() + "\n" + hoveredObject.getInfoText();
            hoverTextBox.setVisible(true);
            hoverTextBox.updatePosition(e.getX(), e.getY());
        }else{
            hoverTextBox.setVisible(false);
            /*
            case POWER_PLANT: if(objAtPos == null) grid.addObject(new PowerPlant(gx, gy, 100));
            case TRANSFORMER: if(objAtPos == null) grid.addObject(new Transformer(gx, gy));
            case HOUSE: if(objAtPos == null) grid.addObject(new House(gx, gy));
            case DELETE: if(objAtPos != null) grid.deleteObjectAt(gx, gy);

             */
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){

    }

    @Override
    public void mouseDown(MouseEvent e){}

    @Override
    public void mouseRelease(MouseEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_1 -> {buildMode = BuildMode.POWER_POLE; log.info("Build Power Pole");}
            case KeyEvent.VK_2 -> {buildMode = BuildMode.POWER_LINE; log.info("Build Power Line");}
            case KeyEvent.VK_3 -> {buildMode = BuildMode.POWER_PLANT; log.info("Build Power Plant");}
            case KeyEvent.VK_4 -> {buildMode = BuildMode.TRANSFORMER; log.info("Build Transformer");}
            case KeyEvent.VK_5 -> {buildMode = BuildMode.HOUSE; log.info("Build House");}
            case KeyEvent.VK_6 -> {buildMode = BuildMode.DELETE; log.info("Build Delete");}
            case KeyEvent.VK_7 -> {buildMode = BuildMode.NONE; log.info("NONE");}
        }
    }
}