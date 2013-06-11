package com.manygamers.rossfudgew.AgeChecker;

import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EListener implements Listener {

	private App plugin;

	public EListener(App plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		String name;
		Player player = event.getPlayer();
		name = player.getName();
		Date now = new Date();
		Date their;
		int year5 = now.getYear();
		now.setYear(year5 - App.age);

		if (App.allowed.get(name) == null &&App.DOB.get(name) == null) {
			player.sendMessage(ChatColor.BOLD + "Please" + ChatColor.UNDERLINE + ChatColor.BLUE + " verify your age" + ChatColor.RESET + " by using the command:" + ChatColor.GOLD + " /DOB"
					+ ChatColor.GREEN + " Your date of birth goes here in the format " + ChatColor.DARK_AQUA + "mm/dd/yyyy" + ChatColor.GREEN + ".");
		} else if (App.allowed.get(name) != null) {
			their = (App.DOB.get(name));
			if (App.allowed.get(name) == true) {
				int date = App.DOB.get(name).getDate();
				int month = App.DOB.get(name).getMonth();
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
				plugin.getServer().broadcastMessage(ChatColor.BLUE + name + "'s birthday is " + ChatColor.YELLOW + message);
			} else if (App.allowed.get(name) == false) {
				int year4 = now.getYear();
				int month4 = now.getMonth();
				int date4 = now.getDate();
				int year3 = their.getYear();
				int month3 = their.getMonth();
				int date3 = their.getDate();
				if (year4 > year3) {
					App.allowed.put(name, true);
					if (App.success.equalsIgnoreCase("default")) {
						player.sendMessage("You are old enough to play on this server");
					} else if (!App.success.equalsIgnoreCase("default")) {
						CommandSender sender1 = plugin.getServer().getConsoleSender();
						String cmds = App.success.replace("%target%", name);
						plugin.getServer().dispatchCommand(sender1, cmds);
					}
				} else if (year4 < year3) {
					App.allowed.put(name, false);
					if (App.failure.equalsIgnoreCase("default")) {
						player.kickPlayer("You are not old enough to play on this server");
					}

				} else if (year4 == year3) {
					if (month4 > month3) {
						App.allowed.put(name, true);
						if (App.success.equalsIgnoreCase("default")) {
							player.sendMessage("You are old enough to play on this server");
						} else if (!App.success.equalsIgnoreCase("default")) {
							CommandSender sender1 = plugin.getServer().getConsoleSender();
							String cmds = App.success.replace("%target%", name);
							plugin.getServer().dispatchCommand(sender1, cmds);
						}
					} else if (month4 < month3) {
						App.allowed.put(name, false);
						if (App.failure.equalsIgnoreCase("default")) {
							player.kickPlayer("You are not old enough to play on this server");
						}

					} else if (month4 == month3) {
						if (date4 >= date3) {
							App.allowed.put(name, true);
							if (App.success.equalsIgnoreCase("default")) {
								player.sendMessage("You are old enough to play on this server");
							} else if (!App.success.equalsIgnoreCase("default")) {
								CommandSender sender1 = plugin.getServer().getConsoleSender();
								String cmds = App.success.replace("%target%", name);
								plugin.getServer().dispatchCommand(sender1, cmds);
							}
						} else if (date4 < date3) {
							App.allowed.put(name, false);
							if (App.failure.equalsIgnoreCase("default")) {
								player.kickPlayer("You are not old enough to play on this server");

							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		if (App.DOB.get(name) == null && App.lock) {
			Location loc = player.getLocation();
			player.teleport(loc);
		}
	}
}
