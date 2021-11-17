package net.wdlvn.multicraft.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class InventoryChangeEvent extends Event{

    public static enum Mode {
        PUT, TAKE, SWAP;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Mode eventresult;
    private final Inventory inv;

    public InventoryChangeEvent(final Player player, final Mode result, final Inventory inv) {
        this.player = player;
        this.eventresult = result;
        this.inv = inv;
    }



    public Player getPlayer() {
        return this.player;
    }


    public Mode getMode() {
        return this.eventresult;
    }

    public Inventory getInventory() {
        return inv;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
