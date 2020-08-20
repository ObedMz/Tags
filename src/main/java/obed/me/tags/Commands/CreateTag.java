package obed.me.tags.Commands;

import net.milkbowl.vault.economy.EconomyResponse;
import obed.me.tags.API.VaultAPI;
import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateTag extends Subcomands{
    @Override
    public void onCommand(Player p, String[] args) {
        //tag create args[0]
        if(!p.hasPermission(Tags.getCreate_permission())){
            p.sendMessage(plugin.getMessageFromConfig("message.permissions.no-permission"));
            return;
        }
        if(args.length < 1){
           p.sendMessage(plugin.getMessageFromConfig("message.create.arguments"));
            return;
        }
        config.reloadConfigPlayer();
        if(config.getConfigPlayer().getString("players." + p.getName()) != null){
            p.sendMessage( plugin.getMessageFromConfig("message.create.error.yet"));
            return;
        }

        String tag = args[0];
        if(ChatColor.stripColor(tag).length() > Tags.getMax_lengh()){
            p.sendMessage( plugin.getMessageFromConfig("message.create.error.long"));
            return;
        }
        if(plugin.getBlacklist().contains(ChatColor.stripColor(tag))){

            p.sendMessage( plugin.getMessageFromConfig("message.create.error.invalid"));
            return;
        }
        String newtag = null;
        if(!p.hasPermission(Tags.getColored_permission())){
            if(tag.contains("&")){
                p.sendMessage( plugin.getMessageFromConfig("message.permissions.color-permission"));
                return;
            }
            newtag = Tags.getColor_default() + ChatColor.stripColor(tag);

        }
       try{
           EconomyResponse ecr = VaultAPI.getEcon().withdrawPlayer(p, Tags.getCost());
           if(ecr.transactionSuccess()){
               config.getConfigPlayer().set("players." + p.getName(), newtag + "&r");
               p.sendMessage(plugin.getMessageFromConfig("message.create.success")
                       .replace("%tag%" , ChatColor.translateAlternateColorCodes('&', newtag)));

               config.saveConfigPlayer();
               config.reloadConfigPlayer();
           } else {
               p.sendMessage( plugin.getMessageFromConfig("message.create.error.money").replaceAll("%money%", Integer.toString(Tags.getCost())));

           }


       }catch (Exception e){
           e.printStackTrace();
       }
    }


    @Override
    public String name() {
        return plugin.command.create;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/" + Tags.getCommandlabel() + " create <tag>      &7Crea tu propio tag.");
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
