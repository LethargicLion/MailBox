package net.lethargiclion.mailbox;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
	
	public Logger mcLog = Logger.getLogger("Minecraft");
	public FileConfiguration mailboxesFile;
	public MailboxManager mgr = new MailboxManager(this);
	public InventoryListener listener = new InventoryListener(this);
	
	@Override
	public void onEnable() {
		mcLog.info("[MailBox] Mailbox version "+getDescription().getVersion()+" enabled!");
		if(!(new File(getDataFolder(),"mailboxes.yml").exists())) {
			mcLog.info("[MailBox] mailboxes.yml does not exist, creating it!");
			try {
				getDataFolder().mkdir();
				File b;
				b = new File(getDataFolder(),"mailboxes.yml");
				b.createNewFile();
			} catch (Exception e) {
				mcLog.info("[MailBox] Unable to create config file!!");
				e.printStackTrace();
				setEnabled(false);
			}
		}  
			
		try {
				File b;
				b = new File(getDataFolder(),"mailboxes.yml");
				mailboxesFile = YamlConfiguration.loadConfiguration(b);
			} catch (Exception e) {
				mcLog.info("[MailBox] Unable to load config file!!");
				e.printStackTrace();
				setEnabled(false);
			}
		
		mgr.loadMailboxes();
		getCommand("sendmail").setExecutor(new MailSender(this));
		getCommand("setmailbox").setExecutor(new MailboxSetter(this));
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	}

}
