package net.lethargiclion.mailbox;

import java.io.IOException;
import java.util.Set;

import net.minecraft.server.Material;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;



public class MailboxManager {

	private PluginMain plugin;
	
	public MailboxManager(PluginMain plugin) {
		this.plugin = plugin;
	}

	public void loadMailboxes() {
		Set<String> users;
		users = plugin.mailboxesFile.getKeys(false);
		for(String user : users) {
			World checkWorld = plugin.getServer().getWorld(plugin.mailboxesFile.getString(user+".world"));
			Chest check = (Chest) checkWorld.getBlockAt(plugin.mailboxesFile.getInt(user+".x"), plugin.mailboxesFile.getInt(user+".y"), plugin.mailboxesFile.getInt(user+".z")).getState();
			if(check == null) {
				plugin.mcLog.info("[MailBox] "+user+" does not have a valid MailBox, removing!");
				plugin.mailboxesFile.set(user, null);
			}
		}
		
	}
	
	public Chest getMailbox(Player plr) {
		String name = plr.getName();
		if(!plugin.mailboxesFile.contains(name)) {
			return null;
		}
		World mailboxWorld = plugin.getServer().getWorld(plugin.mailboxesFile.getString(name+".world"));
		Chest mailbox = (Chest) mailboxWorld.getBlockAt(plugin.mailboxesFile.getInt(name+".x"), plugin.mailboxesFile.getInt(name+".y"), plugin.mailboxesFile.getInt(name+".z")).getState();
		
		if(mailbox == null) {
			return null;
			//TODO handle invalid chests.
		}
		 return mailbox;
	}
	
	public void setMailbox(Player plr, Chest box) {
		String name = plr.getName();
		plugin.mailboxesFile.set(name+".world", box.getWorld().getName());
		plugin.mailboxesFile.set(name+".x", box.getX());
		plugin.mailboxesFile.set(name+".x", box.getY());
		plugin.mailboxesFile.set(name+".x", box.getZ());
		try {
			plugin.mailboxesFile.save("plugins/"+plugin.getDataFolder()+"mailboxes.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
