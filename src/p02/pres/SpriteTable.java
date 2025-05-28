package p02.pres;

import p02.game.Board;
import p02.game.events.*;

import javax.swing.*;

public class SpriteTable extends JTable implements GameEventListener {

    SpriteTable(Board board) {
        super(new SpriteTableModel(board));
        setDefaultRenderer(Object.class, new SpriteCellRenderer());
        setOpaque(false);
        setRowHeight(64);
        setShowGrid(false);
        setRowSelectionAllowed(false);
        setColumnSelectionAllowed(false);
        setCellSelectionEnabled(false);
    }

    @Override
    public void handleTickEvent(TickEvent e){
        repaint();
    }

    @Override
    public void handlePlusOneEvent(PlusOneEvent e) {

    }

    @Override
    public void handleStartEvent(StartEvent e) {

    }

    @Override
    public void handleResetEvent(ResetEvent e) {

    }
}
