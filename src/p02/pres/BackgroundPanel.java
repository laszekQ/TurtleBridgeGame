package p02.pres;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Image background;
    public BackgroundPanel() {
        background = Toolkit.getDefaultToolkit().getImage("assets/background.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
