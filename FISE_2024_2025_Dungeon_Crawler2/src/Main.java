import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    private JFrame displayZoneFrame;
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine;

    public Main() throws Exception {
        createStartScreen();
    }

    public void createStartScreen() {

        JFrame startFrame = new JFrame("Welcome to MAGIC LAND");
        startFrame.setSize(400, 600);
        startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startFrame.setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        startFrame.add(backgroundPanel);

        JLabel titleLabel = new JLabel("MAGIC LAND", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        JButton startButton = new JButton("let's start the fight");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(100, 200, 100));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        startButton.addActionListener(e -> {
            startFrame.dispose();
            try {
                initializeGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        startFrame.setVisible(true);
    }


    private void initializeGame() throws Exception {
        displayZoneFrame = new JFrame("ELF VS MAGE");
        displayZoneFrame.setSize(2400, 900);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        DynamicSprite2 enemy = new DynamicSprite2(1300, 300,
                ImageIO.read(new File("./img/mageattack1.png")), 48, 50);
        enemy.setHealth(100);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();

        // Passe la référence de Main à GameEngine
        gameEngine = new GameEngine(hero, enemy, this);

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(enemy);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.addToMovingSpriteList2(enemy);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public void showGameOverScreen() {
        displayZoneFrame.getContentPane().removeAll();
        GameOver gameOverScreen = new GameOver(this);
        displayZoneFrame.getContentPane().add(gameOverScreen);
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();
    }


    public static void main(String[] args) throws Exception {
        new Main();
    }
}
