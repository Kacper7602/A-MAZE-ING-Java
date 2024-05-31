import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MazeSubject extends JPanel {
    private int[][] maze;

    public MazeSubject() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int blockSize = 10;
                int col = e.getX() / blockSize;
                int row = e.getY() / blockSize;
                if (maze != null && row < maze.length && col < maze[0].length) {
                    if (maze[row][col] == 1) {
                        maze[row][col] = 0; // Zamień ścianę na wolne miejsce
                    } else if (maze[row][col] == 0) {
                        maze[row][col] = 1; // Zamień wolne miejsce na ścianę
                    }
                    repaint();
                }
            }
        });
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
        setPreferredSize(new Dimension(maze[0].length * 10, maze.length * 10));
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (maze != null) {
            int blockSize = 10;
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    Color color = switch (maze[i][j]) {
                        case 1 -> Color.BLACK;
                        case 10 -> Color.GREEN;
                        case 11 -> Color.RED;
                        case 2 -> Color.BLUE; // Koloruj ścieżkę na niebiesko
                        default -> Color.WHITE;
                    };
                    g.setColor(color);
                    g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                }
            }
        }
    }
}
