package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.play.areagame.area.Area;

/**
 * Event for collecting an item in the game.
 */
public class CollectItemEvent extends ICMonEvent{

    private ICMonItem item;

    /**
     * Constructor for creating a CollectItemEvent
     *
     * @param item   The item to be collected
     * @param player The player
     * @param area   The area where the item will be registered
     */
    public CollectItemEvent(ICMonItem item, ICMonPlayer player, Area area){
        super(player);
        this.item = item;
        onStart(new LogAction("ICMonItemCollect has started !"));
        onStart(new RegisterinAreaAction(area,item));
        onComplete(new LogAction("ICMonItemCollect has been completed !"));
    }

    /**
     * update and checks if the item has been collected, and complete the event if true
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        if (item.isCollected()){
            complete();
        }
    }

    /**
     * Interaction with an ICShopAssistant during the event
     *
     * @param assistant           The ICShopAssistant to interact with
     * @param isCellInteraction   Indicates if it's a cell interaction
     */
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        System.out.println("This is an interaction between the player and ICShopAssistant based on events !");
        player.openDialog("collect_item_event_interaction_with_icshopassistant_advice");
    }
}
