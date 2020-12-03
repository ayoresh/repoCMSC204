package sample;

import java.util.*;
import java.io.File;

public class TownGraphManager implements TownGraphManagerInterface{

    private Graph g = new Graph();


    @Override
    public String getRoad(String t1, String t2){
        Road toGet = g.getEdge(new Town(t1), new Town(t2));
        if(toGet == null){
            return null;
        }
        String toReturn = toGet.getName();
        return toReturn;
    }



    @Override
    public boolean addTown(String sent) {
        boolean toReturn;
        toReturn = g.addVertex(new Town(sent));
        return toReturn;
    }


    @Override
    public boolean containsTown(String sent) {
        boolean toReturn;
        toReturn = g.containsVertex(new Town(sent));
        return toReturn;
    }



    @Override
    public boolean deleteTown(String v) {
        boolean toReturn;
        toReturn = graph.removeVertex(new Town(v));
        return toReturn;
    }

    @Override
    public boolean addRoad(String src, String dest, int weight, String name){

        boolean toReturn;
        Town tsrc = new Town(src);
        Town tdest = new Town(dest);

        g.addVertex(tsrc);
        g.addVertex(tdest);

        Road res = g.addEdge(tsrc, tdest, weight, name);

        if (res == null) {
            toReturn = false;
        }
        else{
            toReturn = true;
        }

        return toReturn;
    }

    @Override
    public boolean containsRoadConnection(String t1, String t2){
        Town src = new Town(t1);
        Town dest = new Town(t2);
        boolean toReturn = g.containsEdge(src, dest);
        return toReturn;
    }

    @Override
    public boolean deleteRoadConnection(String t1, String t2, String name){
        Town src = new Town(t1);
        Town dest = new Town(t2);
        Road connectToDel = g.getEdge(src, dest);
        boolean toReturn;
        if(connectToDel == null){
            toReturn = false;
            return  toReturn;
        }
        g.removeEdge(src, dest, connectToDel.getWeight(), name);
        return true;
    }

    @Override
    public ArrayList<String> allRoads(){
        Set<Road> x = g.edgeSet();
        ArrayList<String> toReturn = new ArrayList<String>();

        for(Road temp : x){
            toReturn.add(temp.getName());
        }

        Collections.sort(toReturn);
        return toReturn;
    }

    @Override
    public ArrayList<String> allTowns(){
        Set<Town> x = g.vertexSet();
        ArrayList<String> toReturn = new ArrayList<String>();

        for(Town temp : x){
            toReturn.add(temp.getName());
        }

        Collections.sort(toReturn);
        return toReturn;
    }

    @Override
    public Town getTown(String name){
        Set<Town> vert = g.vertexSet();
        Iterator<Town> iter = vert.iterator();

        Town toGet = new Town(name);
        Town thisOne;

        while(iter.hasNext()){
            thisOne = iter.next();
            if(thisOne.equals(toGet)){
                return thisOne;
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> getPath(String t1, String t2){
        Town src = new Town(t1);
        Town dest = new Town(t2);
        ArrayList<String> toReturn = new ArrayList<String>();

        if((g.containsVertex(src) && g.containsVertex(dest)) && !(g.edgesOf(src).isEmpty() && !g.edgesOf(dest).isEmpty())){
            toReturn = g.shortestPath(src, dest);
            if(toReturn == null){
                return new ArrayList<String>();
            }
            return toReturn;
        }
        return new ArrayList<String>();
    }


    public void readFile(String fname){
        try{
            Scanner input = new Scanner(new File(fname));
            String line = "", name = "", src = "", dest = "";
            int weight = 0;

            while(input.hasNext()){
                line = input.nextLine();
                name = line.substring(0, line.indexOf(','));
                weight = Integer.parseInt(line.substring(line.indexOf(',') + 1, line.indexOf(';')));
                src = line.substring(line.indexOf(';') + 1);
                src = src.substring(0, src.indexOf(';'));
                dest = line.substring(line.indexOf(';') + 1);
                dest = dest.substring(dest.indexOf(';') + 1, dest.length());

                Town destTown = new Town(dest);
                Town srcTown = new Town(src);

                if(!g.containsVertex(srcTown)){
                    g.addVertex(srcTown);
                }

                if(!g.containsVertex(destTown)){
                    g.addVertex(destTown);
                }

                if(!g.containsEdge(srcTown, destTown)){
                    g.addEdge(srcTown,destTown,weight,name);
                }
            }
        }
        catch(Exception ex){
            System.out.println("readFile error");
            ex.printStackTrace();
        }
    }

}
