import controller.BaseGame;
import controller.Game;
import view.PaintArea;

import javax.swing.*;

public class App{
    public static void main(String[] args){
        System.setProperty("sun.java2d.opengl", "true");

        BaseGame model = new Game();
        PaintArea view = new PaintArea(model);
        view.setFocusable(true);

        JFrame frame = new JFrame();
        frame.setTitle("Power Game");
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(view);

        frame.setVisible(true);
        model.start(view);
    }
}
