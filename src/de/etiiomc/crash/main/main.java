package de.etiiomc.crash.main;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
  
  public void onEnable()
  {
	config();
    getCommand("crash").setExecutor(new crash(getConfig()));
    getCommand("crashmanager").setExecutor(new crash(getConfig()));
    System.out.println("Crash | Enabled!");
  }
  
  public void config()
  {
    getConfig().options().header("Crash " + getDescription().getVersion() + "by etiiomc");
	getConfig().addDefault("crash.prefix", "[§cCrashManager§f] ");
	getConfig().addDefault("crash.nopermissions", "§aDu hast nicht die Rechte dazu!");
	getConfig().addDefault("crash.notonline", "§aDieser Spieler ist leider nicht online!");
	getConfig().addDefault("crash.done", "§aDu hast nicht die Rechte dazu!");
	getConfig().addDefault("crash.notselv", "§aDieser Spieler ist leider nicht online!");
	getConfig().addDefault("crash.nothim", "etiiomc");
	getConfig().addDefault("crash.nothimerror", "§aDas Spiel von dieser Person darf nicht gecrasht werden!");
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
}