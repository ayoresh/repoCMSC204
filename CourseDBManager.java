import java.io.*;
import java.util.*;

/**
*@author Amanda Yoresh
*/

public class CourseDBManager implements CourseDBManagerInterface{
	
	private int size = 20;
	CourseDBStructure struct = new CourseDBStructure(size);
	
	@Override
	public void add(String ID, int courseRegNum, int credit, String room, String instructor){
	
		CourseDBElement ele = new CourseDBElement(ID, courseRegNum, credit, room, instructor);
		struct.add(ele);
		
	}
	
	@Override
	public ArrayList<String> showAll(){
		
		ArrayList<String> bList = new ArrayList<String>();
		
		for(int counter = 0; counter < struct.hTable.length; counter++){
			
			if(struct.hTable[counter] != null){
			
				LinkedList<CourseDBElement> tempList = struct.hTable[counter];
				for(CourseDBElement x : tempList){
					
					bList.add(x.toString);
					
				}
			
			}
			
		}
	

		return bList;
	}
	
	@Override
	public CourseDBElement get(int crn){
		
		try{
			return struct.get(crn);
		} catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		return null;
		
	}
	
	@SuppressWarnings
	@Override
	public void readFile(File file) throws FileNotFoundException{
		
		String id = "", strCRN = "", strCREDIT = "", instructor = "", room = "";
		int crn = 0, credit = 0;
		
		Scanner input = new Scanner(file);
		while(input.hasNext()){
			
			if(input.hasNext()){
				id = input.next();
			}
			if(input.hasNext()){
				strCRN = input.next();
				crn = Integer.parseInt(strCRN);
			}
			if(input.hasNext()){
				strCREDIT = input.next();
				credit = Integer.parseInt(strCREDIT);
			}
			if(input.hasNext()){
				room = input.next();
			}
			if(input.hasNext()){
				instructor = input.nextLine();
			}
			
			add(id, crn, credit, room, instructor);
			
		}
		input.close();
		
	}
	
	
	
}