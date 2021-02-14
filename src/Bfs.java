import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class Bfs implements Breadth first search algorithm.
 * The algorithm is an uninformed search algorithm using a queue.
 */
public class Bfs implements Algorithm{

    /**
     * The function runs the bfs algorithm.
     * @param tableSize
     * @param startBoard
     * @return a string containing: a path to goal, number of nodes developed then the number 0.
     */
    public String run(int tableSize,String startBoard){
        Puzzle puzzle=new Puzzle(tableSize,startBoard);
        Queue<Puzzle> queue=new LinkedList<>();
        queue.add(puzzle);
        int num=0; //counts the number ot nodes developed
        while (queue.isEmpty()==false){
            Puzzle current=queue.remove(); //removes the first element in the queue (queue[0])
            num++;
            if (current.goal()) {
                return (current.getPath()+" "+num+" "+"0");
            }
            List<Puzzle> s=current.successors();
            queue.addAll(s); //appends the s list as it is
        }
        return "solution not found";

    }
}
