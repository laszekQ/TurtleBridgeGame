package p02.game;

import p02.game.events.GameEventListener;
import p02.game.events.StartEvent;
import p02.pres.SevenSegmentDigit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.*;
import java.util.Random;

import static p02.game.GameThread.getInstance;

public class Board implements KeyListener {
    private int[][] board = new int[12][5];
    private final GameThread game_thread = getInstance(this);
    private boolean running = false;
    private int pos_x = 0, pos_y = 0;
    private boolean supply = false;
    private boolean receiver_appeared = false;
    private int last_key = 0;

    public static final int player_empty = 'p';
    public static final int player_supplied = 's';
    public static final int player_drowning = 'm';
    public static final int receiver = 'r';
    public static final int player_getting = 'g';
    public static final int turtle_up = 't';
    public static final int turtle_down = 'd';
    public static final int turtle_dive = 'v';
    public static final int water = 'w';
    public static final int fish = 'f';
    public static final int empty = 'e';
    public static final int barrier = 'b';
    private int player = player_getting;

    public Board() {
        super();
        resetBoard();
    }

    public void resetBoard() {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = empty;
            }
        }
        board[0][1] = barrier;
        board[11][1] = barrier;
        for(int i = 0; i < 12; i++) {
            for(int j = 3; j < 5; j++) {
                board[i][j] = water;
            }
        }
        board[pos_x][pos_y] = empty;
        player = player_supplied;
        board[0][0] = player;
        pos_x = 0;
        pos_y = 0;
        for(int i = 2; i <= 10; i += 2)
            board[i][2] = turtle_down;
    }

    public void addListener(GameEventListener listener) {
        game_thread.addListener(listener);
    }

    public void linkDigit(SevenSegmentDigit l){
        game_thread.linkDigit(l);
    }

    public int[][] getBoard(){
        return board;
    }

    public GameThread getThread(){
        return game_thread;
    }

    public int get_pos_x(){
        return pos_x;
    }

    public int get_pos_y(){
        return pos_y;
    }

    public int get_last_key(){
        return last_key;
    }

    public void changeBoard(int[][] new_board){
        board = new_board;
    }

    public void updatePlayerPos(int x, int y){
        board[pos_x][pos_y] = empty;
        board[x][y] = player;
        pos_x = x;
        pos_y = y;
    }

    public void addFish(int x){
        board[x][4] = fish;
    }

    public boolean isSupplied(){
        return supply;
    }

    public void supplyPlayer(){
        supply = true;
        player = player_getting;
    }

    public void supplyReceiver(){
        supply = false;
        player = player_empty;
    }

    public boolean isReceiverAppeared(){
        return receiver_appeared;
    }

    public void drownPlayer(){
        player = player_drowning;
    }

    public void turnReceiver(boolean value){
        receiver_appeared = value;
        if(value){
            if(board[11][0] != player)
                board[11][0] = receiver;
        }
        else{
            if(board[11][0] != player)
                board[11][0] = empty;
        }
    }

    public void callDefeat(){
        running = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!running)
            return;

        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                if(pos_x > 1 && pos_x < 11 && pos_y != 0)
                    updatePlayerPos(pos_x - 1, pos_y - 1);
                else if(pos_x == 11)
                    updatePlayerPos(pos_x - 1, pos_y + 1);
                last_key = e.getKeyCode();
                break;
            case KeyEvent.VK_D:
                if(pos_x <= 10 && pos_y != 0)
                    updatePlayerPos(pos_x + 1, pos_y - 1);
                else if(pos_x == 0) {
                    updatePlayerPos(1, 0);
                    player = player_supplied;
                }
                last_key = e.getKeyCode();
                break;
        }
        game_thread.notifyListeners();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_S && !running) {
            running = true;
            resetBoard();
            game_thread.handleStartEvent(new StartEvent(this));
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
}
