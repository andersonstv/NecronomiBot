package io.github.andersonstv;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import io.github.cdimascio.dotenv.*;

public class BotLauncher {
    public static void main(String[] args) throws Exception{
        Dotenv dotenv = Dotenv.load();
        JDA api = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN")).build();
        registerListener(api);
    }
    static void registerListener(JDA api){
        api.addEventListener(new MessageListener());
    }
}
