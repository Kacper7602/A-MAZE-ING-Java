import java.util.ArrayList;
import java.util.List;

public class MazeModel {
    private int[][] maze;
    private int[] start;
    private int[] end;
    private List<MazeObserver> observers = new ArrayList<>();

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
        notifyObservers();
    }

    public int[] getStart() {
        return start;
    }

    public void setStart(int[] start) {
        this.start = start;
    }

    public int[] getEnd() {
        return end;
    }

    public void setEnd(int[] end) {
        this.end = end;
    }

    public void addObserver(MazeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MazeObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (MazeObserver observer : observers) {
            observer.updateMaze(this);
        }
    }
}
