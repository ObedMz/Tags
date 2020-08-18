package obed.me.tags.Commands;

import obed.me.tags.Manager.ConfigManager;
import obed.me.tags.Tags;
import org.bukkit.entity.Player;

public abstract class Subcomands {
    protected Tags plugin = Tags.get();
    protected ConfigManager config = new ConfigManager();
    public Subcomands() {
    }

    public abstract void onCommand(Player p, String[] args);
    public abstract String name();
    public abstract String info();
    public abstract String[] aliases();
}
