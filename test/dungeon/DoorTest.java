package dungeon;

import dnd.models.Trap;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class DoorTest {

	private Door testerOne;
	private Door testerTwo;

    public DoorTest() {
    }

@Before
public void setup() {
	testerOne = new Door();
	testerTwo = new Door();
    //set up any instance variables here so that they have fresh values for every test
}

@Test
public void testRandDoor() {
	System.out.println("rand door");
	testerOne.randDoor();
	boolean result = false;
	if (testerOne.getDescription().contains("door") || testerOne.getDescription().contains("archway")) {
		result = true;
	}
	assertTrue(result);
}

@Test
public void testSetTrapped() {
	System.out.println("set trapped");
	testerOne.setTrapped(true);
	assertTrue(testerOne.isTrapped());
	testerOne.setTrapped(false);
	boolean expResult = false;
	assertEquals(expResult, testerOne.isTrapped());
}

@Test
public void testSetOpen() {
	System.out.println("set open");
	testerOne.setOpen(true);
	assertTrue(testerOne.isOpen());
	testerOne.setOpen(false);
	boolean expResult = false;
	assertEquals(expResult, testerOne.isOpen());
}

@Test
public void testSetArchway() {
	System.out.println("set archway");
	testerOne.setArchway(true);
	assertTrue(testerOne.isArchway());
	testerOne.setArchway(false);
	boolean expResult = false;
	assertEquals(expResult, testerOne.isArchway());
}

@Test
public void testIsTrapped() {
	System.out.println("is trapped");
	testerOne.setTrapped(false);
	testerTwo.setTrapped(true);
	boolean expResult1 = false;
	boolean expResult2 = true;
	assertEquals(expResult1, testerOne.isTrapped());
	assertEquals(expResult2, testerTwo.isTrapped());
}

@Test
public void testIsOpen() {
	System.out.println("is open");
	testerOne.setOpen(false);
	testerTwo.setOpen(true);
	boolean expResult1 = false;
	boolean expResult2 = true;
	assertEquals(expResult1, testerOne.isOpen());
	assertEquals(expResult2, testerTwo.isOpen());
}

@Test
public void testIsArchway() {
	System.out.println("is archway");
	testerOne.setArchway(false);
	testerTwo.setArchway(true);
	boolean expResult1 = false;
	boolean expResult2 = true;
	assertEquals(expResult1, testerOne.isArchway());
	assertEquals(expResult2, testerTwo.isArchway());
}

@Test
public void testGetTrapDescription() {
	System.out.println("get trap description");
	Trap tempTrap = new Trap();
	testerOne.setTrapped(true, 12);
	tempTrap.setDescription(12);
	assertEquals(testerOne.getTrapDescription(), tempTrap.getDescription());
}

@Test
public void testGetSpaces() {
	System.out.println("get spaces");
	Chamber tempChamber = new Chamber();
	testerOne.addSpace(tempChamber);
	assertEquals(tempChamber, testerOne.getSpaces().get(0));
}

@Test
public void testAddSpace() {
	System.out.println("add space");
	Chamber tempChamber = new Chamber();
	Passage tempPassage = new Passage();
	testerOne.addSpace(tempChamber);
	testerOne.addSpace(tempPassage);
	assertEquals(tempPassage, testerOne.getSpaces().get(1));
}

@Test
public void testGetDescription() {
	System.out.println("get description");
	testerOne.setArchway(true);
	assertTrue(testerOne.getDescription().contains("archway"));
	testerOne.setOpen(false);
	assertTrue(testerOne.getDescription().contains("closed"));
}

/* set up similar to the sample in PassageTest.java */
    
}
