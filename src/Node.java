import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements a tree node used later for all 4 searching algorithms
 */
public class Node implements Comparable<Node> {
    /**
     * The state of the node
     */
    private State state;

    /**
     * The parent node
     */
    private Node parent;

    /**
     * Level of the node
     */
    private int level;

    /**
     * The depth of this node
     */
    private int depth;

    /**
     * A node with a parent
     * @param state - the state of the node
     * @param parent - the parent of the node
     * @param level - the level of the node
     * @param depth - the cost of the node
     */
    public Node(State state, Node parent, int level, int depth) {
        setState(state);
        setParent(parent);
        setLevel(level);
        setDepth(depth);
    }

    /**
     * A node with no parent, root node
     * @param state - the state of the node
     */
    public Node(State state) {
        setState(state);
        setParent(null);
        setLevel(0);
        setDepth(0);
    }

    /**
     * Gets the current state
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the node
     * @param state - a new state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets the parent of the node
     * @return parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent of the node
     * @param parent - the parent node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Gets the level of the node
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the node
     * @param level - new level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the cost of the node
     * @return
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the cost of the node
     * @param cost
     */
    public void setDepth(int cost) {
        this.depth = cost;
    }

    /**
     * Finds to path to a given node
     * @param toFind - the node to find the path to
     * @return the path to the node
     */
    public ArrayList<Node> findPathTo(Node toFind) {
        ArrayList<Node> path = new ArrayList<>();
        while (toFind.parent != null) {
            path.add(toFind);
            toFind = toFind.parent;
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Calculates the depth between the current state and the goal state(needed for A*)
     * @param goal - goal state
     */
    public void calculateDepthForAStar(State goal) {
        int distanceFromGoalA = Math.abs(state.getBlockA()[0] - goal.getBlockA()[0]) + Math.abs(state.getBlockA()[1] - goal.getBlockA()[1]);
        int distanceFromGoalB = Math.abs(state.getBlockB()[0] - goal.getBlockB()[0]) + Math.abs(state.getBlockB()[1] - goal.getBlockB()[1]);
        int distanceFromGoalC = Math.abs(state.getBlockC()[0] - goal.getBlockC()[0]) + Math.abs(state.getBlockC()[1] - goal.getBlockC()[1]);
        depth += distanceFromGoalA + distanceFromGoalB + distanceFromGoalC;
    }

    @Override
    public int compareTo(Node node) {
        if (getDepth() - node.getDepth() == 0) {
            if (getLevel() > node.getLevel()) {
                return -1;
            } else if(node.getLevel() == getLevel()) {
                return 0;
            } else {
                return 1;
            }
        }
        return getDepth() - node.getDepth();
    }
}
