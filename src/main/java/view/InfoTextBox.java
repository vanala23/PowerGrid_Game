package view;

import java.awt.*;

public class InfoTextBox extends TextBox{
    public InfoTextBox(int x, int y, String text){
        super(x, y, 250, 100, text);
    }

    @Override
    public void draw(Graphics2D g2d){
        if(!visible) return;

        g2d.setColor(new Color(0,0,0,180));
        g2d.fillRoundRect(x, y, width, height, 10, 10);

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x + 10, y + 20);
    }
}