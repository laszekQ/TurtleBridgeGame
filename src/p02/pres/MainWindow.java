package p02.pres;

import p02.game.Board;
import p02.game.events.GameEventListener;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    public MainWindow(Board board) {
        setTitle("S32909 Project");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BackgroundPanel panel = new BackgroundPanel();
        panel.setSize(800, 600);
        add(panel, BorderLayout.CENTER);

        JTable table = new JTable(new SpriteTable(board));
        table.setDefaultRenderer(Object.class, new SpriteCellRenderer());
        table.setEnabled(true);
        table.setTableHeader(null);
        table.setRowHeight(64);
        //add(table, BorderLayout.CENTER);

        addKeyListener(board);

        setFocusable(true);
        setVisible(true);
    }
}
