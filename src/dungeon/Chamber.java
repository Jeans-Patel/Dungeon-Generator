package dungeon;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.die.D20;
import dnd.die.Percentile;
import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import dnd.exceptions.NotProtectedException;
import dnd.exceptions.UnusualShapeException;
import java.util.ArrayList;

public class Chamber extends Space{

	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we 
	don't want to dictate how you set up your packages we need them to be public 
	for the purposes of running an automated test suite (junit) on your code.  */

    /**
    * Creates a private attribute of type ChamberContents so all methods can work on
    * the same instace of ChamberContents.
    **/
    private ChamberContents myContents;
    /**
    * Creates a private attribute of type ChamberShapes so all methods can work on
    * the same instace of ChamberShapes.
    **/
    private ChamberShape mySize;
    /**
    * Creates a private attribute of type Monster so all methods can work on
    * the same instace of Monster.
    **/
    private D20 d20;
    private Percentile d100;
    private Monster monster;
    /**
    * Creates a private attribute of type Stairs so all methods can work on
    * the same instace of Stairs.
    **/
    private Stairs stairs;
    /**
    * Creates a private attribute of type Trap so all methods can work on
    * the same instace of Trap.
    **/
    private Trap trap;
    /**
    * Creates a private attribute of type Treasure so all methods can work on
    * the same instace of Treasure.
    **/
    private Treasure treasure;
    /**
     * Creates a private attribute of type door.
     */
    private Door door;
    /**
     * Creates an ArrayList of Monsters.
     */
    private ArrayList<Monster> monsterList;
    /**
     * Creates an ArrayList of Treasure.
     */
    private ArrayList<Treasure> treasureList;
    /**
     * Creates an ArrayList of Door.
     */
    private ArrayList<Door> doorList;
    /**
     * Creates a string builder for the size and shape description.
     */
    private StringBuilder sizeDescription;
    /**
     * Creates a string builder for the description of the contents.
     */
    private StringBuilder contentDescription;

    /**
     * Base constructor for the class, initalizes everything and generates the chamber.
     */
    public Chamber() {
        myContents = new ChamberContents();
        d20 = new D20();
        d100 = new Percentile();
        init();
        randAll();
        addAll();
    }

    /**
     * Second constructer for the class that takes in two perameters for preset chamber shape and chamber contents.
     * @param  theShape    The preset chamber shape provided by the "user".
     * @param  theContents the preset chamber contents provided by the "user".
     */
    public Chamber(ChamberShape theShape, ChamberContents theContents) {
        myContents = theContents;
        mySize = theShape;
        init();
        addAll();
    }

    /**
     * Inatalizes all the instance variables.
     */
    private void init() {
        monster = new Monster();
        stairs = new Stairs();
        trap = new Trap();
        treasure = new Treasure();
        doorList = new ArrayList<Door>();
        monsterList = new ArrayList<Monster>();
        treasureList = new ArrayList<Treasure>();
        contentDescription = new StringBuilder();
        sizeDescription = new StringBuilder();
        door = new Door();
        sizeDescription = new StringBuilder();
    }


    private void addAll() {
    	sizeDescription.append("-------- Chamber Shape/Size/Exits --------\n");
    	addShape();
    	addDimensions();
    	addExits();
        contentDescription.append("\n------------ Chamber Contents ------------\n");
    	addContents();
    }

    private void addShape() {
        sizeDescription.append("Shape: " + mySize.getShape() + "\n");
    }

    private void addDimensions() {
    	try {
            sizeDescription.append("Dimensions: " + mySize.getLength() + "' x " + mySize.getWidth() + "'\n");
        } catch (UnusualShapeException e) {
            sizeDescription.append("Dimensions: There are no dimensions (unusual shape)\n");
        }
        sizeDescription.append("Area: " + mySize.getArea() + " sqft\n");
    }

    private void addExits() {
        if (mySize.getNumExits() == 0) {
            mySize.setNumExits(1);
        }
    	sizeDescription.append("Number of Exits: " + mySize.getNumExits() + "\n");
        // Append the proper number of exits.
        for (int i = 0; i < mySize.getNumExits(); i++) {
            door = new Door();
            door.randDoor();
            addDoor(door);
            sizeDescription.append("\t" + (i + 1) + ". "  + door.getDescription());
        }
    }

    private void addContents() {
        // Declaring variables.
        String contents = myContents.getDescription();

        // The following line simply get...() and print them in a readable way.
        // The switch checks which content(s) are in the chamber display the proper contents.

        if (contents.contains("monster")) {
            addMonster(monster);
        } if (contents.contains("treasure")) {
        	addTreasure(treasure);
        } else if (contents.contains("stairs")) {
            contentDescription.append("stairs : " + stairs.getDescription() + "\n");
        } else if (contents.contains("trap")) {
            contentDescription.append("Its a trap ! " + trap.getDescription() + "\n");
        } else if (contents.contains("empty")) {
            contentDescription.append("Chamber is empty.\n");
        }
    }

    private void randAll() {
    	mySize = mySize.selectChamberShape(d20.roll());
        myContents.chooseContents(d20.roll());
        treasure.chooseTreasure(d100.roll());
        monster.setType(d20.roll());
        stairs.setType(d20.roll());
        trap.chooseTrap(d20.roll());

    }

	public void addMonster(Monster theMonster){
        monsterList.add(theMonster);
		contentDescription.append("Chamber has monster: " + theMonster.getDescription() + " (Min: " + theMonster.getMinNum() + ", Max: " + theMonster.getMaxNum() + ")\n");
	}

	public ArrayList<Monster> getMonsters(){
		return monsterList;
	}


	public void addTreasure(Treasure theTreasure){
        treasureList.add(theTreasure);
        String treasureProtection;
        String treasureDescription = theTreasure.getDescription();
        String treasureContainer = ", contained in " + theTreasure.getContainer();
        try {
            treasureProtection = " and, protected by " + theTreasure.getProtection();
        } catch (NotProtectedException ex) {
            treasureProtection = " and, there is no protection";
        }
        contentDescription.append("Chamber has treasure: " + treasureDescription + treasureContainer + treasureProtection + "\n");
	}

	public ArrayList<Treasure> getTreasureList(){
		return treasureList;
	}

    @Override
	public ArrayList<Door> getDoors(){
    	return doorList;
	}

	@Override
	public void addDoor(Door newDoor){
        newDoor.addSpace(this);
		doorList.add(newDoor);
	}

	@Override
	public String getDescription(){
		return sizeDescription.toString() + contentDescription.toString();
	}
}
