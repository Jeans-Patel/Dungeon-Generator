package dungeon;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChamberTest {
    private Chamber testerOne;
    private Chamber testerTwo;

    public ChamberTest() {
    }

@Before
public void setup(){
	testerOne = new Chamber();
	testerTwo = new Chamber();
}

@Test
public void testAddMonster() {
	System.out.println("add monster");
	Monster tempMonster = new Monster();
	tempMonster.setType(8);
	testerOne.addMonster(tempMonster);
	assertTrue(testerOne.getDescription().contains(tempMonster.getDescription()));
}

@Test
public void testGetMonsters() {
	System.out.println("get monsters");
	Monster tempMonster1 = new Monster();
	Monster tempMonster2 = new Monster();
	tempMonster1.setType(13);
	testerOne.addMonster(tempMonster1);
	tempMonster2.setType(17);
	testerOne.addMonster(tempMonster2);
	assertEquals(tempMonster1, testerOne.getMonsters().get(0));
	assertEquals(tempMonster2, testerOne.getMonsters().get(1));
}

@Test
public void testAddTreasure() {
	System.out.println("add treasure");
	Treasure tempTreasure = new Treasure();
	tempTreasure.chooseTreasure(55);
	testerOne.addTreasure(tempTreasure);
	assertTrue(testerOne.getDescription().contains(tempTreasure.getDescription()));
}

@Test
public void testGetTreasureList() {
	System.out.println("get treasure list");
	Treasure tempTreasure1 = new Treasure();
	Treasure tempTreasure2 = new Treasure();
	tempTreasure1.chooseTreasure(54);
	testerOne.addTreasure(tempTreasure1);
	tempTreasure2.chooseTreasure(97);
	testerOne.addTreasure(tempTreasure2);
	assertEquals(tempTreasure1, testerOne.getTreasureList().get(0));
	assertEquals(tempTreasure2, testerOne.getTreasureList().get(1));
}

@Test
public void testGetDoors() {
	System.out.println("get doors");
	Door tempDoor1 = new Door();
	Door tempDoor2 = new Door();
	tempDoor1.setOpen(true);
	testerOne.addDoor(tempDoor1);
	tempDoor2.setTrapped(true);
	testerOne.addDoor(tempDoor2);
	int expResult = 2;
	assertEquals(expResult, testerOne.getDoors().size());
}

@Test
public void testAddDoor() {
	System.out.println("add door");
}

    /* set up similar to the sample in PassageTest.java */

    
}