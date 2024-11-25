import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    public DynamicSprite hero;
    public DynamicSprite2 enemy;
    private Main mainApp;

    public GameEngine(DynamicSprite hero, DynamicSprite2 enemy, Main mainApp) {
        this.hero = hero;
        this.enemy = enemy;
        this.mainApp = mainApp;
    }

    @Override
    public void update() {
        if (isColliding(hero, enemy)) {
            hero.takeDamage(10);
            enemy.takeDamage(5);

            System.out.println("Collision détectée !");
            System.out.println("Héros : " + hero.getHealth() + " PV");
            System.out.println("Ennemi : " + enemy.getHealth() + " PV");

            if (enemy.getHealth() <= 0) {
                System.out.println("L'ennemi est mort !");
            }

            if (hero.getHealth() <= 0) {
                System.out.println("Game Over : Le héros est mort !");
                mainApp.showGameOverScreen();
            }
        }

    }


    private boolean isColliding(DynamicSprite a, DynamicSprite2 b) {
        return a.getHitBox().intersects(b.getHitBox());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

            case KeyEvent.VK_Z:
                enemy.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_S:
                enemy.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_Q:
                enemy.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_D:
                enemy.setDirection(Direction.EAST);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
