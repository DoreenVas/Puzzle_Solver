import java.util.Comparator;

/**
 * A comparator to help define the order of puzzle objects.
 */
public class PuzzleComparator implements Comparator<Puzzle> {
    /**
     * The compare function gets 2 puzzles and compares the 'f' of them (heuristic+cost so far).
     * The one with the smallest one comes first in the order.
     * If they are equal we compare by the father node first developed.
     * If they have the same father we call the getValue function and get the order by sons.
     * @param p1 first puzzle
     * @param p2 second puzzle
     * @return the order by ints.
     */
    @Override
    public int compare(Puzzle p1, Puzzle p2) {
        int f1=p1.getH()+p1.getPath().length();
        int f2=p2.getH()+p2.getPath().length();
        if(f1<f2)
            return -1;
        else if(f1>f2)
            return 1;
        else{ //f1=f2
            if(p1.getMone()<p2.getMone())
                return -1;
            else if(p1.getMone()>p2.getMone())
                return 1;
            else{
                int i=p1.getPath().length()-1;
                if (getValue(p1.getPath().charAt(i))<getValue(p2.getPath().charAt(i)))
                    return -1;
                else return 1;
            }
        }
    }

    /**
     * Receives a character and returns the value representing your development order.
     * @param a
     * @return
     */
    private int getValue(char a){
        int value;
        switch(a){
            case 'U':{value=1;break;}
            case 'D':{value=2;break;}
            case 'L':{value=3;break;}
            case 'R':{value=4;break;}
            default: {value=0;break;}
        }
        return value;
    }

}
