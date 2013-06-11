package com.manygamers.rossfudgew.AgeChecker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Birthday implements CommandExecutor {

	private App plugin;

	public Birthday(App plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Birthday") && sender instanceof Player) {
			if (args.length == 0) {
				return false;
			} else if (args.length == 1) {
				if (sender.isOp()){
					String key = args[0];
					if (App.DOB.containsKey(key)) {
						int date = App.DOB.get(key).getDate();
						int month = App.DOB.get(key).getMonth();
						int year = App.DOB.get(key).getYear()+1900;
						String month1 = null;
						if (month == 0) {
							month1 = "January";
						} else if (month == 1) {
							month1 = "February";
						} else if (month == 2) {
							month1 = "March";
						} else if (month == 3) {
							month1 = "April";
						} else if (month == 4) {
							month1 = "May";
						} else if (month == 5) {
							month1 = "June";
						} else if (month == 6) {
							month1 = "July";
						} else if (month == 7) {
							month1 = "August";
						} else if (month == 8) {
							month1 = "September";
						} else if (month == 9) {
							month1 = "October";
						} else if (month == 10) {
							month1 = "November";
						} else if (month == 11) {
							month1 = "December";
						}
						String message = date + " of " + month1 +" "+ year;
						sender.sendMessage(key + "'s birthday is " + message);
					}
				}else if (!sender.isOp()){
					String key = args[0];
					if (App.DOB.containsKey(key)) {
						int date = App.DOB.get(key).getDate();
						int month = App.DOB.get(key).getMonth();
						String month1 = null;
						if (month == 0) {
							month1 = "January";
						} else if (month == 1) {
							month1 = "February";
						} else if (month == 2) {
							month1 = "March";
						} else if (month == 3) {
							month1 = "April";
						} else if (month == 4) {
							month1 = "May";
						} else if (month == 5) {
							month1 = "June";
						} else if (month == 6) {
							month1 = "July";
						} else if (month == 7) {
							month1 = "August";
						} else if (month == 8) {
							month1 = "September";
						} else if (month == 9) {
							month1 = "October";
						} else if (month == 10) {
							month1 = "November";
						} else if (month == 11) {
							month1 = "December";
						}
						String message = date + " of " + month1;
						sender.sendMessage(key + "'s birthday is " + message);
					}
				}
				
			}

		}
		return true;
	}

}
