package net.lethargiclion.mailbox;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener{

	public ArrayList<String> activePlayers = new ArrayList<String>();
	private PluginMain plugin;
	
	public InventoryListener(PluginMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent evt) {
		if(!(activePlayers.contains(evt.getPlayer().getName()))) {
			return;
		}
		Inventory inv = evt.getInventory();
		if(inv.getHolder() instanceof Chest) {
			Player plr = (Player) evt.getPlayer();
			Chest chest = (Chest) inv.getHolder();
			if(plugin != null) {
				plugin.mgr.setMailbox(plr, chest);
			}
			((Player) evt.getPlayer()).sendMessage(ChatColor.GREEN+"[MailBox] You set your mailbox!");
			activePlayers.remove(evt.getPlayer().getName());
			return;
		}
	}
}
