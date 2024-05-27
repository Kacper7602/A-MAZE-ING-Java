import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public static char[][] readMazeFromFile(File file) {
        char[][] maze = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String[] lines = sb.toString().split("\n");
            maze = new char[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                maze[i] = lines[i].toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }
}
