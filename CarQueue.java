import java.util.*;

public class CarQueue{
	Queue<Integer> q = new ArrayDeque<Integer>();
	Random ran = new Random();
	public CarQueue(){
		for(int x = 0; x < 5; x++){
			q.add(ran.nextInt(4));
		}
	}
	
	public void addToQueue(){
		class myRunnable implements Runnable{
			@Override 
			public void run(){
				try{
					q.add(ran.nextInt(4));
					q.add(ran.nextInt(4));
					q.add(ran.nextInt(4));
					q.add(ran.nextInt(4));
					q.add(ran.nextInt(4));
					Thread.sleep(1000);
				} catch (InterruptedException ex){
				}
			}
		Runnable runn = new myRunnable();
		Thread x = new Thread(runn);
		x.start();
		}
	}
	
	public int deleteQueue(){
		if(q.isEmpty()){
			return 0;
		} else{
			return q.remove();
		}
	}
}