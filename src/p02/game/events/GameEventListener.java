package p02.game.events;

import java.util.EventListener;

public interface GameEventListener extends EventListener {
    abstract void handleStartEvent(StartEvent e);
    abstract void handleResetEvent(ResetEvent e);
    abstract void handleTickEvent(TickEvent e);
    void handlePlusOneEvent(PlusOneEvent e);
}
