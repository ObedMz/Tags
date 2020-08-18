package obed.me.tags.Commands;

import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class reloadTag extends Subcomands {
    @Override
    public void onCommand(Player p, String[] args) {
        if(!p.hasPermission(Tags.getAdmin_permission())){
            p.sendMessage(plugin.getMessageFromConfig("message.permissions.no-permission"));
            return;
        }

        config.reloadConfig();
        config.reloadConfigPlayer();
        config.reloadMessage();
        p.sendMessage(plugin.getMessageFromConfig("message.reload.success"));
    }

    @Override
    public String name() {
        return plugin.command.reload;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/" + Tags.getCommandlabel() + " reload      &7Recarga toda la configuracion.");

    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
