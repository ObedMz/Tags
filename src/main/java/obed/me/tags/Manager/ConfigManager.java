package obed.me.tags.Manager;

import obed.me.tags.Tags;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigManager {
    private Tags plugin = Tags.get();
    private FileConfiguration config = null;
    private File configFile = null;
    public FileConfiguration message = null;
    private File messageFile = null;

    private FileConfiguration configplayer = null;
    private File playerFile = null;


    //config
    public FileConfiguration getMessage() {
        if(message == null) {
            reloadMessage();
        }
        return message;
    }

    public void reloadMessage(){
        if(message == null) {
            messageFile = new File(plugin.getDataFolder(), "message.yml");
        }

        message = YamlConfiguration.loadConfiguration(messageFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(plugin.getResource("message.yml"), "UTF8");
            if(defConfigStream !=null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                message.setDefaults(defConfig);
            }

        }catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public void saveMessage() {
        try {
            message.save(messageFile);
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void registerMessage() {
        messageFile = new File(plugin.getDataFolder(), "message.yml");
        if(!messageFile.exists()) {
            this.getMessage().options().copyDefaults(true);
            saveMessage();
        }
    }

    // config.yml
    public FileConfiguration getConfig() {
        if(config == null) {
            reloadConfig();
        }
        return config;
    }

    public void reloadConfig(){
        if(config == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(plugin.getResource("config.yml"), "UTF8");
            if(defConfigStream !=null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                config.setDefaults(defConfig);
            }

        }catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public void saveConfig() {
        try {
            config.save(configFile);
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void registerConfig() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }


    // player tag.yml

    public FileConfiguration getConfigPlayer() {
        if(configplayer == null) {
            reloadConfigPlayer();
        }
        return configplayer;
    }

    public void reloadConfigPlayer(){
        if(configplayer == null) {
            playerFile = new File(plugin.getDataFolder(), "players_tag.yml");
        }

        configplayer = YamlConfiguration.loadConfiguration(playerFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(plugin.getResource("players_tag.yml"), "UTF8");
            if(defConfigStream !=null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                configplayer.setDefaults(defConfig);
            }

        }catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public void saveConfigPlayer() {
        try {
            configplayer.save(playerFile);
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void registerConfigPlayer() {
        playerFile = new File(plugin.getDataFolder(), "players_tag.yml");
        if(!playerFile.exists()) {
            this.getConfigPlayer().options().copyDefaults(true);
            saveConfigPlayer();
        }
    }


}
