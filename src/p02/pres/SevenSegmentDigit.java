package p02.pres;
import p02.game.events.*;
import javax.swing.*;
import java.awt.*;

public class SevenSegmentDigit extends JPanel implements GameEventListener {

    private int value = 0;
    GameEventListener nextListener;
    private final boolean[][] segment_layout = {
            { true, true, true, true, true, true, false },
            { false, true, true, false, false, false, false },
            { true, true, false, true, true, false, true },
            { true, true, true, true, false, false, true },
            { false, true, true, false, false, true, true },
            { true, false, true, true, false, true, true },
            { true, false, true, true, true, true, true },
            { true, true, true, false, false, false, false },
            { true, true, true, true, true, true, true },
            { true, true, true, true, false, true, true },
            { false, false, false, false, false, false, false },
    };
            // A    B     C     D     E      F     G

    SevenSegmentDigit(GameEventListener nextListener) {
        super();
        this.nextListener = nextListener;
        setBackground(null);
        setOpaque(false);
        setPreferredSize(new Dimension(25, 50));
    }

    public int notZero() {
        int sum = (value != 0) ? 1 : 0;
        if(nextListener.getClass() == SevenSegmentDigit.class) {
            sum += ((SevenSegmentDigit) nextListener).notZero();
        }
        return sum;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);

        int thickness = 2;
        g2d.setStroke(new BasicStroke(thickness));

        boolean[] to_draw = segment_layout[value];
        int width = getWidth();
        int height = getHeight();

        if (to_draw[0])
            g2d.fillRect(thickness, 0, width - 2 * thickness, thickness);
        if (to_draw[1])
            g2d.fillRect(width - thickness, thickness, thickness, height / 2 - thickness);
        if (to_draw[2])
            g2d.fillRect(width - thickness, height / 2, thickness, height / 2 - thickness);
        if (to_draw[3])
            g2d.fillRect(thickness, height - thickness, width - 2 * thickness, thickness);
        if (to_draw[4])
            g2d.fillRect(0, height / 2, thickness, height / 2 - thickness);
        if (to_draw[5])
            g2d.fillRect(0, thickness, thickness, width - thickness);
        if (to_draw[6])
            g2d.fillRect(thickness, height / 2 - thickness / 2, width - 2 * thickness, thickness);
    }

    @Override
    public void handleStartEvent(StartEvent e) {
        value = 0;
        repaint();
        nextListener.handleStartEvent(new StartEvent(this));
    }

    @Override
    public void handleResetEvent(ResetEvent e) {
        value = 10;
        repaint();
        nextListener.handleResetEvent(new ResetEvent(this));
    }

    @Override
    public void handlePlusOneEvent(PlusOneEvent e) {
        value++;
        if (value == 10) {
            value = 0;
            nextListener.handlePlusOneEvent(new PlusOneEvent(this));
        }
        repaint();
    }

    @Override
    public void handleTickEvent(TickEvent e) {}
}
