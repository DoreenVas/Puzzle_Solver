import java.io.*;
/**
 * java_ex1 is the main class.
 * In this class we read the arguments from "input.txt" file, run the correct algorithm
 * and write the result to the "output.txt" file.
 */
public class java_ex1 {
    final static String bfs="2";
    final static String ids="1";
    final static String astar="3";

    /**
     * The function reads the algorithm to run from "input.txt" file and the starting boards and board size.
     * Then it runs the correct algorithm and writes the result to the "output.txt" file.
     * @param args no args given
     * @throws Exception
     */
    public static void main(String[] args)throws Exception
    {
        //read from file
        File file= new File("input.txt");
        BufferedReader reader= new BufferedReader(new FileReader(file));
        String algorithm=reader.readLine();
        int tableSize=Integer.parseInt(reader.readLine());
        String startBoard=reader.readLine();
        reader.close();

        //select the correct algorithm and run it
        Algorithm a;
        //searching for the correct algorithm
        switch (algorithm){
            case bfs:{
                a = new Bfs();
                break;
            }
            case ids:{
                a = new Ids();
                break;
            }
            case astar:{
                a = new Astar();
                break;
            }
            default: a = new Bfs();
        }
        String answer=a.run(tableSize, startBoard);

        //write to file
        BufferedWriter writer=new BufferedWriter(new FileWriter("output.txt"));
        writer.write(answer);
        writer.close();
    }

}
