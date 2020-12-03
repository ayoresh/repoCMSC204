package sample;

import java.util.*;

public class Graph implements  GraphInterface<Town, Road>{

    private HashSet<Road> e;
    private HashSet<Town> v;
    private ArrayList<String> shortest;

    public Graph(){
        e = new HashSet<Road>();
        v = new HashSet<Town>();
    }

    @Override
    public Road addEdge(Town sV, Town dV,int w, String des) throws IllegalArgumentException, NullPointerException{
        if(!containsVertex(sV) || !containsVertex(dV)){
            throw new IllegalArgumentException("One or both vertices are not contained in the graph");
        }

        if(sV == null || dV == null){
            throw new NullPointerException("Cannot add null edge");
        }

        Road nR = new Road(sV, dV,w,des);
        boolean toReturn = e.add(nR);

        sV.addNeighbor(dV);
        dV.addNeighbor(sV);

        if(!toReturn){
            return null;
        }
        return nR;

    }

    @Override
    public boolean containsEdge(Town sV, Town dV){
        Road temp = new Road(sV, dV, "checking");
        if(e.contains(temp)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Set<Road> edgeSet(){
        return e;
    }


    @Override
    public Set<Town> vertexSet(){
        return v;
    }

    @Override
    public Set<Road> edgesOf(Town ver) throws NullPointerException, IllegalArgumentException{

        if(!containsVertex(ver)){
            throw new IllegalArgumentException("Vertex is not contained in graph");
        }
        if(ver == null){
            throw new NullPointerException("Cannot find null vertex");
        }

        HashSet<Road> res = new HashSet<Road>();
        Iterator<Road> iter = e.iterator();
        Road thisOne;

        while(iter.hasNext()){
            thisOne = iter.next();
            if(thisOne.contains(ver)){
                res.add(thisOne);
            }
        }

        return res;
    }



    @Override
    public boolean removeVertex(Town toRemove){
        HashSet<Road> removethis = new HashSet<Road>();

        if(!v.contains(toRemove)){
            return false;
        }
        else{
            v.remove(toRemove);
            Iterator<Road> iter = e.iterator();
            Road thisOne;

            while(iter.hasNext()){
                thisOne = iter.next();
                if(thisOne.contains(toRemove)){
                    removethis.add(thisOne);
                }
            }

            for(Road temp: removethis){
                e.remove(temp);
            }
            return true;
        }
    }





    @Override
    public Road getEdge(Town sV, Town dV){
        if(sV == null || dV == null){
            return null;
        }
        else{
            Iterator<Road> iter = e.iterator();
            Road thisOne;

            while(iter.hasNext()){
                thisOne = iter.next();
                if(thisOne.contains(sV) && thisOne.contains(dV)){
                    return thisOne;
                }
            }
        }
        return null;
    }

    @Override
    public boolean addVertex(Town toAdd) throws  NullPointerException{

        if(toAdd != null){
            if(v.contains(toAdd)){
                return false;
            }
            else{
                v.add(toAdd);
                return true;
            }
        } else{
            throw new NullPointerException("Null cannot be added");
        }

    }

    @Override
    public boolean containsVertex(Town checkContain){
        Iterator<Town> iter = v.iterator();
        Town thisOne;

        while(iter.hasNext()){
            thisOne = iter.next();
            if(thisOne.equals(checkContain)){
                return true;
            }
        }
        return false;
    }

    private int getInteger(String str){
        for(int x = str.indexOf("mi") - 2; x > -1; x++){
            if(str.charAt(x) == ' '){
                return Integer.parseInt(str.substring(x + 1, str.indexOf("mi") - 1));
            }
        }
        return -1;
    }

    private int getTotalWeight(String str, Town sV){
        String cN = str.substring(0, str.indexOf(" via"));
        Stack<String> p = new Stack<String>();
        int totalWeight = 0;
        p.push(str);

        while(!cN.equals(sV.getName())){
            for(int x = 0; x < shortest.size(); x++){
                if(shortest.get(x).contains("to " + cN)){
                    p.push(shortest.get(x));
                    cN = shortest.get(x).substring(0, shortest.get(x).indexOf(" via"));
                    break;
                }
            }
        }

        while(!p.empty()){
            totalWeight += getInteger(p.pop());
        }

        return totalWeight;

    }


    @Override
    public Road removeEdge(Town sV, Town dV, int w, String des){
        Road revRoad = new Road(sV, dV, w, des);
        Iterator<Road> iter = e.iterator();
        Road thisOne;

        while(iter.hasNext()){
            thisOne = iter.next();

            if(thisOne.equals(revRoad)){
                if((w > -1 && w == thisOne.getWeight()) || w == -1){
                    if((des != null && des.equals(thisOne.getName())) || des == null){
                        e.remove(thisOne);

                        sV.removeNeighbor(dV);
                        dV.removeNeighbor(sV);
                        return thisOne;

                    }
                }
            }
        }
        return null;
    }


    @Override
    public void dijkstraShortestPath(Town sV) {

        /*
         * The notation for each shortest path is stored
         * in shortestPath
         */
        shortest = new ArrayList<String>();
        HashSet<Town> cV = new HashSet<Town>();
        HashSet<Road> conE = new HashSet<Road>();
        HashSet<Road> pE = new HashSet<Road>();
        HashSet<Road> curE = new HashSet<Road>();
        HashSet<Town> torev = new HashSet<Town>();
        Queue<Town> vQ = new PriorityQueue<Town>();
        Town thisOne;

        shortest.add(sV.getName() + " via NONE to " + sV.getName() + " 0 mi");
        cV.add(sV);
        vQ.add(sV);

        curE = (HashSet<Road>) edgesOf(sV);

        for (Road counter : curE) {
            Town dest;
            if (counter.getSource().equals(sV)) {
                dest = counter.getDestination();
            } else {
                dest = counter.getSource();
            }
            shortest.add(sourceVertex.getName() + " via " + counter.getName() + " to " + dest.getName() + " " + counter.getWeight() + " mi");
        }


        while ((cV.size() != v.size()) && vQ.size() >= 0) {

            for(Town counter : vQ){
                curE = (HashSet<Road>) edgesOf(counter);
                int minimumWeight = 999999999;
                Road min = null;


                for (Road otherCounter : curE){
                    if((otherCounter.getWeight() < minimumWeight) && !conE.contains(otherCounter) && !(cV.contains(otherCounter.getSource()))){
                        min = otherCounter;
                        minimumWeight = otherCounter.getWeight();
                    }
                }

                if(min == null){
                    torev.add(counter);
                }
                else{
                    if(min.getSource.equals(counter)) {
                        pE.add(min);
                    }
                    else{
                        pE.add(new Road(min.getDestination(), min.getSource(), min.getWeight(), min.getName()));
                    }
                }
            }


            for(Town temp : torev){
                vQ.remove(temp);
            }

            int minint = 999999;
            Road minR = null;

            for(Road temp : pE){
                if(temp.getWeight() < minint){
                    min = temp;
                    minint = temp.getWeight();
                }
            }


            if(min != null){

                conE.add(min);
                Town newN = min.getDestination();
                cV.add(newN);
                vQ.add(newN);

                curE = (HashSet<Road>) edgesOf(newN);

                for(Road counter : curE){

                    String nodeName = "";
                    if(counter.getSource().equals(newN)){
                        nodeName = counter.getDestination().getName();
                    }
                    else{
                        nodeName = counter.getSource().getName();
                    }


                    int nIndex = -1;
                    for(int x = 0; x < shortest.size(); x++){
                        if(shortest.get(x).contains("to " + nodeName)){
                            nIndex = x;
                            break;
                        }
                    }

                    if(nIndex == -1){
                        int newWeight = -1;

                        for(int y = 0; y < shortest.size(); y++){
                            if(shortest.get(y).contains("to " + nodeName.getName())){
                                newWeight = getTotalWeight(shortest.get(y), sV);
                                break;
                            }
                        }
                    }

                    else{

                        int nbWeight = getTotalWeight(shortest.get(nIndex), sV);
                        in newWeight = -1;

                        for(int x = 0; x < shortest.size(); x++){
                            if(shortest.get(x).contains("to " + newN.getName())){
                                newWeight = getTotalWeight(shortest.get(x), sV);
                                break;
                            }
                        }

                        if((newWeight + counter.getWeight()) < nbWeight){
                            shortest.remove(nIndex);
                            shortest.add(newN.getName() + " via " + counter.getName() + " to " + nodeName + " " + counter.getWeight() + " mi");
                        }
                    }
                }

            }
            else{
                break;
            }

            pE.clear();
            torev.clear();
        }
    }


    @Override
    public ArrayList<String> shortestPath(Town sV, Town dV){

        dijkstraShortestPath(sV);
        ArrayList<String> res = new ArrayList<String>();

        Stack<String> p = new Stack<String>();
        boolean pExists = false;
        String cNode = "";


        for(int x = 0; x < shortest.size(); x++){

            if(shortest.get(x).contains("to " + dV.getName())){
                p.push(shortest.get(x));
                cNode = shortest.get(x).substring(0, shortest.get(x).indexOf(" via"));
                pExists = true;
                break;
            }

        }

        if(!pExists){
            return null;
        }

        while(!cNode.equals(sV.getName())){
            for(int y = 0; y < shortest.size(); y++){
                p.push(shortest.get(y));
                cNode = shortest.get(y).substring(0, shortest.get(y).indexOf(" via"));
                break;
            }
        }

        while(!p.empty()){
            res.add(p.pop());
        }

        return res;


    }







}
