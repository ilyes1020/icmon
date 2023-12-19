package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.play.areagame.area.Area;

public class CollectItemEvent extends ICMonEvent{

    private ICMonItem item;
    public CollectItemEvent(ICMonItem item, ICMonPlayer player, Area area){
        super(player);
        this.item = item;
        onStart(new LogAction("ICMonItemCollect has started !"));
        onStart(new RegisterinAreaAction(area,item));
        onComplete(new LogAction("ICMonItemCollect has been completed !"));
    }
    @Override
    public void update(float deltaTime) { //pas sûr ici sur cette redefinition
        if (item.isCollected()){
            complete();
        }
    }
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        System.out.println("This is an interaction between the player and ICShopAssistant based on events !");
        player.openDialog("collect_item_event_interaction_with_icshopassistant_advice");
    }
}
