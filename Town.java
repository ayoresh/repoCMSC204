package sample;
import java.util.*;

public class Town implements Comparable<Town>{

    private String name;
    private LinkedHashSet<Town> nset;

    public Town(Town temp){
        this.setName(temp.getName());
        nset = new LinkedHashSet<Town>();

        for(Town x : temp.getNeighbors()){
            this.nset.add(x);
        }
    }

    public Town(String name){
        this.name = name;
        nset = new LinkedHashSet<Town>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LinkedHashSet<Town> getNeighbors() {
        return nset;
    }



    public boolean addNeighbor(Town toAdd) {
        return nset.add(toAdd);
    }

    public boolean removeNeighbor(Town torev) {
        return nset.remove(torev);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void setNeighbors(LinkedHashSet<Town> nset) {
        this.nset = nset;
    }

    @Override
    public String toString(){
        String toReturn = getName() + " with neighbors ";
        Iterator iter = getNeighbors().iterator();

        while(iter.hasNext()){
            toReturn += ((Town)iter.next()).getName() + ", ";
        }
        toReturn = toReturn.substring(0, toReturn.length() - 2);
        return toReturn;
    }


    @Override
    public int compareTo(Town toCompare){
        Town chekcer = (Town) toCompare;
        int toReturn;

        toReturn = this.getName().compareTo(chekcer.getName());
        return toReturn;
    }

    @Override
    public boolean equals(Object sent){
        boolean toReturn;
        if(sent instanceof Town){
            Town checker = (Town) sent;
            toReturn = this.getName().equals(checker.getName());
            return toReturn;
        }
        else{
            return false;
        }
    }

}
