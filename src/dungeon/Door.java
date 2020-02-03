package dungeon;

import dnd.die.D20;
import dnd.models.Exit;
import dnd.models.Trap;
import java.util.ArrayList;
import java.util.Random;

public class Door {

    /**
     * Boolean to check if the door is an archway.
     */
    private boolean archway;
    /**
     * Boolean to check if the door is open.
     */
    private boolean open;
    /**
     * Boolean to check if the door is trapped.
     */
    private boolean trapped;
    /**
     * Used to get the trap description.
     */
    private Trap trap;
    /**
     * List of spaces the door is connected to.
     */
    private ArrayList<Space> spaceList;
    /**
     * Builds the description based on the properties of the door.
     */
    private StringBuilder doorDescription;
    /**
     * Random number generated to generate a random door.
     */
    private Random rng;
    private D20 d20;

    /**
     * Base constructor for the class, initalizes everything and makes a random door.
     */
    public Door() {
        trap = new Trap();
        spaceList = new ArrayList<Space>();
        doorDescription = new StringBuilder();
        trapped = false;
        open = false;
        archway = false;
        d20 = new D20();
        rng = new Random();
    }

    /**
     * Makes a random door by setting values to true and false.
     */
    public void randDoor() {
        boolean randomNum1 = rng.nextBoolean();
        boolean randomNum2 = rng.nextBoolean();
        boolean randomNum3 = rng.nextBoolean();

        setArchway(randomNum1);
        setOpen(randomNum2);
        setTrapped(randomNum3);
    }

    /**
     * Sets the door to be trapped or not trapped and sets the trap description if one is provided.
     * @param flag What the condition of trapped is being set to.
     * @param roll The dice roll to pick the trap.
     */
    public void setTrapped(boolean flag, int... roll) {
        trapped = flag;

        if (roll.length != 1) {
            trap.setDescription(d20.roll());
        } else if (roll[0] > 0 && roll[0] < 21) {
            trap.setDescription(roll[0]);
        } else {
            trap.setDescription(d20.roll());
        }
    }

    /**
     * Sets the door to be open or closed depending on the flag.
     * @param flag What the door is being set to.
     */
    public void setOpen(boolean flag) {
        //true == open
        open = flag;
    }

    /**
     * Sets the door to be an arachway or not depending on the flag.
     * @param flag What the archway is being set to.
     */
    public void setArchway(boolean flag) {
        //true == is archway
        archway = flag;
        if (flag) {
            open = true;
            trapped = false;
        }
    }

    /**
     * Checks if the door is trapped.
     * @return Returns true or false depending on the state.
     */
    public boolean isTrapped() {
        return trapped;
    }

    /**
     * Checks if the door is open.
     * @return Returns true or false depending on the state.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Checks if the door is an archway.
     * @return Returns true or false depending on the state.
     */
    public boolean isArchway() {
        return archway;
    }

    /**
     * Gets the description of the trap if it is trapped.
     * @return Returns the description of the trap or null depending on weather the door is trapped or not.
     */
    public String getTrapDescription() {
        if (isTrapped()) {
            return trap.getDescription();
        }
        return null;
    }

    /**
     * Returns the list of spaces connected to the door but checks if they're valid before sending it.
     * @return The ArrayList of spaces connected to the door.
     */
    public ArrayList<Space> getSpaces() {
        return spaceList;
    }

    /**
     * If the second space is still undetermined it takes in one space and sets the second one to null.
     * @param spaceOne The space connected to the door from one side (other side is still unknown).
     */
    public void addSpace(Space spaceOne) {
        spaceList.add(spaceOne);
    }

    /**
     * Appends desciption if door is open.
     */
    private void openDescription() {
        if (isArchway()) {
            doorDescription.append("an archway\n");
        } else if (isTrapped()) {
            doorDescription.append("an open door trapped with ");
            doorDescription.append(trap.getDescription() + "\n");
        } else {
            doorDescription.append("an open door\n");
        }
    }

    private void closedDescription() {
        if (isTrapped()) {
            doorDescription.append("a closed door trapped with ");
            doorDescription.append(trap.getDescription() + "\n");
        } else {
            doorDescription.append("a closed door\n");
        }
    }

    /**
     * Gets the appropriate description of the door depending in its properties.
     * @return The proper description of the door.
     */
    public String getDescription() {
        if (isOpen()) {
            openDescription();
        } else {
            closedDescription();
        }
        return doorDescription.toString();
    }
}
