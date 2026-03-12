package view;

import controller.BaseGame;
import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Log
public class PaintArea extends JPanel{
    private BaseGame model;

    public PaintArea(BaseGame model){
        this.model = model;

        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                model.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                model.mouseDown(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                model.mouseRelease(e);
            }

            @Override
            public void mouseEntered(MouseEvent e){}

            @Override
            public void mouseExited(MouseEvent e){}
        });

        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){}

            @Override
            public void keyPressed(KeyEvent e) {
                model.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e){}
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        model.draw();
    }
}
