package dungeon;

import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;


public class PassageTest {
    //you don't have to use instance variables but it is easier
    // in many cases to have them and use them in each test
    private Passage testerOne;
    private Passage testerTwo;
    private PassageSection sectionOne;
    private PassageSection sectionTwo;

    public PassageTest() {
    }

@Before
public void setup() {
	testerOne = new Passage();
	testerTwo = new Passage();
	sectionOne = new PassageSection();
	sectionTwo = new PassageSection();
    //set up any instance variables here so that they have fresh values for every test
}

@Test
public void createRandomPassageTest()
{
	System.out.println("create random passage");
	testerOne.createRandomPassage();


	boolean result = testerOne.getDescription().toLowerCase().contains("end");
	boolean expResult = true;

	assertEquals(expResult, result);
}

@Test
public void createConnectionTest() {
	System.out.println("create connection");

	testerOne.createConnection();

	boolean result1 = testerOne.getDescription().toLowerCase().contains("end");

	boolean expResult1 = true;

	assertEquals(expResult1, result1);
}

@Test
public void testGetDoor() {
	System.out.println("get door");

	Door door1 = new Door();
	Door door2 = new Door();

	door1.setArchway(true);
	door2.setOpen(true);
	door2.setTrapped(true);

	testerOne.addDoor(door1);
	testerOne.addDoor(door2);

	assertEquals(testerOne.getDoor(0), door1);
	assertEquals(testerOne.getDoor(1), door2);
}

@Test
public void testAddPassageSection() {
	System.out.println("add passage section");
        sectionOne = new PassageSection("Dead End");
        testerOne.addPassageSection(sectionOne);
        assertTrue(testerOne.getDescription().contains("Dead End"));
}

@Test
public void testGetDoors() {
	System.out.println("get doors");
	Door door1 = new Door();
	Door door2 = new Door();
	door1.setArchway(true);
	door2.randDoor();
	testerOne.addDoor(door1);
	testerOne.addDoor(door2);

	int expResult = 2;
	assertEquals(expResult, testerOne.getDoors().size());
}

@Test
public void testAddDoor() {
	System.out.println("add door");

	Door tempDoor = new Door();
	tempDoor.randDoor();
	testerOne.addDoor(tempDoor);

	assertEquals(testerOne, tempDoor.getSpaces().get(0));
}

@Test
public void testGetDescription() {
	System.out.println("get description");


}

}
