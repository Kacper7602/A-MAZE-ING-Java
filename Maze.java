import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maze extends JFrame {
    private JPanel mazePanel;
    private JButton loadTextButton;
    private JButton loadBinaryButton;
    private JButton findPathButton;
    private JButton choosePathButton;
    private JTextField txtFileNameField;
    private JTextField binFileNameField;

    public Maze() {
        setTitle("A-MAZE-ING Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);

        JLabel headerLabel = new JLabel("A-MAZE-ING Solver", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(250, getHeight()));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        txtFileNameField = new JTextField("name of txt file");
        txtFileNameField.setMaximumSize(new Dimension(180, 30));
        txtFileNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        buttonPanel.add(txtFileNameField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loadTextButton = new JButton("Load txt");
        loadTextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadTextButton.setPreferredSize(new Dimension(80, 40));
        loadTextButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10, true));
        loadTextButton.setBackground(Color.BLUE);
        loadTextButton.setForeground(Color.WHITE);
        loadTextButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(loadTextButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        binFileNameField = new JTextField("name of bin file");
        binFileNameField.setMaximumSize(new Dimension(180, 30));
        binFileNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        buttonPanel.add(binFileNameField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loadBinaryButton = new JButton("Load Binary");
        loadBinaryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadBinaryButton.setPreferredSize(new Dimension(80, 40));
        loadBinaryButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10, true));
        loadBinaryButton.setBackground(Color.GREEN);
        loadBinaryButton.setForeground(Color.WHITE);
        loadBinaryButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(loadBinaryButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        findPathButton = new JButton("Find shortest path");
        findPathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        findPathButton.setPreferredSize(new Dimension(80, 40));
        findPathButton.setBorder(BorderFactory.createLineBorder(Color.RED, 10, true));
        findPathButton.setBackground(Color.RED);
        findPathButton.setFont(new Font("Arial", Font.BOLD, 14));
        findPathButton.setForeground(Color.WHITE);
        buttonPanel.add(findPathButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,30))); 
        
        choosePathButton = new JButton("Change start-end points");
        choosePathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        choosePathButton.setPreferredSize(new Dimension(80, 40));
        choosePathButton.setBorder(BorderFactory.createLineBorder(Color.RED, 10, true));
        choosePathButton.setBackground(Color.RED);
        choosePathButton.setFont(new Font("Arial", Font.BOLD, 14));
        choosePathButton.setForeground(Color.WHITE);
        buttonPanel.add(choosePathButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,30))); 

        
        add(buttonPanel, BorderLayout.WEST);

        mazePanel = new JPanel();
        mazePanel.setBackground(Color.GRAY); 
        mazePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mazePanel, BorderLayout.CENTER);

        loadTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Maze.this, "Wczytano plik tekstowy: \"" + txtFileNameField.getText() + ".txt\"");
                drawMaze();
            }
        });

        loadBinaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Maze.this, "Wczytano plik binarny: \"" + binFileNameField.getText() + ".bin\"");
                drawMaze();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void drawMaze() {
        int blockSize = 50;
        int[][] maze = {
        	    {1, 10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        	    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        	    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        	    {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
        	    {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
        	    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        	    {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
        	    {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
        	    {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        	    {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
        	    {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
        	    {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
        	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 1},
        	};


        mazePanel.removeAll();
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                JPanel block = new JPanel();
                if(maze[i][j] == 0) {
                	block.setBackground(Color.WHITE);
                }
                else if(maze[i][j] == 1) {
                	block.setBackground(Color.BLACK);
                }
                else if(maze[i][j] == 10) {
                	block.setBackground(Color.RED);
                	JLabel labelStart = new JLabel("Start");
                	 labelStart.setFont(labelStart.getFont().deriveFont(Font.BOLD, 10));
                	 labelStart.setForeground(Color.WHITE);
                    block.add(labelStart);
                }
                else if(maze[i][j] == 11) {
                	block.setBackground(Color.RED);
                	JLabel labelEnd = new JLabel("Koniec");
                	labelEnd.setFont(labelEnd.getFont().deriveFont(Font.BOLD, 10));
                	labelEnd.setForeground(Color.WHITE);
                    block.add(labelEnd);	
                }
                block.setPreferredSize(new Dimension(blockSize, blockSize));
                mazePanel.add(block);
            }
        }

        mazePanel.setLayout(new GridLayout(maze.length, maze[0].length));
        mazePanel.revalidate();
        mazePanel.repaint();
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
