package sample;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;


public class MorseCodeConverter {

    public static MorseCodeTree placeHolder = new MorseCodeTree();

    public static String printTree(){
        ArrayList<String> temp = placeHolder.toArrayList();
        String toReturn = "";

        for (String x : temp){
            toReturn += (x + " ");
        }
        return  toReturn;
    }

    public static String convertToEnglish(String c){
        String[] temp = c.split(" ");
        String toReturn = "";

        for(String x : temp){
            toReturn += placeHolder.fetch(x);
        }

        return toReturn;
    }

    public static String convertToEnglish(File inFile){
        Scanner scan = null;
        String toReturn = "";
        try {
            scan = new Scanner(inFile);
        } catch (FileNotFoundException e){

        }

        while(scan.hasNextLine()){
            toReturn += convertToEnglish(scan.nextLine());
        }

        return  toReturn;
    }




}
