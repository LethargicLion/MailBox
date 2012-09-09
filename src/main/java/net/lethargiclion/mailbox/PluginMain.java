package net.lethargiclion.mailbox;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
	
	public Logger mcLog = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		mcLog.info("[MailBox] Mailbox version "+getDescription().getVersion()+" enabled!");
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	}

}
