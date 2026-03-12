package controller;

import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Log
public abstract class BaseGame{
    protected JPanel view;
    private Timer timer;

    public BaseGame(){
        log.info("--> basegame ctor called ");
        timer = new Timer(20, e -> tick());
    }

    public void start(JPanel view){
        this.view = view;
        log.info("basegame timer started");
        timer.start();
    }

    private void tick(){
        update();
        view.repaint();
    }

    abstract public void update();
    abstract public void draw();
    abstract public void mouseClicked(MouseEvent e);
    abstract public void mouseDown(MouseEvent e);
    abstract public void mouseRelease(MouseEvent e);
    abstract public void keyPressed(KeyEvent e);
}
