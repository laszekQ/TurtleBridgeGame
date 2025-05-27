package p02.pres;

import p02.game.Board;
import p02.game.events.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class SpriteTable extends AbstractTableModel implements GameEventListener {
    private final Board board;

    public SpriteTable(Board board) {
        this.board = board;
    }

    @Override
    public int getRowCount() {
        return board.getBoard()[0].length;
    }

    @Override
    public int getColumnCount() {
        return board.getBoard().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return board.getBoard()[columnIndex][rowIndex];
    }

    public void update(){
        fireTableDataChanged();
    }

    @Override
    public void handleTickEvent(TickEvent e){
        update();
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
