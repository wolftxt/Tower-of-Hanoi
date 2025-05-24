
public class HanoiGame {

    private HanoiPlan plan;
    private boolean playing = true;

    public HanoiGame(int disks) {
        plan = new HanoiPlan(disks);
        startGame();
    }

    public HanoiGame(HanoiGame game) {
        this.plan = new HanoiPlan(game.getPlan());
    }

    public void startGame() {
        for (int i = 0; i < plan.getDisks(); i++) {
            plan.setDiskAt(0, i, true);
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void moveDiskTo(int rodOld, int index, int rodNew) {
        // Exceptions
        if (!playing) {
            throw new RuntimeException("You can't move a disk after you won the game.");
        }
        if (!plan.isDiskAt(rodOld, index)) {
            throw new RuntimeException("You cannot move a disk that doesn't exist.");
        }
        if (rodOld < 0 || rodOld > 2) {
            throw new RuntimeException("Moving disk from a incorrect rod.");
        }
        if (index < 0 || index > plan.getDisks()) {
            throw new RuntimeException("Moving a incorrect disk index.");
        }
        if (rodNew < 0 || rodNew > 2) {
            throw new RuntimeException("Moving disk to a incorrect rod.");
        }
        if (rodOld == rodNew) {
           throw new RuntimeException("Moving a disk to the same rod.");
        }
        for (int i = plan.getDisks() - 1; i > index; i--) {
            if (plan.isDiskAt(rodOld, i)) {
                throw new RuntimeException("Cannot move a disk that isn't at the top.");
            }
            if (plan.isDiskAt(rodNew, i)) {
                throw new RuntimeException("Cannot move to a rod that has a smaller disk.");
            }
        }
        // End of exceptions
        plan.setDiskAt(rodOld, index, false);
        plan.setDiskAt(rodNew, index, true);
    }
    public HanoiPlan getPlan(){
        return plan;
    }

    public boolean isWinner() {
        int counter = 0;
        for (int index = 0; index < plan.getDisks(); index++) {
            if (plan.isDiskAt(2, index)) {
                counter++;
            }
        }
        if (counter == plan.getDisks()) {
            return true;
        }
        return false;
    }

}
