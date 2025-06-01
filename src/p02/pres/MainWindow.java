package p02.pres;

import p02.game.Board;
import p02.game.events.StartEvent;
import p02.game.events.TickEvent;

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
        SevenSegmentDigit d00 = new SevenSegmentDigit(board.getThread());
        SevenSegmentDigit d0 = new SevenSegmentDigit(d00);
        SevenSegmentDigit d = new SevenSegmentDigit(d0);

        board.addListener(sprite_table);
        board.addListener(d);
        board.addListener(d0);
        board.addListener(d00);
        board.linkDigit(d);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.setOpaque(false);
        scorePanel.add(d00);
        scorePanel.add(d0);
        scorePanel.add(d);

        overlayPanel.add(scorePanel, BorderLayout.NORTH);
        overlayPanel.add(sprite_table);
        overlayPanel.add(bgPanel);

        add(overlayPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }
}
