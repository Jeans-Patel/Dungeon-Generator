package dungeon;

import dnd.models.Monster;
import java.util.ArrayList;
import java.util.HashMap;

public class Passage extends Space {

    /**
     * Creates an ArrayList of passage sections.
     */
    private ArrayList<PassageSection> thePassage;
    /**
     * Creates an ArrayList of doors.
     */
    private ArrayList<Door> doorList;
    /**
     * Creates a hashmap of doors that map to passage sections.
     */
    private HashMap<Door, PassageSection> doorMap;
    /**
     * Creates a PassageSection called section.
     */
    private PassageSection section;
    /**
     * Creates int to count the number of sections.
     */
    private int numSections;
    /**
     * Creats a string builder to have the full description in one string.
     */
    private StringBuilder passageDescription;
    private Integer exitCounter;

    /**
     * Initalizes all the attributes.
     */
    public Passage() {
        thePassage = new ArrayList<PassageSection>();
        doorMap = new HashMap<Door, PassageSection>();
        doorList = new ArrayList<Door>();
        passageDescription = new StringBuilder();
        section = null;
        numSections = 0;
        exitCounter = 0;
    }

    /**
     * Generates an entire passage (until dead end or door).
     */
    public void createRandomPassage() {
        do {
            section = new PassageSection();
            if (numSections >= 9) {
                section = new PassageSection("Passage ends in an archway to chamber");
            }
            addPassageSection(section);
            if (section.getDoor() != null) {
                addDoor(section.getDoor());
            }
        } while (section.isEnd() == false);
    }

    public void createConnection() {
        section = new PassageSection("Passage ends in a door to chamber");
        addPassageSection(section);
        section = new PassageSection("Passage ends in a door to chamber");
        addPassageSection(section);
    }


    /**
     * Gets a specific door from the passage.
     * @param  i The index of the door in the doorList.
     * @return   Null if there is no door at the index or the door at the index
     */
    public Door getDoor(int i) {
        if (doorList.size() < i + 1) {
            return null;
        }
        return doorList.get(i);
    }

    /**
     * Adds a new passage section to the passage.
     * @param toAdd The PassageSection that is being added to the passage.
     */
    public void addPassageSection(PassageSection toAdd) {
        numSections++;
        thePassage.add(toAdd);
    }

    private void checkForExit(PassageSection ps) {
        if (doorMap.get(getDoor(exitCounter)) == ps) {
            passageDescription.append("(Exit " + (exitCounter+1) + ")\t");
            exitCounter++;
        } else {
            passageDescription.append("\t\t");
        }
    }

    /**
     * Returns the list of doors in the passage.
     * @return The ArrayList of doors.
     */
    @Override
    public ArrayList<Door> getDoors() {
        return doorList;
    }

    /**
     * Connets the passage to a door.
     * @param newDoor The door that is going to be connected to the passage.
     */
    @Override
    public void addDoor(Door newDoor) {
        newDoor.addSpace(this);
        doorMap.put(newDoor, section);
        doorList.add(newDoor);
    }

    /**
     * Returns the description of the entire passage.
     * @return Returns the description of the entire passage.
     */
    @Override
    public String getDescription() {
        passageDescription.append("Passage has " + numSections + " sections\n");
        for (PassageSection ps : thePassage) {
            passageDescription.append("\t");
            checkForExit(ps);
            passageDescription.append(ps.getDescription());
        }
        return passageDescription.toString();
    }
}
