package obed.me.tags.Commands;

import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class helpTag extends Subcomands {
    @Override
    public void onCommand(Player p, String[] args) {
        if(!p.hasPermission(Tags.getAdmin_permission())){
            p.sendMessage(plugin.getMessageFromConfig("message.permissions.no-permission"));
            return;
        }

        for(Subcomands str : plugin.command.getCommands()){
            p.sendMessage(str.info());
        }

    }

    @Override
    public String name() {
        return plugin.command.help;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/" + Tags.getCommandlabel() + " help      &7Muestra todos los comandos del plugin.");
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
