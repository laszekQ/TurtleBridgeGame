package p02.game;

import p02.game.events.GameEvent;
import p02.game.events.GameEventListener;
import p02.game.events.PlusOneEvent;
import p02.game.events.TickEvent;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameThread extends Thread {
    private static GameThread instance;
    private final int base_delay = 500;
    private int delay = base_delay;
    private final Board board;


    private GameThread(Board board) {
        this.board = board;
    }

    public static synchronized GameThread getInstance(Board board) {
        if(instance == null) {
            instance = new GameThread(board);
        }
        return instance;
    }

    public void startOrResume() {
        instance = new GameThread(board);
        instance.start();
    }

    public void stopThread(){
        delay = base_delay;
        interrupt();
    }

    @Override
    public void run() {
        while(board.isRunning()){
            if(delay > 5)
                delay -= 5;

            ArrayList<GameEventListener> listeners = board.getListeners();
            for(GameEventListener listener : listeners)
                listener.handleTickEvent(new TickEvent(this));

            int[][] b = board.getBoard();
            Board.Position pos = board.getPlayerPos();

            if(pos.x == 0 && pos.y == 0){
                board.addSupply();
            }
            else if(pos.x == Board.x - 1 && pos.y == 1 && board.isSupplied()){
                board.getFirstDigit().handlePlusOneEvent(new PlusOneEvent(this));
            }
            else if(pos.y % 2 != 0){
                if(board.getLastKey() == KeyEvent.VK_A){
                    pos.x -= 1;
                    pos.y += 1;
                }
                else{
                    pos.x += 1;
                    pos.y += 1;
                }
                board.setPlayerPos(pos);
            }

            try {
                sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
