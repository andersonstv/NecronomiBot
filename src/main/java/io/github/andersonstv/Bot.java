package io.github.andersonstv;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import io.github.cdimascio.dotenv.*;

public class Bot {
    public static void main(String[] args) throws Exception{
        Dotenv dotenv = Dotenv.load();
        JDA api = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN")).build();

    }
}
