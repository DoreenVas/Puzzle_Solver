import java.util.List;
import java.util.PriorityQueue;

/**
 * Class Astar implements the A* algorithm.
 * The algorithm is an informed search algorithm that uses a heuristic function(Manhattan distance).
 */
public class Astar implements Algorithm{
    /**
     * The function implements the A* algorithm using a priority queue.
     * The queue sorts the puzzle objects with the PuzzleComparator.
     * @param tableSize
     * @param startBoard
     * @return A string containing: path to solution, number of developed nodes and the path cost.
     */
    public String run(int tableSize,String startBoard){
        Puzzle puzzle=new Puzzle(tableSize,startBoard);
        PriorityQueue<Puzzle> pQueue=new PriorityQueue<>(1,new PuzzleComparator());
        puzzle.hFunc();
        pQueue.add(puzzle);
        int num=0; //counts the number ot nodes developed
        puzzle.setMone(num);
        while(pQueue.isEmpty()==false){
            Puzzle current=pQueue.poll();
            num++;
            if(current.goal())
                return (current.getPath()+" "+num+" "+current.getPath().length());
            List<Puzzle> s=current.successors();
            for (Puzzle p:s){
                p.setMone(num);
                p.hFunc();
                pQueue.add(p);
            }
        }
        return "solution not found";
    }

}
