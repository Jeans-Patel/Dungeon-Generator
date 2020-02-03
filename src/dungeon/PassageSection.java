package dungeon;

import dnd.die.D20;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.Random;
import java.util.HashMap;

public class PassageSection {

    /**
     * Creates a Door to use if section has a door.
     */
    private Door door;
    /**
     * Creates a Monster to use if section has a monster.
     */
    private Monster monster;
    /**
     * Creates Stairs to use if section has stairs.
     */
    private Stairs stairs;
    /**
     * Holds the description of the section.
     */
    private StringBuilder sectionDescription;
    private HashMap<Integer, String> section;
    private D20 d20;
    private Integer roll;
    private Boolean random;

    /**
     * Initalizes all the attributes.
     */
    public PassageSection() {
        monster = null;
        door = null;
        stairs = null;
        random = true;
        d20 = new D20();
        section = new HashMap<Integer, String>();
        sectionDescription = new StringBuilder();
        makeHashMap();
        makeHashMapRepeats();
        checkSection();
    }

    /**
     * Initalizes all the attributes and sets section to an appropriate string based on the description provided.
     * @param  description The description provided to make the section have something specific.
     */
    public PassageSection(String description) {
        monster = null;
        door = null;
        stairs = null;
        random = false;
        roll = -1;
        d20 = new D20();
        sectionDescription = new StringBuilder();
        sectionDescription.append(description);
        checkDescription();
    }

    /**
     * Makes the first half of the hashmap.
     */
    private void makeHashMap() {
        section.put(1, "Passage goes straight for 10 feet\n");
        section.put(3, "Passage ends in Door to a Chamber\n");
        section.put(6, "Archway (door) to right (main passage continues straight for 10 ft)\n");
        section.put(8, "Archway (door) to left (main passage continues straight for 10 ft)\n");
        section.put(10, "Passage turns to left and continues for 10 ft\n");
        section.put(12, "Passage turns to right and continues for 10 ft\n");
        section.put(14, "Passage ends in archway (door) to chamber\n");
        section.put(17, "Stairs, (passage continues straight for 10 ft): ");
        section.put(18, "Dead End\n");
        section.put(20, "Wandering Monster (passage continues straight for 10 ft): ");
    }

    /**
     * Makes the second half of the hashmap.
     */
    private void makeHashMapRepeats() {
        section.put(2, "Passage goes straight for 10 feet\n");
        section.put(4, "Passage ends in Door to a Chamber\n");
        section.put(5, "Passage ends in Door to a Chamber\n");
        section.put(7, "Archway (door) to right (main passage continues straight for 10 ft)\n");
        section.put(9, "Archway (door) to left (main passage continues straight for 10 ft)\n");
        section.put(11, "Passage turns to left and continues for 10 ft\n");
        section.put(13, "Passage turns to right and continues for 10 ft\n");
        section.put(15, "Passage ends in archway (door) to chamber\n");
        section.put(16, "Passage ends in archway (door) to chamber\n");
        section.put(19, "Dead End\n");
    }

    /**
     * Checks the user inputed description to see if anything needs to be made.
     */
    private void checkDescription() {
        if (sectionDescription.toString().toLowerCase().contains("end")) {
            checkEnd();
        } else if (sectionDescription.toString().toLowerCase().contains("archway")) {
            roll = 8;
        } else if (sectionDescription.toString().toLowerCase().contains("stairs")) {
            roll = 17;
        } else if (sectionDescription.toString().toLowerCase().contains("monster")) {
            roll = 20;
        }
        checkSection();
    }

    /**
     * Checks to see what type of "end" the user inputed.
     */
    private void checkEnd() {
        if (sectionDescription.toString().toLowerCase().contains("archway")) {
            roll = 15;
        } else if (sectionDescription.toString().toLowerCase().contains("door")) {
            roll = 4;
        } else {
            roll = 18;
        }
    }
    /**
     * Generates a random section.
     */
    private void checkSection() {
        if (random) {
            roll = d20.roll();
        }
        if (roll >= 3 && roll <= 5) {
            makeDoor();
        } else if ((roll >= 6 && roll <= 9) || (roll >= 14 && roll <= 16)) {
            makeArchway();
        } else if (roll >= 17) {
            makeSpecial();
        }
    }

    /**
     * Makes a door.
     */
    private void makeDoor() {
        door = new Door();
        door.randDoor();
    }

    /**
     * Makes an archway.
     */
    private void makeArchway() {
        door = new Door();
        door.setArchway(true);
    }

    /**
     * Makes a special case (monster or stairs).
     */
    private void makeSpecial() {
        if (roll == 17) {
            setStairs();        
        } else if (roll == 20) {
            setMonster();
        }
    }

    /**
     * Adds a monster to the section.
     */
    private void setMonster() {
        monster = new Monster();
        monster.setType(d20.roll());
    }

    /**
     * Adds stairs.
     */
    private void setStairs() {
        stairs = new Stairs();
        stairs.setType(d20.roll());
    }

    /**
     * Checks if this section is an end to the passage
     * @return true or false depending on the state of the section
     */
    public Boolean isEnd() {
        if ((roll >= 3 && roll <= 5) || (roll >= 14 && roll <= 16) || (roll >= 18 && roll <= 19)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the door in the section if there is one.
     * @return The door or null depending on the section.
     */
    public Door getDoor() {
        return door;
    }

    /**
     * Gets the description of the section.
     * @return The full description of the section.
     */
    public String getDescription() {
        if (random) {
            sectionDescription.append(section.get(roll));
            if (roll == 17) {
                sectionDescription.append(stairs.getDescription() + "\n");
            } else if (roll == 20) {
                sectionDescription.append(monster.getDescription() + "\n");
            }
        }
        return sectionDescription.toString();
    }
}
