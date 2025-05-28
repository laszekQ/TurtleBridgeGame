package p02.game;

import p02.game.events.*;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameThread extends Thread implements GameEventListener {
    private static GameThread instance;
    private Board board;
    private final int base_sleeptime = 1000;
    private int sleeptime = base_sleeptime;
    private final List<GameEventListener> tick_listeners = new ArrayList<>();
    private GameEventListener d_digit = null;

    private GameThread(Board b) {
        board = b;
    }

    public static GameThread getInstance(Board b) {
        if (instance == null) {
            instance = new GameThread(b);
        }
        return instance;
    }

    public void addListener(GameEventListener l) {
        tick_listeners.add(l);
    }
    public void linkDigit(GameEventListener l) {
        d_digit = l;
    }

    public void notifyListeners() {
        for (GameEventListener l : tick_listeners) {
            l.handleTickEvent(new TickEvent(this));
        }
    }

    public void resetSleep() {
        sleeptime = base_sleeptime;
    }

    private void spawnFish(int[][] b){
        ArrayList<Integer> free_pos = new ArrayList<>();
        for(int i = 2; i <= 10; i += 2){
            if(b[i][4] == Board.water){
                free_pos.add(i);
            }
        }
        int new_pos = free_pos.get(new Random().nextInt(free_pos.size()));
        board.addFish(new_pos);
    }

    @Override
    public void run() {
        while(board.isRunning()) {
            int[][] board_copy = board.getBoard();

            for(int i = 2; i <= 10; i += 2){
                if(board_copy[i][4] == Board.fish && board_copy[i][3] == Board.water){
                    board_copy[i][4] = Board.water;
                    board_copy[i][3] = Board.fish;
                }
            }

            if(new Random().nextDouble() < 0.3) {
                spawnFish(board_copy);
            }

            for(int i = 2; i <= 10; i += 2){
                if(board_copy[i][3] == Board.turtle_down){
                    board_copy[i][3] = Board.water;
                    board_copy[i][2] = Board.turtle_up;
                }

                if(board_copy[i][3] == Board.fish && board_copy[i][2] == Board.turtle_down){
                    board_copy[i][3] = Board.turtle_down;
                    board_copy[i][2] = Board.empty;
                }

                if(board_copy[i][2] == Board.turtle_down && new Random().nextDouble() < 0.3)
                    board_copy[i][2] = Board.turtle_up;
                else if(board_copy[i][2] == Board.turtle_up && new Random().nextDouble() < 0.3)
                    board_copy[i][2] = Board.turtle_down;
            }

            int player_pos_x = board.get_pos_x();
            int player_pos_y = board.get_pos_y();
            int cell_under = board_copy[player_pos_x][player_pos_y + 1];
            int last_key = board.get_last_key();

            board_copy[player_pos_x][player_pos_y] = Board.empty;

            if(player_pos_x % 2 != 0) {
                switch(last_key) {
                    case KeyEvent.VK_A:
                        if(player_pos_x != 1){
                            player_pos_x--;
                            player_pos_y++;
                        }
                        else{
                            player_pos_x--;
                        }
                        break;
                    case KeyEvent.VK_D:
                        if(player_pos_x != 11){
                            player_pos_x++;
                            player_pos_y++;
                        }
                        break;
                }
            }

            if(player_pos_x == 0 && player_pos_y == 0 && board.isSupplierAppeared()){
                board.supplyPlayer();
            }
            else if(player_pos_x == 11 && player_pos_y == 0 && board.isReceiverAppeared() && board.isSupplied()){
                //trigger PlusOneEvent
                board.supplyReceiver();
                d_digit.handlePlusOneEvent(new PlusOneEvent(this));
            }

            if(!board.isReceiverAppeared() && new Random().nextDouble() < 0.4)
                board.turnReceiver(true);
            else if(new Random().nextDouble() < 0.4)
                board.turnReceiver(false);

            if(!board.isSupplierAppeared() && new Random().nextDouble() < 0.4)
                board.turnSupplier(true);
            else if(new Random().nextDouble() < 0.4)
                board.turnSupplier(false);


            board.changeBoard(board_copy);
            board.updatePlayerPos(player_pos_x, player_pos_y);

            notifyListeners();

            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(sleeptime > 260)
                sleeptime -= 1;
        }
    }

    @Override
    public void handleStartEvent(StartEvent e) {
        if(!isAlive())
            start();
    }

    @Override
    public void handleResetEvent(ResetEvent e) {

    }

    @Override
    public void handleTickEvent(TickEvent e) {

    }

    @Override
    public void handlePlusOneEvent(PlusOneEvent e) {
        this.interrupt();
    }
}
