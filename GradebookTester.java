/**
*@author Amanda Yoresh
*Lab 3
*/

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class GradebookTester{
	
	GradeBook gradebookOne, gradebookTwo;
	
	@Before
	public void setUp() throws Exception{
		
		gradebookOne = new GradeBook(5);
		gradebookOne.addScore(60);
		gradebookOne.addScore(75);
		gradebookOne.addScore(98);
		gradebookOne.addScore(25);
		gradebookOne.addScore(86);
		
		
		gradebookTwo = new GradeBook(5);
		gradebookTwo.addScore(65);
		gradebookTwo.addScore(78);
		gradebookTwo.addScore(62);
		gradebookTwo.addScore(87);
		gradebookTwo.addScore(69);
		
		
	}
	
	
	@After
	public void tearDown() throws Exception{
		gradebookOne = null;
		gradebookTwo = null;
	}
	
	@Test
	public void testMinimum(){
		assertTrue(25 == gradebookOne.minimum());
		assertTrue(62 == gradebookTwo.minimum());
	}
	
	@Test
	public void testFinalScore(){
		assertTrue(319.0 == gradebookOne.finalScore());
		assertTrue(299.0 == gradebookTwo.finalScore());
	}
	
	@Test
	public void testSum(){
		assertTrue(344.0 == gradebookOne.sum());
		assertTrue(361.0 == gradebookTwo.sum());
	}
	
	@Test 
	public void testGetScoreSize(){
		assertEquals(5, gradebookOne.getScoreSize());
		assertEquals(5, gradebookTwo.getScoreSize());
	}
	
	@Test
	public void testToString(){
		assertTrue("60.0 75.0 98.0 25.0 86.0 ".equals(gradebookOne.toString()));
		assertTrue("65.0 78.0 62.0 87.0 69.0 ".equals(gradebookTwo.toString()));
	}
}