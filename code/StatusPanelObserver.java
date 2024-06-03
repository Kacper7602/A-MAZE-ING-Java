import javax.swing.*;
import java.awt.*;

public class StatusPanelObserver {
    private JLabel infoLabel;
    private Color defaultColor = Color.GRAY;  // Dodana zmienna do przechowywania domyślnego koloru

    // Konstruktor przyjmujący JLabel jako argument
    public StatusPanelObserver(JLabel infoLabel) {
        this.infoLabel = infoLabel;
        this.defaultColor = infoLabel.getForeground();  // Zachowaj domyślny kolor tekstu
    }

 // Metoda do ustawiania tekstu JLabel z domyślnym kolorem
    public void setDefault() {
        infoLabel.setText("...");
        infoLabel.setForeground(defaultColor); // Ustaw domyślny kolor
    }
    
    // Metoda do ustawiania tekstu JLabel z domyślnym kolorem
    public void setInfo(String info) {
        infoLabel.setText(info);
        infoLabel.setForeground(defaultColor); // Ustaw domyślny kolor
    }

    // Metoda do ustawiania tekstu JLabel z określonym kolorem
    public void setInfo(String info, Color color) {
        infoLabel.setText(info);
        infoLabel.setForeground(color);  // Ustaw określony kolor
    }
}
