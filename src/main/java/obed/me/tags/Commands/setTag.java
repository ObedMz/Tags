package obed.me.tags.Commands;

import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class setTag extends Subcomands{
    @Override
    public void onCommand(Player p, String[] args) {
        if(!p.hasPermission(Tags.getAdmin_permission())){
            p.sendMessage(plugin.getMessageFromConfig("message.permissions.no-permission"));
            return;
        }
        if(args.length <=1){
            p.sendMessage(plugin.getMessageFromConfig("message.set.arguments"));
            return;
        }
        String player = args[0];
        String tag = Tags.getColor_default() + args[1] + "&r";

        config.getConfigPlayer().set("players." + player, tag);
        p.sendMessage(plugin.getMessageFromConfig("message.set.success")
        .replaceAll("%player%", player)
        .replaceAll("%tag%", ChatColor.translateAlternateColorCodes('&', tag)));

        config.saveConfigPlayer();
    }

    @Override
    public String name() {
        return plugin.command.set;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/" + Tags.getCommandlabel() + " set <player> <tag>      &7Cambia el tag de otro jugador.");

    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
