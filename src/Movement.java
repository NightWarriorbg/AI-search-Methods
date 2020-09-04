import java.util.Arrays;

public class Movement {

    /**
     * The main logic behind the movement is done here.
     * @param node - the node which the movements needs to be done on
     * @param newAgent - the new position of the agent
     * @return the node that it was moved to
     */
    private Node move(Node node, int newAgent[]) {
        Node moveTo;
        if(Arrays.equals(node.getState().getBlockA(), newAgent)) {
            State state = new State(node.getState().getGridSize(), node.getState().getAgent(), node.getState().getBlockB(), node.getState().getBlockC(), newAgent, node.getState().getBlockedCells());
            moveTo = new Node(state, node, node.getLevel() + 1, node.getDepth() + 1);
        } else if(Arrays.equals(node.getState().getBlockB(), newAgent)) {
            State state = new State(node.getState().getGridSize(), node.getState().getBlockA(), node.getState().getAgent(), node.getState().getBlockC(), newAgent, node.getState().getBlockedCells());
            moveTo = new Node(state, node, node.getLevel() + 1, node.getDepth() + 1);
        } else if(Arrays.equals(node.getState().getBlockC(), newAgent)) {
            State state = new State(node.getState().getGridSize(), node.getState().getBlockA(), node.getState().getBlockB(), node.getState().getAgent(), newAgent, node.getState().getBlockedCells());
            moveTo = new Node(state, node, node.getLevel() + 1, node.getDepth() + 1);
        } else {
            State state = new State(node.getState().getGridSize(), node.getState().getBlockA(), node.getState().getBlockB(), node.getState().getBlockC(), newAgent, node.getState().getBlockedCells());
            moveTo = new Node(state, node, node.getLevel() + 1, node.getDepth() + 1);
        }
        return moveTo;
    }

    /**
     * Checks if a cell exists in the blocked cells database
     * @param x - x of the blocked cell
     * @param y - y of the blocked cell
     * @param blockedCells - database with blocked cells and their coordinates
     * @return if the cell on the given coordinates is blocked or not
     */
    private boolean isBlocked(int x, int y, int blockedCells[][]) {
        boolean blocked = false;
        if (blockedCells != null) {
            for (int[] cell : blockedCells) {
                if (x == cell[0] && y == cell[1]) {
                    blocked = true;
                    break;
                }
            }
        }
        return blocked;
    }

    /**
     * Moves the node up
     * @param node - the node which is being moved
     * @return the new node after the movement is done, if the node is blocked - don't do anything
     */
    public Node goUp(Node node) {
        if (node.getState().getAgent()[1] - 1 >= 0 && !isBlocked(node.getState().getAgent()[0], node.getState().getAgent()[1] - 1, node.getState().getBlockedCells()))  {
            return move(node, new int[] {node.getState().getAgent()[0], node.getState().getAgent()[1] - 1});
        }
        return null;
    }

    /**
     * Moves the node down
     * @param node - the node which is being moved
     * @return the new node after the movement is done, if the node is blocked - don't do anything
     */
    public Node goDown(Node node) {
        if (node.getState().getAgent()[1] + 1 <= node.getState().getGridSize() - 1&& !isBlocked(node.getState().getAgent()[0], node.getState().getAgent()[1] + 1, node.getState().getBlockedCells()) ) {
            return move(node, new int[] {node.getState().getAgent()[0], node.getState().getAgent()[1] + 1});
        }
        return null;
    }

    /**
     * Moves the node to the left
     * @param node - the node which is being moved
     * @return the new node after the movement is done, if the node is blocked - don't do anything
     */
    public Node goLeft(Node node) {
        if (node.getState().getAgent()[0] - 1 >= 0 && !isBlocked(node.getState().getAgent()[0] - 1, node.getState().getAgent()[1], node.getState().getBlockedCells()) ) {
            return move(node, new int[] {node.getState().getAgent()[0] - 1, node.getState().getAgent()[1]});
        }
        return null;
    }

    /**
     * Moves the node to the right
     * @param node - the node which is being moved
     * @return the new node after the movement is done, if the node is blocked - don't do anything
     */
    public Node goRight(Node node) {
        if (node.getState().getAgent()[0] + 1 <= node.getState().getGridSize() - 1 && !isBlocked(node.getState().getAgent()[0] + 1, node.getState().getAgent()[1], node.getState().getBlockedCells()) ) {
            return move(node, new int[] {node.getState().getAgent()[0] + 1, node.getState().getAgent()[1]});
        }
        return null;
    }

}
