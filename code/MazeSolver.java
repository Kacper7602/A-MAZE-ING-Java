import java.util.ArrayDeque;
import java.util.Queue;

public class MazeSolver {
    private int[][] maze;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean[][] visited;
    private int[][] previous;

    public MazeSolver(int[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
        this.previous = new int[maze.length][maze[0].length];
    }

    public void solve(int startRow, int startCol, int endRow, int endCol) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startRow * maze[0].length + startCol);
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int row = current / maze[0].length;
            int col = current % maze[0].length;

            if (row == endRow && col == endCol) {
                reconstructPath(startRow, startCol, endRow, endCol);
                return;
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValid(newRow, newCol)) {
                    queue.add(newRow * maze[0].length + newCol);
                    visited[newRow][newCol] = true;
                    previous[newRow][newCol] = current;
                }
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length
                && maze[row][col] != 1 && !visited[row][col];
    }

    private void reconstructPath(int startRow, int startCol, int endRow, int endCol) {
        int current = endRow * maze[0].length + endCol;
        while (current != startRow * maze[0].length + startCol) {
            int row = current / maze[0].length;
            int col = current % maze[0].length;
            maze[row][col] = 2; // Mark path with 2
            current = previous[row][col];
        }
    }

    public int[][] getMaze() {
        return maze;
    }
}
