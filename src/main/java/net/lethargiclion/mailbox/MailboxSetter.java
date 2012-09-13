package net.lethargiclion.mailbox;

import org.bukkit.ChatColor;
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
			sender.sendMessage(ChatColor.GREEN+"[MailBox] Please open the chest you want to set.");
			plugin.listener.activePlayers.add(sender.getName());
			return true;
		}
		return false;
	}

}
