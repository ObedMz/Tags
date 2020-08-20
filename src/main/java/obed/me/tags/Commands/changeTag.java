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
        config.reloadConfigPlayer();
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
        boolean validate = true;
        String Mtag = tag;
        for(ChatColor c : ChatColor.values()){
            Mtag =  Mtag.replace("&" + c.getChar(), "");
        }
        for(String value : plugin.getBlacklist()){

            if(ChatColor.stripColor(value).equalsIgnoreCase(Mtag)){
                p.sendMessage( plugin.getMessageFromConfig("message.create.error.invalid"));
                validate = false;
                return;
            }
        }
        if(!validate){
            return;
        }
        String newtag = tag;
        if(!p.hasPermission(Tags.getColored_permission())){
            if(tag.contains("&")){
                p.sendMessage( plugin.getMessageFromConfig("message.permissions.color-permission"));
                return;
            }
            newtag = Tags.getColor_default() + ChatColor.stripColor(tag);

        }
        config.getConfigPlayer().set("players." + p.getName(),newtag);
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
