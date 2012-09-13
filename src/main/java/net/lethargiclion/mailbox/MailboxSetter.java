package net.lethargiclion.mailbox;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MailboxSetter implements CommandExecutor {
	
	PluginMain plugin;
	public MailboxSetter(PluginMain plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(command.getName().equalsIgnoreCase("setmailbox")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("[MailBox] You need to be a player!");
				return false;
			}
			Player plr = (Player) sender;
			Block target = plr.getTargetBlock(null, 100);
			Chest targetChest = (Chest) target.getState();
			if(targetChest == null) {
				plr.sendMessage(ChatColor.RED+"[MailBox] That is not a chest!");
				return false;
			}
			
			plugin.mgr.setMailbox(plr, targetChest);
			plr.sendMessage(ChatColor.GREEN+"[MailBox] Mailbox set!");
			return true;
		}
		return false;
	}

}
