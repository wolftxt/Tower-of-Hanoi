
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class HanoiSolver extends JComponent {

    private BufferedImage solveButton;
    private HanoiGame game;
    private int[][] path;
    private int minNumberOfMoves;

    private boolean solutionFound;

    public HanoiSolver() {
        loadImages();
    }

    public int[] solve(HanoiGame game) {
        this.game = game;
        solutionFound = false;
        minNumberOfMoves = (int) Math.pow(2, game.getPlan().getDisks()) - 1;
        path = new int[minNumberOfMoves][3];
        for (int i = 1; i <= minNumberOfMoves; i++) {
            if (solutionFound) {
                break;
            }
            findSolution(0, i);
        }
        return path[0];
    }

    private void findSolution(int count, int max) {
        HanoiGame gameTest = new HanoiGame(game);
        if (winningPath(count)) {
            solutionFound = true;
            return;
        }
        if (count >= max) {
            return;
        }
        for (int i = 0; i < count; i++) {
            gameTest.moveDiskTo(path[i][0], path[i][1], path[i][2]);
        }
        for (int index = 0; index < gameTest.getPlan().getDisks(); index++) {
            if (count - 1 >= 0 && index == path[count - 1][1]) {
                continue;
            }
            for (int rodOld = 0; rodOld <= 2; rodOld++) {
                if (!gameTest.getPlan().isDiskAt(rodOld, index)) {
                    continue;
                }
                for (int rodNew = 0; rodNew <= 2; rodNew++) {
                    boolean exception = false;
                    if (rodOld == rodNew) {
                        continue;
                    }
                    for (int j = gameTest.getPlan().getDisks() - 1; j > index; j--) {
                        if (gameTest.getPlan().isDiskAt(rodOld, j)) {
                            exception = true;
                        }
                        if (gameTest.getPlan().isDiskAt(rodNew, j)) {
                            exception = true;
                        }
                    }
                    if (exception) {
                        continue;
                    }
                    if (solutionFound) {
                        return;
                    }
                    path[count][0] = rodOld;
                    path[count][1] = index;
                    path[count][2] = rodNew;
                    findSolution(count + 1, max);
                }
            }
        }
    }

    private boolean winningPath(int count) {
        HanoiGame gameTest = new HanoiGame(game);
        for (int i = 0; i < count; i++) {
            gameTest.moveDiskTo(path[i][0], path[i][1], path[i][2]);
        }
        return gameTest.isWinner();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(solveButton, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void loadImages() {
        try {
            solveButton = ImageIO.read(HanoiSolver.class.getResourceAsStream("solveButton.png"));
        } catch (IOException ex) {
            System.out.println("Error occured while loading images.");
        }
    }
}
