package obed.me.tags;

import obed.me.tags.API.PlaceHolderAPI;
import obed.me.tags.API.VaultAPI;
import obed.me.tags.Commands.CommandManager;
import obed.me.tags.Manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public final class Tags extends JavaPlugin {
    private static Tags instance;
    private ConfigManager config;
    private VaultAPI vault = new VaultAPI();
    public CommandManager command;
    private static String commandlabel, placeholderlabel, admin_permission, colored_permission, color_default, create_permission;
    private static Integer max_lengh, cost;
    private List<String> blacklist = new ArrayList<String>();




    public void loadConfiguration(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[TAG] Loading config file.");
        setCommandlabel(config.getConfig().getString("config.base_command"));
        setPlaceholderlabel(config.getConfig().getString("config.placeholder"));
        setAdmin_permission(config.getConfig().getString("config.permissions.admin_permission"));
        setColored_permission(config.getConfig().getString("config.permissions.colored_permission"));
        setMax_lengh(config.getConfig().getInt("config.max-length"));
        setCost(config.getConfig().getInt("config.cost"));
        setColor_default(config.getConfig().getString("config.default_color"));
        setBlacklist(config.getConfig().getStringList("config.blacklist"));
        setCreate_permission(config.getConfig().getString("config.permissions.create_permission"));
    }
    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigManager();
        config.registerConfig();
        config.registerMessage();
        config.registerConfigPlayer();
        loadConfiguration();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceHolderAPI(this).register();
        }
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            if(commandlabel == null){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The label command is empty, check the config.yml.");
                Bukkit.shutdown();
            }
            PluginCommand pluginCommand = constructor.newInstance(commandlabel, this);
            pluginCommand.setDescription("Tag base command");
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get((SimplePluginManager) getServer().getPluginManager());
            commandMap.register(getDescription().getName(), pluginCommand);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        vault.setupEconomy();
        command = new CommandManager();
        command.setup();
    }
    public String getMessageFromConfig(String path){
        return ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("message.prefix") +  config.getMessage().getString(path));

    }
    public static Tags get(){
        return instance;
    }
    public static String getCreate_permission() {
        return create_permission;
    }

    public static void setCreate_permission(String create_permission) {
        Tags.create_permission = create_permission;
    }
    public static String getColor_default() { return color_default; }

    public static void setColor_default(String color_default) { Tags.color_default = color_default; }
    public static String getCommandlabel(){
        return commandlabel;
    }

    public static String getPlaceholderlabel() { return placeholderlabel; }

    public static void setPlaceholderlabel(String placeholderlabel) {
        Tags.placeholderlabel = placeholderlabel;
    }

    public static String getAdmin_permission() {
        return admin_permission;
    }

    public static void setAdmin_permission(String admin_permission) {
        Tags.admin_permission = admin_permission;
    }

    public static String getColored_permission() {
        return colored_permission;
    }

    public static void setColored_permission(String colored_permission) {
        Tags.colored_permission = colored_permission;
    }

    public static void setCommandlabel(String commandlabel) {
        Tags.commandlabel = commandlabel;
    }

    public static Integer getMax_lengh() {
        return max_lengh;
    }

    public static void setMax_lengh(Integer max_lengh) {
        Tags.max_lengh = max_lengh;
    }

    public static Integer getCost() {
        return cost;
    }

    public static void setCost(Integer cost) {
        Tags.cost = cost;
    }

    public List<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List<String> blacklist) {
        this.blacklist = blacklist;
    }
}
