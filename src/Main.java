import java.util.*;

/**
 * Main method for testing purposes plus all implementation of all 4 search methods
 */
public class Main {
    public static void main(String[] args) {
        // The size of the grid
        int size = 4;

        // Creating a new start state
        State startState = new State(size, new int[] {0,3}, new int[] {1,3}, new int[] {2, 3}, new int[] {3, 3});
        State goalState = new State(size, new int[] {1,1}, new int[] {1,2}, new int[] {1, 3}, null);

        // Adding the start state to a node
        Node node = new Node(startState);

        DepthFirstSearch(node, goalState);
        //BreadthFirstSearch(node, goalState);
        //IterativeDeepeningSearch(node, goalState);
        //AStarSearch(node, goalState);
    }

    /**
     * Compares the coordinates all blocks of the current state with the coordinates of the blocks of the goal state
     * @param state - current state to compare with goal
     * @param goal - the goal state
     * @return if the state is a goal state or not
     */
    private static boolean isGoal(State state, State goal) {
        boolean equalsBlockA = Arrays.equals(state.getBlockA(), goal.getBlockA());
        boolean equalsBlockB = Arrays.equals(state.getBlockB(), goal.getBlockB());
        boolean equalsBlockC = Arrays.equals(state.getBlockC(), goal.getBlockC());
        if (equalsBlockA && equalsBlockB && equalsBlockC){
            return true;
        }
        return false;
    }

    /**
     * Implementation of DFS
     * @param root - the root node of the state
     * @param goal - the goal state of the search
     */
    private static void DepthFirstSearch(Node root, State goal) {
        System.out.println("Depth-First Search on state:\n" + root.getState());

        int nodes = 0;
        Stack<Node> stack = new Stack<>();
        Movement movement = new Movement();

        stack.add(root);

        while(!stack.isEmpty()) {
            ArrayList<Node> successors = new ArrayList<>();
            Node current = stack.pop();

            // If the goal state is reached
            if(isGoal(current.getState(), goal)) {
                ArrayList<Node> states = current.findPathTo(current);

                System.out.println("Depth-First Search has finished with depth: " + current.getDepth() + " | nodes expanded: " + nodes + "\n" + current.getState() + "\nStates:\n");

                for(Node state : states) {
                    System.out.println(state.getState());
                    System.out.println(nodes);
                }
                break;
            }

            successors.add(movement.goUp(current));
            successors.add(movement.goDown(current));
            successors.add(movement.goLeft(current));
            successors.add(movement.goRight(current));
            nodes++;

            // Randomizing the moves
            Collections.shuffle(successors);

            for(Node child : successors) {
                if(child != null) {
                    stack.add(child);
                }
            }
        }
    }

    /**
     * Implementation of DFS
     * @param root - the root node of the state
     * @param goal - the goal state of the search
     */
    private static void BreadthFirstSearch(Node root, State goal) {
        System.out.println("Breadth-First Search on state:\n" + root.getState());

        int nodes = 0;
        Queue<Node> queue = new LinkedList<>();
        Movement movement = new Movement();

        queue.add(root);

        while(!queue.isEmpty()) {
            ArrayList<Node> successors = new ArrayList<>();

            Node current = queue.remove();

            // If the goal state is reached
            if(isGoal(current.getState(), goal)) {
                ArrayList<Node> steps = current.findPathTo(current);

                System.out.println("Breadth-First Search has finished with depth: " + current.getDepth() + " | nodes expanded: " + nodes + "\n" + current.getState() + "\nStates:\n");

                for(Node step : steps) {
                    System.out.println(step.getState());
                }
                break;
            }

            successors.add(movement.goUp(current));
            successors.add(movement.goDown(current));
            successors.add(movement.goLeft(current));
            successors.add(movement.goRight(current));
            nodes++;

            for(Node child : successors) {
                if(child != null) {
                    queue.add(child);
                }
            }
        }
    }

    /**
     * Implementation of IDS
     * @param root
     * @param goal
     */
    private static void IterativeDeepeningSearch(Node root, State goal) {
        System.out.println("Breadth-First Search on state:\n" + root.getState());

        int nodes = 0;
        int maxDepth = 0;
        Stack<Node> stack = new Stack<>();
        Movement movement = new Movement();

        stack.add(root);

        while(!stack.isEmpty()) {
            ArrayList<Node> successors = new ArrayList<>();

            Node current = stack.pop();

            //If the goal state is reached
            if(isGoal(current.getState(), goal)) {
                ArrayList<Node> steps = current.findPathTo(current);

                System.out.println("Iterative deepening Search has finished with depth: " + current.getDepth() + " | nodes expanded: " + nodes + "\n" + current.getState() + "\nStates:\n");

                for(Node step : steps) {
                    System.out.println(step.getState());
                }
                break;
            } else {
                // Only expands the node if the nax depth of the node > current level
                if(current.getLevel() < maxDepth) {
                    successors.add(movement.goUp(current));
                    successors.add(movement.goDown(current));
                    successors.add(movement.goLeft(current));
                    successors.add(movement.goRight(current));
                    nodes++;

                    for(Node child : successors) {
                        if(child != null) {
                            stack.add(child);
                        }
                    }
                }
            }

            // Increase the max depth if there is no solution for this depth
            if(stack.size() == 0) {
                stack.push(root);
                maxDepth++;
            }

        }
    }

    /**
     * Implementation of A*
     * @param root
     * @param goal
     */
    private static void AStarSearch(Node root, State goal) {
        System.out.println("A* Search on:\n" + root.getState());

        int nodes = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Movement movement = new Movement();

        queue.add(root);
        while(!queue.isEmpty()) {

            ArrayList<Node> successors = new ArrayList<>();
            Node current = queue.poll();

            //If the goal state is reached
            if(isGoal(current.getState(), goal)) {

                ArrayList<Node> steps = current.findPathTo(current);

                System.out.println("A* deepening Search has finished with depth: " + current.getDepth() + " | nodes expanded: " + nodes + "\n" + current.getState() + "\nStates:\n");

                for(Node step : steps) {
                    System.out.println(step.getState());
                }
                break;
            }

            successors.add(movement.goUp(current));
            successors.add(movement.goDown(current));
            successors.add(movement.goLeft(current));
            successors.add(movement.goRight(current));
            nodes++;


            for(Node child : successors) {
                if(child != null) {
                    child.calculateDepthForAStar(goal);
                    queue.add(child);
                }
            }
        }
    }

}
