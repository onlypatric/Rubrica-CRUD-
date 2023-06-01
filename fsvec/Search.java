package fsvec;

/**
 * Search
 */
public class Search {

    public int val;
    public boolean found;
    public boolean proc1,proc2;
    public Search(int val,boolean found) {
        this.val=val;
        this.found=found;
        proc1=false;
        proc2=false;
    }
    @Override
    public String toString() {
        return "Ricerca [val=" + val + ", found=" + found + "]";
    }
    
}