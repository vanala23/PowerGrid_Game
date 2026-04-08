package view;

import java.awt.*;

public class HoverTextBox extends TextBox{
    public HoverTextBox(String text){
        super(0, 0, 180, 60, text);
    }

    public void updatePosition(int mouseX, int mouseY){
        this.x = mouseX + 10;
        this.y = mouseY + 10;
    }

    @Override
    public void draw(Graphics2D g2d){
        if(!visible) return;

        g2d.setColor(new Color(30,30,30,200));
        g2d.fillRoundRect(x, y, width, height, 10, 10);

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x + 10, y + 20);
    }
}