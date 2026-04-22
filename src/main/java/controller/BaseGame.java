package controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Slf4j
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
    abstract public void draw(Graphics2D g2d);
    abstract public void mouseClicked(MouseEvent e);
    abstract public void mouseDown(MouseEvent e);
    abstract public void mouseRelease(MouseEvent e);
    abstract public void mouseMoved(MouseEvent e);
    abstract public void mouseDragged(MouseEvent e);
    abstract public void keyPressed(KeyEvent e);
}