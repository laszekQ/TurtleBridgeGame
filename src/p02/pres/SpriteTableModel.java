package p02.pres;

import p02.game.Board;

import javax.swing.table.AbstractTableModel;

public class SpriteTableModel extends AbstractTableModel {
    private final Board board;

    public SpriteTableModel(Board board) {
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
}
