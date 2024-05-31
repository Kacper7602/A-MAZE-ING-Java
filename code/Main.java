import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
//    	if (args.length != 2) {
//            System.out.println("Usage: java MazeApp <filetype> <filepath>");
//            System.out.println("filetype: 'text' or 'binary'");
//            return;
//        }

        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MazeGUI();
            }
        });
    }
}
