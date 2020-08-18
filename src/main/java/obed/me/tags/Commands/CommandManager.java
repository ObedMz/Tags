package obed.me.tags.Commands;

import obed.me.tags.Tags;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {
    private static ArrayList<Subcomands> commands = new ArrayList<Subcomands>();
    private Tags plugin = Tags.get();

    public CommandManager(){}

    //SubComands
    public String main;
    public String create = "create";
    public String delete = "delete";
    public String set = "set";
    public String change = "change";
    public String reload = "reload";
    public String help = "help";

    public void setup(){
        main = Tags.getCommandlabel();
        plugin.getCommand(main).setExecutor(this);
        commands.add(new CreateTag());
        commands.add(new DeleteTag());
        commands.add(new setTag());
        commands.add(new changeTag());
        commands.add(new reloadTag());
        commands.add(new helpTag());


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase(main)){
            if(args.length == 0){
                p.sendMessage(plugin.getMessageFromConfig("message.arguments"));
                return true;
            }

        }
        Subcomands target  = this.get(args[0]);
        if(target == null) {
            p.sendMessage(plugin.getMessageFromConfig("message.unknow"));
            return true;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(Arrays.asList(args));
        arrayList.remove(0);
        // declaration and initialise String Array
        String str[] = new String[arrayList.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arrayList.size(); j++) {

            // Assign each value to String array
            str[j] = arrayList.get(j);
        }
        try{

            target.onCommand(p,str);
        }catch (Exception e){
            p.sendMessage(ChatColor.RED+ "An error has ocurred.");
            e.printStackTrace();
        }


        return true;
    }

    private Subcomands get(String name){
        Iterator<Subcomands> subcommands = this.commands.iterator();

        while(subcommands.hasNext()){
            Subcomands sc = (Subcomands) subcommands.next();
            if(sc.name().equalsIgnoreCase(name)){
                return sc;
            }
            String[] aliases;
            int length = (aliases = sc.aliases()).length;
            for(int var5=0;var5 < length; var5++){
                String alias = aliases[var5];
                if(name.equalsIgnoreCase(alias)){
                    return sc;
                }
            }
        }
        return null;


    }

    public ArrayList<Subcomands> getCommands(){
        return commands;
    }

}
