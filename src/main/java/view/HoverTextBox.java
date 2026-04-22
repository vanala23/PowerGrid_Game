package view;

import java.awt.*;

public class HoverTextBox extends TextBox{
    public HoverTextBox(String text){
        super(0, 0, 180, 30, text);
    }

    public void updatePosition(int mouseX, int mouseY){
        this.x = mouseX + 10;
        this.y = mouseY + 10;
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
        height = lines.length * lineHeight + 10;

        g2d.setColor(new Color(30,30,30,200));
        g2d.fillRoundRect(x, y, width, height, 10, 10);

        g2d.setColor(Color.WHITE);

        for(int i = 0; i < lines.length; i++){
            g2d.drawString(lines[i], x + 10, y + 20 + i * lineHeight);
        }
    }
}