package net.lethargiclion.mailbox;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Material;
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
			Block block = checkWorld.getBlockAt(plugin.mailboxesFile.getInt(user+".x"), plugin.mailboxesFile.getInt(user+".y"), plugin.mailboxesFile.getInt(user+".z"));
			Chest check = null;
			if(block.getType() == Material.CHEST) {
				check = (Chest) block.getState();
			}
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
		Block block =  mailboxWorld.getBlockAt(plugin.mailboxesFile.getInt(name+".x"), plugin.mailboxesFile.getInt(name+".y"), plugin.mailboxesFile.getInt(name+".z"));
		Chest mailbox = null;
		
		if (block.getType() == Material.CHEST) {
			mailbox = (Chest)block.getState();
		}
		
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
		plugin.mailboxesFile.set(name+".y", box.getY());
		plugin.mailboxesFile.set(name+".z", box.getZ());
		try {
			plugin.mailboxesFile.save(new File(plugin.getDataFolder(),"mailboxes.yml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
