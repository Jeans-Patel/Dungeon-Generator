package dungeon;

import java.util.ArrayList;

public class Generate {

    /**
     * Creates a temporary chamber.
     */
    private static Chamber tempChamber;
    /**
     * Creates a temporary passage.
     */
    private static Passage tempPassage;
    /**
     * Counts the number of chambers.
     */
    private static int numChambers;
    /**
     * Creates a temporary door, the first door.
     */
    private Door first;
    /**
     * Creates a temporary door.
     */
    private Door tempDoor;
    /**
     * Holds the full description of the entire level.
     */
    private StringBuilder fullDescription;
    /**
     * Creates an ArrayList of doors that are from the chamber.
     */
    private ArrayList<Door> chamberDoors;
    /**
     * Creates an ArrayList of doors that are from the passage.
     */
    private ArrayList<Door> passageDoors;
    /**
     * Creates a temporary ArrayList of spaces.
     */
    private ArrayList<Space> tempSpaces;
    /**
     * Creates a usful ArrayList of spaces (the ones connected to other things not deadends).
     */
    private ArrayList<Space> usefulSpaces;
    /**
     * Creates a useless ArrayList of spaces (the ones connected to dead ends on the other side).
     */
    private ArrayList<Space> uselessSpaces;

    /**
     * Initalizes all the attributes.
     */
    public Generate() {
        tempChamber = null;
        tempPassage = null;
        tempDoor = null;
        first = new Door();
        numChambers = 0;
        fullDescription = new StringBuilder();
        tempSpaces = new ArrayList<Space>();
        usefulSpaces = new ArrayList<Space>();
    }

    /**
     * A recursive method that keeps going until 5 chambers have been generated.
     * @param  entrance The door taken from the previous chamber/passage.
     * @return          null whenever the level gets dead ends before generating 5 chambers.
     */
    public Door generateLevel(Door entrance) {
        if (numChambers == 5) {
            return null;
        } else if (numChambers == 0) {
            tempChamber = new Chamber();
            tempPassage = new Passage();
            tempDoor = new Door();
            tempPassage.createRandomPassage();
            tempChamber.getDoors().get(0).addSpace(tempChamber);
            tempChamber.getDoors().get(0).addSpace(tempPassage);
            tempSpaces = tempChamber.getDoors().get(0).getSpaces();
            usefulSpaces.add(tempSpaces.get(0));
            usefulSpaces.add(tempSpaces.get(1));
            passageDoors = tempPassage.getDoors();
            if (passageDoors.size() == 0) {
                return null;
            }
            numChambers += 1;
            generateLevel(passageDoors.get(passageDoors.size() - 1));
            return first;
        } else {
            tempChamber = new Chamber();
            entrance.addSpace(tempPassage);
            entrance.addSpace(tempChamber);
            tempPassage = new Passage();
            tempPassage.createRandomPassage();
            tempChamber.getDoors().get(0).addSpace(tempChamber);
            tempChamber.getDoors().get(0).addSpace(tempPassage);
            tempSpaces = tempChamber.getDoors().get(0).getSpaces();
            usefulSpaces.add(tempSpaces.get(0));
            usefulSpaces.add(tempSpaces.get(1));
            passageDoors = tempPassage.getDoors();
            if (passageDoors.size() == 0) {
                return null;
            }
            numChambers += 1;
            generateLevel(passageDoors.get(passageDoors.size() - 1));
            return null;
        }
    }

    /**
     * The magical method that makes everything come together. It generates everything.
     */
    public void magic() {
        first = new Door();
        first.setArchway(true);
        while (numChambers != 5) {
            numChambers = 0;
            generateLevel(first);
        }
    }

    /**
     * Prints everything to the stirng in a readable fashion.
     */
    public void printAll() {
        int j;
        char passageNumber = 'a';
        char chamberNumber = 'A';
        for (int i = 0; i < 10; i += 1) {
            if (usefulSpaces.get(i) instanceof Chamber) {
                System.out.println("\nChamber " + chamberNumber + ":\n" + usefulSpaces.get(i).getDescription());
                chamberDoors = usefulSpaces.get(i).getDoors();
                for (j = 1; j < chamberDoors.size(); j++) {
                    uselessSpaces = chamberDoors.get(j).getSpaces();
                    System.out.println("Exit " + (j + 1) + " leads to dead end");
                }
                chamberNumber += 1;
                System.out.println("\n\t\tExit " + (1) + " leads to passage " + passageNumber);
                System.out.println("****************************************************************");
            } else {
                System.out.println("\nPassage " + passageNumber + " " + usefulSpaces.get(i).getDescription());
                passageDoors = usefulSpaces.get(i).getDoors();
                for (j = 0; j < (passageDoors.size() - 1); j++) {
                    uselessSpaces = passageDoors.get(j).getSpaces();
                    System.out.println("Exit " + (j + 1) + " leads to a dead end");
                }
                if (i == 9) {
                    System.out.println("Exit " + (passageDoors.size()) + " leads to a dead end");
                    System.out.println("5 Chanber have been generated.");
                } else {
                    passageNumber += 1;
                    System.out.println("\n\t\tExit " + (passageDoors.size()) + " leads to chamber " + chamberNumber);
                    System.out.println("****************************************************************");
                }
            }
        }
    }

    public static void main(String[] args) {
        Generate newmain = new Generate();
        newmain.magic();
        newmain.printAll();
    }
}
