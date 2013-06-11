package com.manygamers.rossfudgew.AgeChecker;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
	static int age = 13;
	File file = new File("plugins/AgeChecker/config.yml");
	static String error = "An error has happened...... incoming stack trace....";
	static HashMap<String, Date> DOB = new HashMap<String, Date>();
	static HashMap<String, Boolean> allowed = new HashMap<String, Boolean>();
	static boolean lock;
	static String success;
	static String successmsg;
	static String failure;
	static String failuremsg;
	static boolean birthdaysage;
	File dobyaml1 = new File("plugins/AgeChecker/DOB.yml");
	File allowedyaml1 = new File("plugins/AgeChecker/allowed.yml");
	YamlConfiguration dobyaml2 = new YamlConfiguration();
	YamlConfiguration allowedyaml2 = new YamlConfiguration();

	public void onEnable() {
		config();
		getCommand("DOB").setExecutor(new DOBCommand(this));
		getCommand("bypass").setExecutor(new BypassCommand(this));
		getCommand("birthday").setExecutor(new Birthday(this));
		getCommand("birthdays").setExecutor(new Birthdays(this));
		getServer().getPluginManager().registerEvents(new EListener(this), this);
		getLogger().info("Listeners have been enabled");
		dobyaml();
		dobyamls();
		allowedyaml();
		allowedyamls();
		task();
		getLogger().info("Using mm/dd/yyyy as the date format");
		getLogger().info("Plugin enabled");
	}

	public void onDisable() {
		dobyamls();
		allowedyamls();
		getLogger().info("Plugin disabled");
	}

	void errorLogger() {
		getLogger().info(error);
	}

	void dobyaml() {
		dobyaml2 = YamlConfiguration.loadConfiguration(dobyaml1);

		for (String name : dobyaml2.getKeys(true)) {
			String s = dobyaml2.getString(name);
			long l = Date.parse(s);
			Date date = new Date();
			date.setTime(l);
			DOB.put(name, date);
		}

	}

	void allowedyaml() {
		allowedyaml2 = YamlConfiguration.loadConfiguration(allowedyaml1);
		for (String name : allowedyaml2.getKeys(true)) {
			String s = allowedyaml2.getString(name);
			boolean bool = Boolean.parseBoolean(s);
			allowed.put(name, bool);
			getLogger().info(name+" "+bool);
		}

	}

	void dobyamls() {
		for (String key : DOB.keySet()) {
			String value = DOB.get(key).toString().substring(0, 10) + " " + DOB.get(key).toString().substring(24);
			dobyaml2.set(key, value);
		}
		try {
			dobyaml2.save(dobyaml1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void allowedyamls() {
		for (String key : allowed.keySet()) {
			boolean value = allowed.get(key);
			allowedyaml2.set(key, value);
		}
		try {
			allowedyaml2.save(allowedyaml1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void config() {
		this.saveDefaultConfig();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		age = (Integer) config.get("age");
		lock = (Boolean) config.get("lock");
		success = (String) config.get("success");
		successmsg = (String) config.get("successmsg");
		failure = (String) config.get("failure");
		failuremsg = (String) config.get ("Failuremsg");
		birthdaysage = (Boolean) config.get("birthdaysage");
	}

	public void task() {
		for (int count = 0; count > 10000; count++) {
			if (count == 9999) {
				dobyaml();
				dobyamls();
				allowedyaml();
				allowedyamls();
				task();
			} else
				continue;
		}
	}
}
