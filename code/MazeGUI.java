import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MazeGUI extends JFrame {
    private JPanel buttonPanel;
    private JPanel fileNamePanel;
    private MazeSubject mazePanel;
    private JButton loadButton;
    private JButton findPathButton;
    private JButton setEntryPointButton;
    private JButton setEndPointButton;
    private JFileChooser fileChooser;
    private int[][] maze;
    private int startRow, startCol, endRow, endCol;

    public MazeGUI() {
        setTitle("A-MAZE-ING Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("A-MAZE-ING Solver", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headerLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 7)));

        JLabel infoLabel = new JLabel("...", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(infoLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 7)));
        add(headerPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(250, getHeight()));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        fileNamePanel = new JPanel();
        fileNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileNamePanel.setMaximumSize(new Dimension(235, 30));
        JLabel fileNameLabel = new JLabel("Read maze from txt file");
        fileNameLabel.setForeground(Color.WHITE);
        fileNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        fileNamePanel.add(fileNameLabel);
        buttonPanel.add(fileNamePanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loadButton = new JButton("Load file");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setPreferredSize(new Dimension(180, 40));
        loadButton.setFont(new Font("Arial", Font.BOLD, 14));
        loadButton.setForeground(Color.WHITE);
        loadButton.setBackground(Color.BLUE);
        loadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text and Binary Files", "txt", "bin"));
                int result = fileChooser.showOpenDialog(MazeGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    fileNameLabel.setText(fileName);
                    loadMazeFromFile(selectedFile);
                }
            }
        });

        loadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoLabel.setText("Load the maze from a text or binary file.");
                loadButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoLabel.setText("...");
                loadButton.setBackground(Color.BLUE);
            }
        });

        findPathButton = new JButton("Find shortest path");
        findPathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        findPathButton.setPreferredSize(new Dimension(150, 40));
        findPathButton.setFont(new Font("Arial", Font.BOLD, 14));
        findPathButton.setForeground(Color.WHITE);
        findPathButton.setBackground(Color.RED);
        findPathButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(findPathButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        add(buttonPanel, BorderLayout.WEST);

        findPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maze != null) {
                    findShortestPath();
                } else {
                    JOptionPane.showMessageDialog(MazeGUI.this, "Please load a maze first.", "No Maze Loaded", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        findPathButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoLabel.setText("Find the shortest path in the maze!");
                findPathButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoLabel.setText("...");
                findPathButton.setBackground(Color.RED);
            }
        });
        setEntryPointButton = new JButton("Set new entry point");
        setEntryPointButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setEntryPointButton.setPreferredSize(new Dimension(150, 40));
        setEntryPointButton.setFont(new Font("Arial", Font.BOLD, 14));
        setEntryPointButton.setForeground(Color.WHITE);
        setEntryPointButton.setBackground(Color.RED);
        setEntryPointButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(setEntryPointButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        add(buttonPanel, BorderLayout.WEST);
        setEntryPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maze != null) {
                    findShortestPath();
                } else {
                    JOptionPane.showMessageDialog(MazeGUI.this, "Please load a maze first.", "No Maze Loaded", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        setEntryPointButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoLabel.setText("Choose your own starting point!");
                setEntryPointButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoLabel.setText("...");
                setEntryPointButton.setBackground(Color.RED);
            }
        });


        setEndPointButton = new JButton("Set new end point");
        setEndPointButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setEndPointButton.setPreferredSize(new Dimension(150, 40));
        setEndPointButton.setFont(new Font("Arial", Font.BOLD, 14));
        setEndPointButton.setForeground(Color.WHITE);
        setEndPointButton.setBackground(Color.RED);
        setEndPointButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(setEndPointButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        add(buttonPanel, BorderLayout.WEST);
        setEndPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maze != null) {
                    findShortestPath();
                } else {
                    JOptionPane.showMessageDialog(MazeGUI.this, "Please load a maze first.", "No Maze Loaded", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        setEndPointButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoLabel.setText("Choose your own end point!");
                setEndPointButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoLabel.setText("...");
                setEndPointButton.setBackground(Color.RED);
            }
        });
        mazePanel = new MazeSubject();
        mazePanel.setBackground(Color.GRAY);
        mazePanel.setPreferredSize(new Dimension(600, 400));

        JScrollPane scrollPane = new JScrollPane(mazePanel);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadMazeFromFile(File selectedFile) {
        maze = FileHandler.readMazeFromFile(selectedFile);
        startRow = startCol = endRow = endCol = -1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 10) {
                    startRow = i;
                    startCol = j;
                } else if (maze[i][j] == 11) {
                    endRow = i;
                    endCol = j;
                }
            }
        }
        if (startRow == -1 || startCol == -1 || endRow == -1 || endCol == -1) {
            JOptionPane.showMessageDialog(this, "Invalid maze format. Please make sure the maze contains start and end points.", "Invalid Maze", JOptionPane.ERROR_MESSAGE);
        } else {
            mazePanel.setMaze(maze);
            mazePanel.repaint();
        }
    }

    private void findShortestPath() {
        MazeSolver mazeSolver = new MazeSolver(maze);
        mazeSolver.solve(startRow, startCol, endRow, endCol);
        int[][] solvedMaze = mazeSolver.getMaze();
        mazePanel.setMaze(solvedMaze);
        mazePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MazeGUI();
            }
        });
    }
}

