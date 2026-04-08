package view;

import java.awt.*;

public abstract class TextBox{
    protected boolean visible;
    public int x, y, width, height;
    public String text;

    public TextBox(int x, int y, int width, int height, String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.visible = true;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean isVisible(){
        return visible;
    }

    public abstract void draw(Graphics2D g2d);
}