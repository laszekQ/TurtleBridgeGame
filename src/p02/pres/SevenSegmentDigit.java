package p02.pres;
import p02.game.events.*;

import javax.swing.*;
import java.awt.*;

public class SevenSegmentDigit extends JPanel implements GameEventListener {
    private int value;
    SevenSegmentDigit next;

    SevenSegmentDigit() {
        value = 0;
        next = null;
    }

    SevenSegmentDigit(SevenSegmentDigit next) {
        value = 0;
        this.next = next;
    }

    @Override
    public void handleStartEvent(StartEvent e) {
        value = 0;
    }

    @Override
    public void handleResetEvent(ResetEvent e) {

    }

    @Override public void handleTickEvent(TickEvent e){}

    public void handlePlusOneEvent(PlusOneEvent e) {
        value += 1;
        if(value == 10){
            value = 0;
            repaint();
            next.handlePlusOneEvent(new PlusOneEvent(e));
        }
    }


    @Override
    public void paintComponent(Graphics g){}
}
