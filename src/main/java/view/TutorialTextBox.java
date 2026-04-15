package view;

import java.awt.*;

public class TutorialTextBox extends TextBox{
    public TutorialTextBox(String text){
        super(50, 50, 420, 180, text);
        visible = false;
    }

    @Override
    public void draw(Graphics2D g2d){
        if(!visible) return;

        String[] lines = text.split("\n");
        int lineHeight = g2d.getFontMetrics().getHeight();

        int maxWidth = 0;
        for(String line : lines){
            maxWidth = Math.max(maxWidth,
                    g2d.getFontMetrics().stringWidth(line));
        }

        width = maxWidth + 30;
        height = lines.length * lineHeight + 30;

        g2d.setColor(new Color(15,15,30,230));
        g2d.fillRoundRect(x, y, width, height, 20, 20);

        g2d.setColor(Color.WHITE);

        for(int i = 0; i < lines.length; i++){
            g2d.drawString(lines[i], x + 15, y + 25 + i * lineHeight);
        }
    }
}