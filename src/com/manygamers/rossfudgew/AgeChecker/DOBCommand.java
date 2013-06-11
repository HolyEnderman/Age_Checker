package com.manygamers.rossfudgew.AgeChecker;

import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DOBCommand implements CommandExecutor {

	private App plugin;

	public DOBCommand(App plugin) {
		this.plugin = plugin;
	}

	Player player;
	String name;

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("DOB") && sender instanceof Player) {
			player = (Player) sender;
			name = player.getName();
			Date now = new Date();
			Date their = new Date();
			String dob1;
			try {
				dob1 = args[0];
				char y1;
				char y2;
				char y3;
				char y4;
				char m1;
				char m2;
				char d1;
				char d2;
				{
				 {
					y1 = dob1.charAt(6);
					y2 = dob1.charAt(7);
					y3 = dob1.charAt(8);
					y4 = dob1.charAt(9);
					m1 = dob1.charAt(0);
					m2 = dob1.charAt(1);
					d1 = dob1.charAt(3);
					d2 = dob1.charAt(4);
					String y5 = Character.toString(y1);
					String y6 = Character.toString(y2);
					String y7 = Character.toString(y3);
					String y8 = Character.toString(y4);
					String m3 = Character.toString(m1);
					String m4 = Character.toString(m2);
					String d3 = Character.toString(d1);
					String d4 = Character.toString(d2);
					String year2 = y5 + y6 + y7 + y8;
					String month2 = m3 + m4;
					String date2 = d3 + d4;
					int year3 = Integer.parseInt(year2) - 1900;
					int month3 = Integer.parseInt(month2) - 1;
					int date3 = Integer.parseInt(date2);
					their.setYear(year3);
					their.setMonth(month3);
					their.setDate(date3);
					int year4 = now.getYear() - App.age;
					// plugin.getServer().getLogger().info("the year=" + year4);
					// plugin.getServer().getLogger().info("their year=" +
					// their.getYear());
					int month4 = now.getMonth();
					int date4 = now.getDate();
					App.DOB.put(name, their);
					if (App.allowed.get(name) == null) {
						if (year4 > year3) {
							App.allowed.put(name, true);
							if (App.success.equalsIgnoreCase("default")) {
								defaults();
							}
							if (!App.success.equalsIgnoreCase("default")) {
								nons();
							}
						} else if (year4 < year3) {
							App.allowed.put(name, false);
							if (App.failure.equalsIgnoreCase("default")) {
								defaultf();
							}
							if (!App.failure.equalsIgnoreCase("default")) {
								nonf();
							}
						} else if (year4 == year3) {
							if (month4 > month3) {
								App.allowed.put(name, true);
								if (App.success.equalsIgnoreCase("default")) {
									defaults();
								}
								if (!App.success.equalsIgnoreCase("default")) {
									nons();
								}
							} else if (month4 < month3) {
								App.allowed.put(name, false);
								if (App.failure.equalsIgnoreCase("default")) {
									defaultf();
								}
								if (!App.failure.equalsIgnoreCase("default")) {
									nonf();
								}
							} else if (month4 == month3) {
								if (date4 >= date3) {
									App.allowed.put(name, true);
									if (App.success.equalsIgnoreCase("default")) {
										defaults();
									}
									if (!App.success.equalsIgnoreCase("default")) {
										nons();
									}
								} else if (date4 < date3) {
									App.allowed.put(name, false);
									if (App.failure.equalsIgnoreCase("default")) {
										defaultf();
									}
									if (!App.failure.equalsIgnoreCase("default")) {
										nonf();
									}
								}
							}
						}
					}
				}

				
				} 
			}
			catch (ArrayIndexOutOfBoundsException e) {
				player.sendMessage( ChatColor.GREEN + "Oops, you did something wrong. Use ./help dob for more info");
			}
			catch (StringIndexOutOfBoundsException e) {
				  player.sendMessage( ChatColor.DARK_GREEN + "Input your birthday using the specified mm/dd/yyyy format. Be sure to put 0 infront of single digits.");
			  }

			}
			return true;
	        
		
	}

	void defaults() {
		player.sendMessage("You are old enough to play on this server");
	}

	void defaultf() {
		player.kickPlayer("You are not old enough to play on this server");
	}

	void nons() {
		CommandSender sender1 = plugin.getServer().getConsoleSender();
		String cmds = App.success.replace("%target%", name);
		player.sendMessage(App.successmsg);
		plugin.getServer().dispatchCommand(sender1, cmds);
	}

	void nonf() {
		CommandSender sender1 = plugin.getServer().getConsoleSender();
		String cmds = App.failure.replace("%target%", name);
		player.sendMessage(App.failuremsg);
		plugin.getServer().dispatchCommand(sender1, cmds);
	}
}
