import java.util.List;

/**
 * Class Ids implements Ids algorithm.
 * The algorithm is an uninformed search algorithm working recursively.
 */
public class Ids implements Algorithm {
    /**
     * The function creates a root puzzle object from the arguments then increments the limit
     * until a solution is found running the dfs_L function.
     * @param tableSize
     * @param startBoard
     * @return A string containing: a path, number of nodes in the last iteration and the
     * limit in which the solution was found.
     */
    public String run(int tableSize, String startBoard) {
        Puzzle puzzle=new Puzzle(tableSize,startBoard);
        int limit=0;
        Pair solution=dfs_L(puzzle,limit);
        while(solution.getString().equals("FAIL")){
            limit++;
            solution=dfs_L(puzzle,limit);
        }

        return (solution.getString()+" "+solution.getInteger().toString()+" "+limit);
    }

    /**
     * The function works recursively like a stack implementing the Iterative deepening search.
     * @param puzzle
     * @param limit
     * @return a pair containing the path and the number of nodes developed.
     */
    private Pair dfs_L(Puzzle puzzle, int limit){
        Integer nodes=1; //counts the number of nodes in the iteration
        if(puzzle.goal())
            return new Pair(puzzle.getPath(),nodes);
        else{
            if (limit==0)
                return new Pair("FAIL",1);
            for(Puzzle p: puzzle.successors()){
                Pair solution=dfs_L(p,limit-1);
                if(!solution.getString().equals("FAIL")) {
                    Integer sum=solution.getInteger()+nodes;
                    return new Pair(solution.getString(),sum);
                }
                else{
                    nodes+=solution.getInteger();
                }
            }
            return new Pair("FAIL",nodes);
        }
    }
}
