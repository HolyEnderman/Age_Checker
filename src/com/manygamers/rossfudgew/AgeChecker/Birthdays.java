package com.manygamers.rossfudgew.AgeChecker;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Birthdays implements CommandExecutor {
	

	private App plugin;

	public Birthdays(App plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Birthdays") && sender instanceof Player) {
			int size = App.DOB.size();
			String[] message = new String[size];
			for (String key : App.DOB.keySet()) {
				int array = 0;
				Date date = App.DOB.get(key);
				Date now = new Date();
				int d1 = now.getDate();
				int d2 = date.getDate();
				int m1 = now.getMonth();
				int m2 = date.getMonth();
				int y1 = now.getYear();
				int y2 = date.getYear();
				if (m1 == m2) {
					if (d1 == d2) {
						if (App.birthdaysage == true) {
							int age = y1 - y2;
							String age1 = "(" + age + ")";
							message[array] = key+age1+", ";
						} else if (App.birthdaysage == false) {
							message[array] = key+", ";

						}
					}
				}
				array++;
			}
			if(message[0]!=null){
				sender.sendMessage(message);
			}else if(message[0] == null){
				sender.sendMessage("No birthdays today");
			}
		}
		return true;
	}
}
