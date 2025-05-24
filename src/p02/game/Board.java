package p02.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Random;

public class Board implements KeyListener {
    private final GameThread gameThread = GameThread.getInstance(this);

    private final int x = 12;
    private final int y = 5;

    private boolean running = false;
    private final int[][] board; // f - fish, t - turtle, w - water
    private final int turtle_up = 't';
    private final int turtle_down = 'd';
    private final int fish = 'f';
    private final int water = 'w';
    private final int player = 'p';

    private int p_pos_x;
    private int p_pos_y;

    public Board() {
        board = new int[x][y];
        p_pos_x = 0;
        p_pos_y = 0;
        for(int i = 1; i < x - 1; i += 2)
            board[i][2] = turtle_down;
    }

    public boolean isRunning() {
        return running;
    }

    private void spawnFish() {
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
}
