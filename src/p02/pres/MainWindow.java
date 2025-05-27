package p02.pres;

import p02.game.Board;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    public MainWindow() {
        Board board = new Board();
        addKeyListener(board);

        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new OverlayLayout(overlayPanel));
        overlayPanel.setPreferredSize(new Dimension(800, 600));

        setTitle("S32909 Project");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BackgroundPanel bgPanel = new BackgroundPanel();

        SpriteTable sprite_table = new SpriteTable(board);
        sprite_table.setOpaque(false);
        sprite_table.setRowHeight(64);
        sprite_table.setAutoResizeMode(0);

        board.addListener(sprite_table);

        overlayPanel.add(sprite_table);
        overlayPanel.add(bgPanel);

        add(overlayPanel, BorderLayout.CENTER);

        setFocusable(true);
        setVisible(true);
    }
}
