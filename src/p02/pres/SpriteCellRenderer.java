package p02.pres;

import p02.game.Board;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class SpriteCellRenderer extends DefaultTableCellRenderer {
    private final Image player = new ImageIcon("assets/player.png").getImage();
    private final Image fish = new ImageIcon("assets/fish.png").getImage();
    private final Image turtle_up = new ImageIcon("assets/turtle_up1.png").getImage();
    private final Image turtle_down = new ImageIcon("assets/turtle_down1.png").getImage();
    private final Image water = new ImageIcon("assets/water.png").getImage();

    private final int size = 64;

    SpriteCellRenderer() {
        super();
        setOpaque(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setText("");
        label.setHorizontalAlignment(JLabel.CENTER);

        int cell_val = (int) value;
        switch (cell_val) {
            case Board.player:
                label.setIcon(new ImageIcon(player.getScaledInstance(size, size, Image.SCALE_SMOOTH)));
                break;
            case Board.fish:
                label.setIcon(new ImageIcon(fish.getScaledInstance(size, size, Image.SCALE_SMOOTH)));
                break;
            case Board.turtle_up:
                label.setIcon(new ImageIcon(turtle_up.getScaledInstance(size, size, Image.SCALE_SMOOTH)));
                break;
            case Board.turtle_down:
                label.setIcon(new ImageIcon(turtle_down.getScaledInstance(size, size, Image.SCALE_SMOOTH)));
                break;
            case Board.water:
                label.setIcon(new ImageIcon(water.getScaledInstance(size, size, Image.SCALE_SMOOTH)));
                break;
            default:
                label.setIcon(null);
                label.setBackground(Color.WHITE);
                label.setOpaque(true);
        }
        return label;
    }
}
