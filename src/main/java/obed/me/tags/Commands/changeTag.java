package obed.me.tags.Commands;

import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class changeTag extends Subcomands {
    @Override
    public void onCommand(Player p, String[] args) {
        if(args.length < 1){
            p.sendMessage(plugin.getMessageFromConfig("message.change.arguments"));
            return;
        }
        if(config.getConfigPlayer().getString("players." + p.getName()) == null
        || config.getConfigPlayer().getString("players." + p.getName()).equals("")){
            p.sendMessage(plugin.getMessageFromConfig("message.change.error.no-tag"));
            return;
        }
        String tag = args[0];
        if(tag.length() > Tags.getMax_lengh()){
            p.sendMessage( plugin.getMessageFromConfig("message.create.error.long"));
            return;
        }
        if(plugin.getBlacklist().contains(tag)){
            p.sendMessage( plugin.getMessageFromConfig("message.create.error.invalid"));
            return;
        }

        if(!p.hasPermission(Tags.getColored_permission())){
            if(tag.contains("&")){
                p.sendMessage( plugin.getMessageFromConfig("message.permissions.color-permission"));
            }
            tag = Tags.getColor_default() + ChatColor.stripColor(tag);
        }
        config.getConfigPlayer().set("players." + p.getName(),tag);
        p.sendMessage(plugin.getMessageFromConfig("message.change.success"));
        config.saveConfigPlayer();
    }

    @Override
    public String name() {
        return plugin.command.change;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/" + Tags.getCommandlabel() + " change <tag>      &7Cambia tu tag.");
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
