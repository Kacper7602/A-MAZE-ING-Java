import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private char[][] maze;

    public void setMaze(char[][] maze) {
        this.maze = maze;
        System.out.println(maze[0].length);
        System.out.println(maze.length);
        setPreferredSize(new Dimension(maze[0].length * 30, maze.length * 30));
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (maze != null) {
            int blockSize = 10;
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    Color color;
                    switch (maze[i][j]) {
                        case 'X':
                            color = Color.BLACK;
                            break;
                        case 'P':
                            color = Color.GREEN;
                            break;
                        case 'K':
                            color = Color.RED;
                            break;
                        default:
                            color = Color.WHITE;
                            break;
                    }
                    g.setColor(color);
                    g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                }
            }
        }
    }
}
