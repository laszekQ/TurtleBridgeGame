package p02.pres;
import p02.game.events.GameEventListener;
import p02.game.events.PlusOneEvent;
import p02.game.events.ResetEvent;
import p02.game.events.StartEvent;

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

    public void handlePlusOneEvent(PlusOneEvent e) {
        value += 1;
        if(value == 10){
            value = 0;
            next.handlePlusOneEvent(new PlusOneEvent(e));
        }
    }

    @Override
    public void paintComponent(Graphics g){}
}
