package net.lethargiclion.mailbox;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginNotifier implements Listener {
	public List<String> notifyUsers = new ArrayList<String>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		if(notifyUsers.contains(evt.getPlayer().getName())) {
			evt.getPlayer().sendMessage(ChatColor.YELLOW+"[MailBox] You have received mail while you were offline!");
			notifyUsers.remove(evt.getPlayer().getName());
		}
	}

}
