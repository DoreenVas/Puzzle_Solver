/**
 * The interface of an algorithm.
 * Implements the 'run' function.
 */
public interface Algorithm {
    /**
     * The function implements an algorithm, the puzzle object is created with the given arguments.
     * @param tableSize
     * @param startBoard
     * @return String that contains the path to the goal, number of developed nodes and the depth/cost of the path.
     */
    public String run(int tableSize,String startBoard);
}
