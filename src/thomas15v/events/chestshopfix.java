package thomas15v.events;

import com.Acrobot.ChestShop.Events.PreShopCreationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by thomas on 4/7/14.
 */
public class chestshopfix implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void PreShopCreationEvent(PreShopCreationEvent e){
        if(e.getSignLine((byte)3).equalsIgnoreCase("X9268"))
            e.setOutcome(PreShopCreationEvent.CreationOutcome.INVALID_ITEM);

    }

}
