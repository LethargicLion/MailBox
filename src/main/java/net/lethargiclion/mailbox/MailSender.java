package net.lethargiclion.mailbox;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.bananaco.rules.util.Book;
import de.bananaco.rules.util.CraftBookBuilder;

public class MailSender implements CommandExecutor {
	
	private PluginMain plugin;
	public MailSender(PluginMain plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(command.getName().equalsIgnoreCase("sendmail")) {
			if(!(args.length < 0)) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("[MailBox] You need to be a player to run this command!");
					return false;
				}
				Player plr = (Player) sender;
				if(plr.getItemInHand().getType() != Material.WRITTEN_BOOK) {
					plr.sendMessage(ChatColor.RED+"[MailBox] That needs to be a written book!");
					return false;
				}
				ItemStack book = plr.getItemInHand();
				@SuppressWarnings("unused")
				Book b = new CraftBookBuilder().getBook(book);
				Player target = plugin.getServer().getPlayer(args[0]);
				if(target == null) {
					plr.sendMessage(ChatColor.RED+"[MailBox] "+args[0]+" is not a player!");
					return false;
				}
				Chest targetBox = plugin.mgr.getMailbox(target);
				if(targetBox == null) {
					plr.sendMessage(ChatColor.RED+"[MailBox] "+target.getDisplayName()+" does not have a MailBox!");
					return false;
				}
				Inventory mailspace = targetBox.getInventory();
				if(mailspace.firstEmpty() == -1) {
					plr.sendMessage(ChatColor.RED+"[MailBox] Target mailbox is full!");
					return false;
				}
				//Finally, we can actually send the book.
				mailspace.addItem(book);
				return true;
			}
		}
		return false;
	}

}
