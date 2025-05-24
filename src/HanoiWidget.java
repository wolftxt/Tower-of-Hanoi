
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JComponent;

public class HanoiWidget extends JComponent {

    private HanoiGame game;
    private boolean pressed = false;
    private int oldPressedThird;
    private int pressedThird;
    private Point mouseCoords;

    private Color[] colors;

    public void moveDiskTo() {
        if (game.getPlan().getTopDisk(oldPressedThird) == -1) {
            return;
        }
        game.moveDiskTo(oldPressedThird, game.getPlan().getTopDisk(oldPressedThird), pressedThird);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getPressedThird() {
        return pressedThird;
    }

    public void setPressedThird(int x) {
        this.pressedThird = x / (this.getWidth() / 3);
    }

    public int getOldPressedThird() {
        return oldPressedThird;
    }

    public void setOldPressedThird(int x) {
        this.oldPressedThird = x / (this.getWidth() / 3);
    }

    public Point getMouseCoords() {
        return mouseCoords;
    }

    public void setMouseCoords(int x, int y) {
        this.mouseCoords = new Point(x, y);
    }

    public HanoiWidget() {
        game = new HanoiGame(3);
        generateColors();
    }

    public HanoiWidget(int disks) {
        game = new HanoiGame(disks);
        generateColors();
    }

    public HanoiGame getGame() {
        return game;
    }

    public void newGame(int disks) {
        game = new HanoiGame(disks);
        generateColors();
    }

    private void generateColors() {
        Random rand = new Random();
        colors = new Color[game.getPlan().getDisks()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Generate board
        g.fillRect(0, this.getHeight() - (this.getHeight() / 10), this.getWidth(), this.getHeight() / 10);
        // Generate rods
        for (int i = 0; i < 3; i++) {
            g.setColor(Color.black);
            g.fillRect(((i + 1) * (this.getWidth() / 4)) - (this.getWidth() / 40), this.getHeight() / 4, this.getWidth() / 20, this.getHeight() * 3 / 4);
        }
        // Generate disks
        for (int i = 0; i < 3; i++) {
            int counter = 1;
            for (int j = 0; j < game.getPlan().getDisks(); j++) {
                g.setColor(colors[j]);
                if (pressed && j == game.getPlan().getTopDisk(i) && oldPressedThird == i && game.isPlaying() && game.getPlan().isDiskAt(i, j)) {
                    g.fillOval(mouseCoords.x - (int) (this.getWidth() / 5.0 * Math.pow(4.0 / 5.0, j)) / 2, mouseCoords.y - this.getHeight() * 65 / 100 / game.getPlan().getDisks() / 2, (int) (this.getWidth() / 5.0 * Math.pow(4.0 / 5.0, j)), this.getHeight() * 65 / 100 / game.getPlan().getDisks());
                } else if (game.getPlan().isDiskAt(i, j)) {
                    g.fillOval((i + 1) * (this.getWidth() / 4) - (int) (this.getWidth() / 5.0 * Math.pow(4.0 / 5.0, j)) / 2, this.getHeight() - (this.getHeight() / 10) - counter * this.getHeight() * 65 / 100 / game.getPlan().getDisks(), (int) (this.getWidth() / 5.0 * Math.pow(4.0 / 5.0, j)), this.getHeight() * 65 / 100 / game.getPlan().getDisks());
                    counter++;
                }
            }
        }
    }
}
