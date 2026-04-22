package model;

import model.energy.PowerLine;
import model.energy.PowerPlant;

import java.awt.*;
import java.util.*;
import java.util.List;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import model.energy.Transformer;

import java.awt.*;

@Slf4j
public class Grid{
    public int width, height;
    public int tileSize;

    public List<GridObject> objects = new ArrayList<>();

    public Grid(int width, int height, int tileSize){
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
    }

    public void addObject(GridObject obj){
        this.objects.add(obj);
    }

    public GridObject getObjectAt(int x, int y){
        for(GridObject obj : objects){
            if(obj.x == x && obj.y == y) return obj;
        }
        return null;
    }

    public void deleteObjectAt(int x, int y){
        getObjectAt(x,y).isAlive = false;
        objects.removeIf(obj -> obj.x == x && obj.y == y);
    }

    public void updatePower(){

        for(GridObject obj : objects){
            obj.setPower(0);
        }

        for(GridObject obj : objects){
            if(obj instanceof House h){
                h.setPowered(false);
            }
        }

        for(GridObject obj : objects){
            if(obj instanceof PowerPlant plant){
                propagatePower(plant);
            }
        }


    }

    public Set<GridObject> findConnected(GridObject start) {
        Set<GridObject> visited = new HashSet<>();
        Queue<GridObject> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            GridObject current = queue.poll();

            for (GridObject neighbor : current.getConnections()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited;
    }

    private void propagatePower(GridObject start){
        Queue<GridObject> queue = new LinkedList<>();
        Set<GridObject> visited = new HashSet<>();

        start.setPower(((PowerPlant)start).getPower());

        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){
            GridObject current = queue.poll();

            for(GridObject neighbor : getConnectedObjects(current)){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);

                    double power = current.getPower();

                    power *= 0.98;

                    if(neighbor instanceof Transformer t){
                        double before = power;
                        power = t.transform(power);

                        log.info("Transformer at ({}, {}) transformed power: {:.2f} -> {:.2f}",
                                neighbor.getX(), neighbor.getY(), before, power);
                    }

                    neighbor.setPower(power);
                    queue.add(neighbor);

                    if(neighbor instanceof House h){
                        h.setPowered(power >= h.getDemand());
                    }
                }
            }
        }
    }

    private java.util.List<GridObject> getConnectedObjects(GridObject obj){
        java.util.List<GridObject> neighbors = new java.util.ArrayList<>();
        for(GridObject o : objects){
            if(o instanceof PowerLine line){
                if(line.getStart() == obj) neighbors.add(line.getEnd());
                if(line.getEnd() == obj) neighbors.add(line.getStart());
            }
        }
        return neighbors;
    }

    public void update(){
        for(GridObject obj : objects){
            if (!obj.isAlive()) {
                objects.remove(obj);
                break;
            }
            obj.update();
        }
    }

    public void drawAll(Graphics2D g2d){
        for(GridObject obj : objects){
            obj.draw(g2d, tileSize);
        }
    }
}