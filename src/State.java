import java.util.ArrayList;
/**
 * Represents a state of the grid
 */
public class State {

    /**
     * Size of the grid
     */
    private int gridSize;

    /**
     * The coordinates for the first building block
     */
    private int blockA[];

    /**
     * The coordinates for the second building block
     */
    private int blockB[];

    /**
     * The coordinates for the third building block
     */
    private int blockC[];

    /**
     * The coordinates for the agent
     */
    private int agent[];

    /**
     * A database with all blocked cells
     */
    private int blockedCells[][];

    /**
     * Creates a new state without any blocked cells
     * @param gridSize - the size of the grid
     * @param blockA - the coordinates of first building block
     * @param blockB - the coordinates of the second building block
     * @param blockC - the coordinates of the third building block
     * @param agent - the coordinates of the agent
     */
    public State(int gridSize, int blockA[], int blockB[], int blockC[], int agent[]) {
        this.gridSize = gridSize;
        this.blockA = blockA;
        this.blockB = blockB;
        this.blockC = blockC;
        this.agent = agent;
    }

    /**
     * Creates a state with blocked cells
     * @param gridSize - the size of the grid
     * @param blockA - the coordinates of first building block
     * @param blockB - the coordinates of the second building block
     * @param blockC - the coordinates of the third building block
     * @param agent - the coordinates of the agent
     * @param blockedCells - a database will all blocked cells
     */
    public State(int gridSize, int blockA[], int blockB[], int blockC[], int agent[], int[]...blockedCells) {
        this.gridSize = gridSize;
        this.blockA = blockA;
        this.blockB = blockB;
        this.blockC = blockC;
        this.agent = agent;
        this.blockedCells = blockedCells;
    }

    /**
     * Gets the size of the grid
     * @return size of grid
     */
    public int getGridSize() {
        return gridSize;
    }

    /**
     * Gets the coordinates of first block
     * @return first block cell
     */
    public int[] getBlockA() {
        return blockA;
    }

    /**
     * Sets the coordinates of the first block
     * @param blockA - new cell
     */
    public void setBlockA(int blockA[]) {
        this.blockA = blockA;
    }

    /**
     * Gets the coordinates of second block
     * @return second block cell
     */
    public int[] getBlockB() {
        return blockB;
    }

    /**
     * Sets the coordinates of the second block
     * @param blockB
     */
    public void setBlockB(int blockB[]) {
        this.blockB = blockB;
    }

    /**
     * Gets the coordinates of the third block
     * @return value of third block
     */
    public int[] getBlockC() {
        return blockC;
    }

    /**
     * Gets the coordinates of the third block
     * @return the third block cell
     */
    public void setBlockC(int block[]) {
        this.blockC = blockC;
    }

    /**
     * Gets the coordinates of the agent
     * @return the agent cell
     */
    public int[] getAgent() {
        return agent;
    }

    /**
     * Sets a new value to the cell of the agent
     * @param agent - new cell of the agent
     */
    public void setAgent(int agent[]) {
        this.agent = agent;
    }

    /**
     * Gets a list of all the blocked cells
     * @return
     */
    public int[][] getBlockedCells() {
        return blockedCells;
    }

    /**
     * Modifies the toString method so that the current State is properly displaced(This is only for our own convenience)
     * @return
     */
    @Override
    public String toString() {
        String val = "";
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (x == getBlockA()[0] && y == getBlockA()[1]) {
                    val += "[A] ";
                } else if (x == getBlockB()[0] && y == getBlockB()[1]) {
                    val += "[B] ";
                } else if (x == getBlockC()[0] && y == getBlockC()[1]) {
                    val += "[C] ";
                } else if (x == getAgent()[0] && y == getAgent()[1]) {
                    val += "[*] ";
                } else {
                    boolean blocked = false;
                    if (blockedCells != null) {
                        for (int cell[] : blockedCells) {
                            if (cell[0] == x && cell[1] == y) {
                                val += "[!] ";
                                blocked = true;
                                break;
                            }
                        }
                    }

                    if (!blocked) {
                        val += "[ ] ";
                    }
                }
            }
            val += "\n";
        }
        return val;
    }

}
