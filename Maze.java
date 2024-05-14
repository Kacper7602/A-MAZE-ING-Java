import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze extends JFrame {
    private JPanel buttonPanel;
    private JPanel fileNamePanel;
    private JPanel mazePanel;
    private JButton uploadButton;
    private JButton findPathButton;
    private JFileChooser fileChooser;
    private char[][] maze;

    public Maze() {
        setTitle("A-MAZE-ING Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);

        JLabel headerLabel = new JLabel("A-MAZE-ING Solver", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(250, getHeight()));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        fileNamePanel = new JPanel();
        fileNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileNamePanel.setMaximumSize(new Dimension(235, 30));
        JLabel fileNameLabel = new JLabel("Read maze from txt or bin file");
        fileNameLabel.setForeground(Color.WHITE);
        fileNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        fileNamePanel.add(fileNameLabel);
        buttonPanel.add(fileNamePanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        uploadButton = new JButton("Upload file");
        uploadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadButton.setPreferredSize(new Dimension(180, 40));
        uploadButton.setFont(new Font("Arial", Font.BOLD, 14));
        uploadButton.setForeground(Color.WHITE);
        uploadButton.setBackground(Color.BLUE);
        buttonPanel.add(uploadButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text and Binary Files", "txt", "bin"));
                int result = fileChooser.showOpenDialog(Maze.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    fileNameLabel.setText(fileName);
                    readMazeFromFile(selectedFile);
                    mazePanel.repaint();
                }
            }
        });

        findPathButton = new JButton("Find shortest path");
        findPathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        findPathButton.setPreferredSize(new Dimension(150, 40));
        findPathButton.setFont(new Font("Arial", Font.BOLD, 14));
        findPathButton.setForeground(Color.WHITE);
        findPathButton.setBackground(Color.RED);
        buttonPanel.add(findPathButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        add(buttonPanel, BorderLayout.WEST);

        mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (maze != null) {
                    int blockSize = 30;
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
        };
        mazePanel.setBackground(Color.GRAY);

        mazePanel.setPreferredSize(new Dimension(600, 400));

        JScrollPane scrollPane = new JScrollPane(mazePanel);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void readMazeFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            int rows = sb.toString().split("\n").length;
            maze = new char[rows][];
            int i = 0;
            for (String l : sb.toString().split("\n")) {
                maze[i++] = l.toCharArray();
            }
            mazePanel.setPreferredSize(new Dimension(maze[0].length * 30, rows * 30));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Maze();
            }
        });
    }
}
