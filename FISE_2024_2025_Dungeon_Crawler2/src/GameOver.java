import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {

    private Main mainApp;

    public GameOver(Main mainApp) {
        this.mainApp = mainApp;

        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("GAME OVER", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 48));
        messageLabel.setForeground(Color.RED);

        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);

        this.add(messageLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour redémarrer le jeu
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        // Action pour quitter l'application
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void restartGame() {
        System.out.println("Redémarrage du jeu...");
        mainApp.createStartScreen();
    }
}
