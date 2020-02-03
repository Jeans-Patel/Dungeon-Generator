package dungeon;

import dnd.models.Exit;
import dnd.models.Monster;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;


public class PassageSectionTest {
    
/* set up similar to the sample in PassageTest.java */
	private PassageSection testerOne;
	private PassageSection testerTwo;

	public PassageSectionTest() {
	}

@Before
public void setup() {
	testerOne = new PassageSection();
	testerTwo = new PassageSection();
    //set up any instance variables here so that they have fresh values for every test
}

@Test
public void testIsEnd() {
	System.out.println("is end");
	testerOne = new PassageSection("Dead End");

	boolean expResult = true;
	assertEquals(expResult, testerOne.isEnd());
}

@Test
public void testGetDoor() {
	System.out.println("get door");
	testerOne = new PassageSection("Passage ends in an archway to a chamber");
	assertTrue(testerOne.getDoor().isArchway());
}

@Test
public void testGetDescription() {
	System.out.println("get description");
	testerOne = new PassageSection("Get description works!");
	assertTrue(testerOne.getDescription().contains("Get description works!"));
}
    
}
