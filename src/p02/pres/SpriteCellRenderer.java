package p02.pres;

import p02.game.Board;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpriteCellRenderer extends DefaultTableCellRenderer {
    private final int size = 64;

    private final ImageIcon player_empty = scale(new ImageIcon("assets/player_empty.png"), 1);
    private final ImageIcon player_supplied = scale(new ImageIcon("assets/player_supplied.png"), 1);
    private final ImageIcon player_getting = scale(new ImageIcon("assets/supplying.png"), 1);
    private final ImageIcon player_drowning = scale(new ImageIcon("assets/drowning.png"), 1);
    private final ImageIcon receiver = scale(new ImageIcon("assets/receiver.png"), 1);
    private final ImageIcon fish = scale(new ImageIcon("assets/fish.png"), 2);
    private final ImageIcon turtle_up1 = scale(new ImageIcon("assets/turtle_up1.png"), 1);
    private final ImageIcon turtle_up2 = scale(new ImageIcon("assets/turtle_up2.png"),1);
    private final ImageIcon turtle_down1 = scale(new ImageIcon("assets/turtle_down1.png"),1);
    private final ImageIcon turtle_down2 = scale(new ImageIcon("assets/turtle_down2.png"), 1);
    private final ImageIcon turtle_dive1 = scale(new ImageIcon("assets/turtle_dive1.png"), 1);
    private final ImageIcon turtle_dive2 = scale(new ImageIcon("assets/turtle_dive2.png"), 1);

    private final HashMap<Integer, ImageIcon> image_map = new HashMap<>();

    SpriteCellRenderer() {
        super();
        setOpaque(false);

        image_map.put(Board.player_empty, player_empty);
        image_map.put(Board.player_supplied, player_supplied);
        image_map.put(Board.player_getting, player_getting);
        image_map.put(Board.player_drowning, player_drowning);
        image_map.put(Board.receiver, receiver);
        image_map.put(Board.fish, fish);
        image_map.put(Board.turtle_up, turtle_up1);
        image_map.put(Board.turtle_down, turtle_down1);
        image_map.put(Board.turtle_dive, turtle_dive1);
    }

    private ImageIcon scale(ImageIcon img , int k){
        return new ImageIcon(img.getImage().getScaledInstance(size / k, size / k, Image.SCALE_DEFAULT));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setText("");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(false);

        Integer cell_val =  (Integer) value;
        label.setIcon(image_map.get(cell_val));

        if(cell_val == Board.turtle_up && new Random().nextDouble() < 0.3)
            label.setIcon(turtle_up2);
        if(cell_val == Board.turtle_down && new Random().nextDouble() < 0.3)
            label.setIcon(turtle_down2);
        if(cell_val == Board.turtle_dive && new Random().nextDouble() < 0.3)
            label.setIcon(turtle_dive2);

        return label;
    }
}
