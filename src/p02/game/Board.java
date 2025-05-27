package p02.game;

import p02.game.events.GameEventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.*;
import java.util.Random;

public class Board implements KeyListener {
    private final GameThread gameThread = GameThread.getInstance(this);
    private final ArrayList<GameEventListener> listeners = new ArrayList<>();
    private GameEventListener first_digit = null;

    public static final int x = 12;
    public static final int y = 5;

    private boolean running = false;
    private final int[][] board; // f - fish, t - turtle, w - water
    public static final int TURTLE_UP = 't';
    public static final int TURTLE_DOWN = 'd';
    public static final int FISH = 'f';
    public static final int WATER = 'w';
    public static final int PLAYER = 'p';

    private int p_pos_x;
    private int p_pos_y;
    private boolean supply = true;
    private int last_key = -1;

    public Board() {
        board = new int[x][y];
        p_pos_x = 0;
        p_pos_y = 0;
        for(int i = 1; i < x - 1; i += 2)
            board[i][2] = TURTLE_DOWN;
        board[1][1] = PLAYER;
    }

    public boolean isRunning() {
        return running;
    }

    public ArrayList<GameEventListener> getListeners() {
        return listeners;
    }

    public void addEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void setFirstDigit(GameEventListener first_digit) {
        this.first_digit = first_digit;
    }

    public GameEventListener getFirstDigit() {
        return first_digit;
    }

    public void addSupply(){
        supply = true;
    }

    public boolean isSupplied(){
        return supply;
    }


    public int[][] getBoard() {
        return board;
    }

    public Position getPlayerPos() {
        return new Position(p_pos_x, p_pos_y);
    }
    public void setPlayerPos(int x, int y) {
        p_pos_x = x;
        p_pos_y = y;
    }
    public void setPlayerPos(Position pos) {
        p_pos_x = pos.x;
        p_pos_y = pos.y;
    }

    public int getLastKey() {
        return last_key;
    }

    public void spawnFish() {
        LinkedList<Integer> free_cells = new LinkedList<>();
        for(int i = 0; i < x; i++)
            if(board[i][y - 1] == 'e')
                free_cells.add(i);
        int nf_pos_x = new Random().nextInt(free_cells.size());
        board[nf_pos_x][y - 1] = 'f';
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                if(p_pos_x > 0) {
                    p_pos_x--;
                    p_pos_y--;
                }
                break;
            case KeyEvent.VK_D:
                if(p_pos_x < x - 1) {
                    p_pos_x++;
                    p_pos_y--;
                }
                break;
        }
        last_key = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_S:
                if(!running) {
                    running = true;
                    gameThread.startOrResume();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    static class Position{
        public int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
