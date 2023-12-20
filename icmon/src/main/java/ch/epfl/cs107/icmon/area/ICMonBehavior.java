package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public final class ICMonBehavior extends AreaBehavior {
    /**
     * Default AreaBehavior Constructor
     *
     * @param window (Window): graphic context, not null
     * @param name   (String): name of the behavior image, not null
     */
    public ICMonBehavior(Window window, String name) {
        super(window, name);
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ICMonCellType color = ICMonCellType.toType(getRGB(height - 1 - y, x));
                setCell(x, y, new ICMonCell(x, y, color));
            }
        }
    }

    /**
     * Enum representing the allowed walking types
     */
    public enum AllowedWalkingType {
        NONE, // None
        SURF, // Only with surf
        FEET, // Only with feet
        ALL // All previous
    }

    /**
     * Enum representing different types of cells in a game
     */
    public enum ICMonCellType {
        NULL(0, AllowedWalkingType.NONE),
        WALL(-16777216, AllowedWalkingType.NONE),
        BUILDING(-8750470, AllowedWalkingType.NONE),
        INTERACT(-256, AllowedWalkingType.NONE),
        DOOR(-195580, AllowedWalkingType.ALL),
        INDOOR_WALKABLE(-1, AllowedWalkingType.FEET),
        OUTDOOR_WALKABLE(-14112955, AllowedWalkingType.FEET),
        WATER(-16776961, AllowedWalkingType.SURF),
        GRASS(-16743680, AllowedWalkingType.FEET);

        final int type;
        final AllowedWalkingType allowedWalkingType;

        ICMonCellType(int type, AllowedWalkingType allowedWalkingType) {
            this.type = type;
            this.allowedWalkingType = allowedWalkingType;
        }

        /**
         * Gets the allowed walking type for this celltype.
         *
         * @return The allowed walking type.
         */
        public AllowedWalkingType getAllowedWalkingType() {
            return allowedWalkingType;
        }

        /**
         * Converts an integer type code to an ICMonCellType (enum constant).
         *
         * @param type The integer type code to be converted.
         * @return The corresponding ICMonCellType enum constant.
         */
        public static ICMonCellType toType(int type) {
            for (ICMonCellType ict : ICMonCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assign it to a type
            System.out.println(type);
            return NULL;
        }
    }

    /**
     * Represents a cell in the game.
     */
    public class ICMonCell extends Cell {

        // The type of the ICMonCell
        private final ICMonCellType type;

        /**
         * Default Cell constructor
         *
         * @param x    (int): x-coordinate of this cell
         * @param y    (int): y-coordinate of this cell
         * @param type
         */
        public ICMonCell(int x, int y, ICMonCellType type) {
            super(x, y);
            this.type = type;
        }

        /**
         * Getter for the type of the ICMonCell.
         *
         * @return The type of the ICMonCell.
         */
        public ICMonCellType getType() { //j'ai crée ce getter pour ICMonPlayer
            return type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {

            for (Interactable interactable: entities){
                return interactable.takeCellSpace() != entity.takeCellSpace();
            }

            return type.allowedWalkingType != AllowedWalkingType.NONE;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
        }

    }
}
