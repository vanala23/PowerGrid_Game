package view;

import java.awt.*;

public class InfoTextBox extends TextBox{
    public InfoTextBox(String text){
        super(495, 20, 300, 120, text);
        visible = false;
    }


    @Override
    public void draw(Graphics2D g2d){
        if(!visible) return;

        String[] lines = text.split("\n");
        int lineHeight = g2d.getFontMetrics().getHeight();

        int maxWidth = 0;
        for(String line : lines){
            maxWidth = Math.max(maxWidth, g2d.getFontMetrics().stringWidth(line));
        }

        width = maxWidth + 20;
        height = lines.length * lineHeight + 20;

        g2d.setColor(new Color(20,20,20,220));
        g2d.fillRoundRect(x, y, width, height, 15, 15);

        g2d.setColor(Color.WHITE);

        for(int i = 0; i < lines.length; i++){
            g2d.drawString(lines[i], x + 10, y + 20 + i * lineHeight);
        }
    }
}