package p02.pres;

import p02.game.Board;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class SpriteCellRenderer extends DefaultTableCellRenderer {
    private final Image player = new ImageIcon("assets/player.png").getImage();
    private final Image fish = new ImageIcon("assets/fish.png").getImage();
    private final Image turtle_up = new ImageIcon("assets/turtle_up.png").getImage();
    private final Image turtle_down = new ImageIcon("assets/turtle_down.png").getImage();
    private final Image water = new ImageIcon("assets/water.png").getImage();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setText("");
        label.setHorizontalAlignment(JLabel.CENTER);

        int cell_val = (int) value;
        switch (cell_val) {
            case Board.PLAYER:
                label.setIcon(new ImageIcon(player));
                break;
            case Board.FISH:
                label.setIcon(new ImageIcon(fish));
                break;
            case Board.TURTLE_UP:
                label.setIcon(new ImageIcon(turtle_up));
                break;
            case Board.TURTLE_DOWN:
                label.setIcon(new ImageIcon(turtle_down));
                break;
            case Board.WATER:
                label.setIcon(new ImageIcon(water));
                break;
            default:
                label.setIcon(null);
                label.setBackground(Color.WHITE);
                label.setOpaque(true);
        }
        return label;
    }
}
