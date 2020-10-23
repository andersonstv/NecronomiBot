package io.github.andersonstv;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildListener extends ListenerAdapter {
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        MessageChannel channel = event.getGuild().getSystemChannel();
        String sep = System.lineSeparator();
        String introMessage = "*That is not dead which can eternal lie." + sep +
                "And with strange aeons even death may die.*" + sep +
                "Hello, I'm NecronomiBot, a bot made to help running Call of Cthulhu (and other RPGs) " +
                "on Discord. You can check out the source code at: " +
                "https://github.com/andersonstv/NecronomiBot";
        if (channel != null){
            channel.sendMessage(introMessage).queue();
        }
    }
}
