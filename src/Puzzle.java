import java.util.ArrayList;
import java.util.List;

/**
 * Class puzzle implementing a puzzle.
 */
public class Puzzle {
    private int [][]board;
    private int ts;
    private String path;
    private int h;
    private int [] xPos;
    private int [] yPos;
    private int mone;

    /**
     * Getter.
     * @return the path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter.
     * @return the heuristic value.
     */
    public int getH() {
        return h;
    }

    /**
     * Getter.
     * @return the mone counter.
     */
    public int getMone() {
        return mone;
    }

    /**
     * Setter.
     * @param mone
     */
    public void setMone(int mone) {
        this.mone = mone;
    }

    /**
     * The puzzle builder. Initializes the arguments and creates the matrix board. Also for
     * each number updates its x and Y position in the xPos and yPos arrays accordingly.
     * @param tableSize
     * @param startBoard
     */
    Puzzle(int tableSize,String startBoard){ // building the board from the given startBoard
        this.ts=tableSize;
        this.board=new int[ts][ts];
        this.path="";
        this.h=0;
        this.xPos=new int[ts*ts];
        this.yPos=new int[ts*ts];
        String[]strLst=startBoard.split("-");
        int i=0;
        int num;
        for (int row=0;row<ts;row++) {
            for(int col=0;col<ts;col++){
                num=Integer.parseInt(strLst[i]);
                board[row][col]=num;
                xPos[num]=row;
                yPos[num]=col;
                i++;
            }
        }
    }

    /**
     * Finds the number zero position in the board.
     * @return the x and y location in an array.
     */
    private int[] findZero(){
        for (int row=0;row<ts;row++) {
            for(int col=0;col<ts;col++){
                if (board[row][col]==0){
                    int [] location={row,col};
                    return location;
                }
            }
        }
        int [] location={-5,-5};
        return location;
    }

    /**
     * Checks if this.board is the goal state board.
     * @return true or false.
     */
    public boolean goal(){
        int i=1;
        for (int row=0;row<ts;row++) {
            for(int col=0;col<ts;col++){
                if (board[row][col]!=i && (row!=ts-1 || col!=ts-1))
                    return false;
                if (row==ts-1 && col==ts-1 && board[row][col]!=0)
                    return false;
                i++;
            }
        }
        return true;
    }

    /**
     * creates a list of new puzzle successors (the possible ones) ordered by [Up,Down,Left,Right].
     * @return The list of successors created.
     */
    public List<Puzzle> successors(){
        int[]location=this.findZero();
        int zRow=location[0];
        int zCol=location[1];
        List<Puzzle> pzlList=new ArrayList<Puzzle>();
        if(zRow!=ts-1) { //Up is optional
            Puzzle up=copyPuzzle();
            up.swap(zRow,zCol,zRow+1,zCol);
            up.path=this.path+"U";
            pzlList.add(up); //appends the puzzle to the list
        }
        if(zRow!=0){ //Down is optional
            Puzzle down=copyPuzzle();
            down.swap(zRow,zCol,zRow-1,zCol);
            down.path=this.path+"D";
            pzlList.add(down);
        }
        if(zCol!=ts-1){ //Left is optional
            Puzzle left=copyPuzzle();
            left.swap(zRow,zCol,zRow,zCol+1);
            left.path=this.path+"L";
            pzlList.add(left);
        }
        if(zCol!=0){ //Right is optional
            Puzzle right=copyPuzzle();
            right.swap(zRow,zCol,zRow,zCol-1);
            right.path=this.path+"R";
            pzlList.add(right);
        }
        return pzlList;
    }

    /**
     * Copies the puzzle by creating a new puzzle object with the same starting arguments.
     * @return
     */
    private Puzzle copyPuzzle(){
        String startBoard = boardToString();
        Puzzle p=new Puzzle(ts,startBoard);
        return p;
    }

    /**
     * The method helps convert the matrix board to a string.
     * @return The string board.
     */
    private String boardToString() {
        String startBoard="";
        for (int row=0;row<ts;row++) {
            for (int col = 0; col < ts; col++) {
                startBoard+=Integer.toString(board[row][col])+"-";
            }
        }
        if(startBoard!=null && startBoard.length()>0)  //trim the last "-"
            startBoard=startBoard.substring(0,startBoard.length()-1);
        return startBoard;
    }

    /**
     * Swaps between the two values in the given coordinates, then swaps the corresponding coordinates in the
     * xPos and yPos arrays.
     * @param zRow
     * @param zCol
     * @param newRow
     * @param newCol
     */
    private void swap(int zRow, int zCol,int newRow,int newCol){
        //swapping the values in the board
        int temp=board[zRow][zCol];
        board[zRow][zCol]=board[newRow][newCol];
        board[newRow][newCol]=temp;
        //updating the xPos and yPos arrays
        int xTemp=zRow;
        int yTemp=zCol;
        xPos[0]=newRow;
        yPos[0]=newCol;
        xPos[board[zRow][zCol]]=xTemp;
        yPos[board[zRow][zCol]]=yTemp;
    }

    /**
     * Computes the heuristic function and places it in this.h variable.
     * The function is computed using the xPos and ypos arrays.
     */
    public void hFunc(){
        int i=1;
        int sum=0;
        for (int row=0;row<ts;row++) {
            for(int col=0;col<ts;col++){
                if (row!=ts-1 || col!=ts-1){
                    sum+=Math.abs(xPos[i]-row)+Math.abs(yPos[i]-col);
                }
                i++;
            }
        }
        h=sum;
    }
}
