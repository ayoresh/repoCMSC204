import java.io.IOException;
import java.util.LinkedList;

/**
*@author Amanda Yoresh
*/

public class CourseDBStructure implements CourseDBStructureInterface{
	
	protected LinkedList<CourseDBElement>[] hTable;
	protected int tSize;
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int tSize){
		this.tSize = tSize;
		hTable = (LinkedList<CourseDBElement>[]) new LinkedList[tSize];
	}
	
	@Override
	public int getTableSize(){
		int toReturn = tSize;
		return toReturn;
	}
	
	@Override
	public CourseDBElement get(int crn) throws IOException{
		
		for(int counter = 0; counter < tSize; counter++){
			if(hTable[counter] != null){
				for(CourseDBElement x : hTable[counter]){
					if(x.getCRN() == x){
						return x;
					}
				}
			}
		}
		throw new IOException("Course CRN " + x + " is not in the database.");
	}
	
	
	
	@Override
	public void add(CourseDBElement ele){
		
		CourseDBElement tempele = new CourseDBElement(ele.getId(), ele.getCRN(), ele.getCredits(), ele.getRoom(), ele.getInstructor());
		
		int hashcode = Math.abs(ele.hashCode()) % tSize;
		LinkedList<CourseDBElement> eleList = hTable[hashCode];
		
		if(eleList == null){
			hTable[hashCode] = new LinkedList<CourseDBElement>();
		}
		
		hTable[hashCode].add(tempele);
	}
}