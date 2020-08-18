package obed.me.tags.API;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import obed.me.tags.Manager.ConfigManager;
import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;


public class PlaceHolderAPI  extends PlaceholderExpansion {
    private Tags plugin;
    public PlaceHolderAPI(Tags plugin){
        this.plugin = plugin;
    }
    private ConfigManager config = new ConfigManager();

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        config.reloadConfigPlayer();
        if(identifier.equals("player")){
            if(config.getConfigPlayer().getString("players." + p.getName()) == null){
                return "";
            }
            return ChatColor.translateAlternateColorCodes('&' , config.getConfigPlayer().getString("players." + p.getName()));
        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;

    }
    @Override
    public String onRequest(OfflinePlayer p, String identifier){
        config.reloadConfigPlayer();
        if(identifier.equals("player")){
            if(config.getConfigPlayer().getString("players." + p.getName()) == null){
                return "";
            }
            return ChatColor.translateAlternateColorCodes('&' , config.getConfigPlayer().getString("players." + p.getName()));
        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }

    @Override
    public String getIdentifier() {
        return Tags.getPlaceholderlabel();
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }
    @Override
    public boolean persist(){
        return true;
    }
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }
}
