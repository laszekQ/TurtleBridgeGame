import p02.game.Board;
import p02.game.GameThread;
import p02.pres.MainWindow;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            MainWindow mw = new MainWindow();
        });
    }
}