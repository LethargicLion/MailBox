package net.lethargiclion.mailbox;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
	
	public Logger mcLog = Logger.getLogger("Minecraft");
	public FileConfiguration mailboxesFile = null;
	public MailboxManager mgr = new MailboxManager(this);
	
	@Override
	public void onEnable() {
		mcLog.info("[MailBox] Mailbox version "+getDescription().getVersion()+" enabled!");
		if(!(new File("plugins/"+getDataFolder()+"mailboxes.yml").exists())) {
			mcLog.info("[MailBox] mailboxes.yml does not exist, creating it!");
			try {
				File b;
				b = new File("plugins/"+getDataFolder()+"mailboxes.yml");
				b.createNewFile();
				mailboxesFile.load(b);
			} catch (Exception e) {
				mcLog.info("[MailBox] Unable to create config file!!");
				setEnabled(false);
			}
		} else {
			try {
				File b;
				b = new File("plugins/"+getDataFolder()+"mailboxes.yml");
				mailboxesFile.load(b);
			} catch (Exception e) {
				mcLog.info("[MailBox] Unable to create config file!!");
				setEnabled(false);
			}
		}
		mgr.loadMailboxes();
		getCommand("sendmail").setExecutor(new MailSender(this));
		getCommand("setmailbox").setExecutor(new MailboxSetter(this));
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	}

}
