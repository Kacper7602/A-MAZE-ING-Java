import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Maze extends JFrame {
    private JPanel buttonPanel;
    private JPanel fileNamePanel;
    private MazePanel mazePanel;
    private JButton loadButton;
    private JButton findPathButton;
    private JFileChooser fileChooser;
    private char[][] maze;

    public Maze() {
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
        
        JLabel infoLabel = new JLabel("[info]", SwingConstants.CENTER);
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
        JLabel fileNameLabel = new JLabel("Read maze from txt or bin file");
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
                int result = fileChooser.showOpenDialog(Maze.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    fileNameLabel.setText(fileName);
                    maze = FileHandler.readMazeFromFile(selectedFile);
                    mazePanel.setMaze(maze);
                    mazePanel.repaint();
                }
            }
        });
        
        loadButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Kod, który zostanie uruchomiony, gdy kursor wejdzie na przycisk
            	infoLabel.setText("Wczytaj labirynt z pliku tekstowego lub binarnego.");
                loadButton.setBackground(Color.GREEN); // Przykładowa akcja: zmiana koloru przycisku
            }

            public void mouseExited(MouseEvent e) {
                // Kod, który zostanie uruchomiony, gdy kursor opuści przycisk
                infoLabel.setText("...");
                loadButton.setBackground(Color.BLUE); // Przykładowa akcja: przywrócenie koloru przycisku
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
        
        findPathButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Kod, który zostanie uruchomiony, gdy kursor wejdzie na przycisk
            	infoLabel.setText("Znajdz najkrótszą ścięzkę w labiryncie!");
                findPathButton.setBackground(Color.GREEN); // Przykładowa akcja: zmiana koloru przycisku
            }

            public void mouseExited(MouseEvent e) {
                // Kod, który zostanie uruchomiony, gdy kursor opuści przycisk
                infoLabel.setText("...");
                findPathButton.setBackground(Color.RED); // Przykładowa akcja: przywrócenie koloru przycisku
            }
        });

        mazePanel = new MazePanel();
        mazePanel.setBackground(Color.GRAY);
        mazePanel.setPreferredSize(new Dimension(600, 400));

        JScrollPane scrollPane = new JScrollPane(mazePanel);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
