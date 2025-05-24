package p02.game;

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
        //instance = new GameThread(board);
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
            try {
                sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
