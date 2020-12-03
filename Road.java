package sample;

public class Road implements Comparable<Road>{

    private Town src, dest;
    private String name;
    private int weight;


    public Road(Town src, Town dest, String name){
        this.src = src;
        this.name = name;
        this.dest = dest;
        weight = 1;
    }

    public Road(Town src, Town dest, int deg, String name){
        this.src = src;
        this.dest = dest;
        weight = deg;
        this.name = name;
    }


    public void setSource(Town src) {
        this.src = src;
    }


    public Town getDestination() {
        return dest;
    }

    public Town getSource() {
        return src;
    }

    public String getName() {
        return name;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }
    public void setDestination(Town dest) {
        this.dest = dest;
    }

    @Override
    public int hashCode() {
        return getSource().hashCode() + getDestination().hashCode();
    }

    public boolean contains(Town doescontain) {
        return (src.equals(doescontain) || dest.equals(doescontain));
    }

    @Override
    public String toString() {
        return "Road: " + getName() + " connects towns: " + getSource().getName()
                + " and " + getDestination().getName();
    }

    @Override
    public int compareTo(Road sent){
        int toReturn;
        toReturn = this.getWeight() - sent.getWeight();
        return toReturn;
    }

    @Override
    public boolean equals(Object x){
        boolean toReturn = false;
        Road y;
        if(x instanceof Road){
            y = (Road)x;
            toReturn = (y.contains(this.getSource()) && y.contains(this.getDestination()));
            return toReturn;
        }
        return toReturn;

    }


}
