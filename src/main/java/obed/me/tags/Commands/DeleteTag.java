package obed.me.tags.Commands;

import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DeleteTag extends Subcomands {
    @Override
    public void onCommand(Player p, String[] args) {
        if(args.length < 1){
            if(config.getConfigPlayer().getString("players." + p.getName()) != null){
                config.getConfigPlayer().set("players." + p.getName(), null);
                p.sendMessage(plugin.getMessageFromConfig("message.delete.success"));
                config.saveConfigPlayer();
                return;
            }
            p.sendMessage(plugin.getMessageFromConfig("message.delete.error"));
            return;
        }
        //admin use
        if(!p.hasPermission(Tags.getAdmin_permission())){
            p.sendMessage(plugin.getMessageFromConfig("message.permissions.no-permission"));
            return;
        }
        String jugador = args[0];

        if(config.getConfigPlayer().getString("players." + jugador) == null || config.getConfigPlayer().getString("players." + jugador).equals("")){
            p.sendMessage(plugin.getMessageFromConfig("message.delete.error.no_tag"));
            return;
        }

        config.getConfigPlayer().set("players." + p.getName(), null);
        p.sendMessage(plugin.getMessageFromConfig("message.delete.success_other")
                .replaceAll("%player%" , jugador));
        config.saveConfigPlayer();
    }

    @Override
    public String name() {
        return plugin.command.delete;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/" + Tags.getCommandlabel() + " delete <player>      &7Elimina tu propio tag o de otros.");

    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
