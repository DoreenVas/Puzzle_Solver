/**
 * Pair Class.
 * Contains an Integer and a String.
 */
public class Pair {
    private Integer i;
    private String s;

    /**
     * Builder.Initializing i and s to the given arguments.
     * @param st
     * @param in
     */
    Pair(String st,Integer in){
        this.i=in;
        this.s=st;
    }

    /**
     * Getter.
     * @return the String s.
     */
    public String getString(){
        return s;
    }

    /**
     * Getter.
     * @return the Integer i.
     */
    public Integer getInteger(){
        return i;
    }

}
