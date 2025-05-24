
import java.util.Arrays;

public class HanoiPlan {

    private boolean[][] plan;
    private int disks;

    public HanoiPlan(int disks) {
        if (disks < 1) {
            throw new RuntimeException("Cannot start a game with fewer than 1 disk.");
        }
        this.disks = disks;
        plan = new boolean[3][disks];
        clearPlan();
    }

    public HanoiPlan(HanoiPlan plan) {
        this.plan = plan.getPlan();
        this.disks = plan.getDisks();
    }

    public boolean[][] getPlan() {
        boolean[][] result = new boolean[plan.length][disks];
        for (int i = 0; i < plan.length; i++) {
            result[i] = Arrays.copyOf(plan[i], plan[i].length);
        }
        return result;
    }

    public int getDisks() {
        return disks;
    }

    public int getTopDisk(int rod) {
        if (rod < 0 || rod > 2) {
            throw new RuntimeException("Incorrect rod index");
        }
        for (int i = disks - 1; i >= 0; i--) {
            if (plan[rod][i]) {
                return i;
            }
        }
        return -1;
    }

    public void clearPlan() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < disks; j++) {
                plan[i][j] = false;
            }
        }
    }

    public void setDiskAt(int rod, int index, boolean diskOrNot) {
        if (rod < 0 || rod > 2) {
            throw new RuntimeException("Incorrect rod index");
        }
        if (index < 0 || index > disks) {
            throw new RuntimeException("Incorrect disk index");
        }
        plan[rod][index] = diskOrNot;
    }

    public boolean isDiskAt(int rod, int index) {
        if (rod < 0 || rod > 2) {
            throw new RuntimeException("Incorrect rod index");
        }
        if (index < 0 || index > disks) {
            throw new RuntimeException("Incorrect disk index");
        }
        return plan[rod][index];
    }
}
