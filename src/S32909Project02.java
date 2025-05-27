import p02.game.Board;
import p02.game.GameThread;
import p02.pres.MainWindow;
import javax.swing.*;

public class S32909Project02 extends JFrame {
    public static void main(String[] args) {
        Board board = new Board();
        MainWindow mw = new MainWindow(board);
        mw.setLocationRelativeTo(null);
    }
}