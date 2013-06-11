package com.manygamers.rossfudgew.AgeChecker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BypassCommand implements CommandExecutor {

	private App plugin;

	public BypassCommand(App plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bypass") && sender instanceof Player && args.length == 2) {
			String perm = "AgeChecker.bypass";
			if (sender.hasPermission(perm) || sender.isOp()) {
				String target = args[0];
				String set1 = args[1];
				if (set1 == "true"){
					App.allowed.put(target, true);
					sender.sendMessage(target+" has been allowed to bypass the age gate");
					if (App.success == "default") {
					} else if (App.success != "default") {
						CommandSender sender1 = plugin.getServer().getConsoleSender();
						String cmds = App.success.replace("%target%", target);
						plugin.getServer().dispatchCommand(sender1, cmds);
					}
				} else if (set1 == "false"){
					App.allowed.put(target, false);
					sender.sendMessage(target+" has to pass the age gate");
					if (App.failure == "default") {
					} else if (App.failure != "default") {
						CommandSender sender1 = plugin.getServer().getConsoleSender();
						String cmds = App.failure.replace("%target%", target);
						plugin.getServer().dispatchCommand(sender1, cmds);

					}
				} else if (set1 != "true" || set1 != "false"){
					sender.sendMessage("Invalid option! please use either true or false");
				}

			}
		}
		return true;
	}

}
