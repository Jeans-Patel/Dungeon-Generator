package dungeon;

import java.util.ArrayList;

public abstract class Space {

    /**
     * Gets the desciption from either chamber or passage.
     * @return The description of the space.
     */
    public abstract  String getDescription();
    /**
     * Sets the door of the chamber or passage to theDoor.
     * @param theDoor The door that the chamber or passage sets itself to.
     */
    public abstract void addDoor(Door theDoor);
    /**
     * Gets the number of doors from chamber or passage.
     * @return ArrayList containing the doors from that chamber or passage.
     */
    public abstract ArrayList getDoors();

}
