/**
*@author Amanda Yoresh
*/

public class CourseDBElement implements Comparable{
		
		private String ID = "", room = "", instructor = "";
		private int crn = 0; credit = 0;
		
		public CourseDBElement(){
		}
		
		public CourseDBElement(String ID, int crn, int credit, String room, String instructor){
			this.ID = ID;
			this.crn = crnl
			this.credit = credit;
			this.instructor = instructor;
			this.room = room;
		}
		
		public String toString(){
			return ("\nCourse: " + ID + " CRN: " + crn + " Credits: " + credit + " Instructor: " + instructor + " Room: " + room);
		}
		
		public int hashCode(){
			int toReturn = ("".crn).hashCode();
			return toReturn;
		}
		
		@Override
		public int compareTo(CourseDBElement ele){
			int toReturn = this.crn = ele.crn;
			return toReturn;
		}
		
		@Override
		public boolean equals(Object ele){
			boolean toReturn;
			
			if(this.toString().equals(ele.toString())){
				toReturn = true;
			}
			else{
				toReturn = false;
			}
			return toReturn;
		
		public String getId(){
			return ID;
		}
		
		public int getCRN(){
			return crn;
		}
		
		public int getCredits(){
			return credit;
		}
		
		public String getRoom(){
			return room;
		}
		
		public String getInstructor(){
			return instructor;
		}
		
		public void setId(String ID){
			this.ID = ID;
		}
		
		public void setCRN(int crn){
			this.crn = crn;
		}
		
		public void setCredits(int credit){
			this.credit  = credit;
		}
		public void setRoom(String room){
			this.room = room;
		}
		public void setInstructor(String instructor){
			this.instructor = instructor;
		}
		
		
}