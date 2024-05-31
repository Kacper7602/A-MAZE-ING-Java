import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public static int[][] readMazeFromFile(File file) {
        int[][] maze = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String[] lines = sb.toString().split("\n");
            maze = new int[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                maze[i] = new int[lines[i].length()];
                for (int j = 0; j < lines[i].length(); j++) {
                    char c = lines[i].charAt(j);
                    if (c == 'X') maze[i][j] = 1;
                    else if (c == 'P') maze[i][j] = 10;
                    else if (c == 'K') maze[i][j] = 11;
                    else maze[i][j] = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }
}
