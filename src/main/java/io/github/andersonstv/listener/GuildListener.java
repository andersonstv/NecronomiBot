/*
 *     NecronomiBot. A Discord Bot for use with RPGs (RolePlaying Games)
 *     Copyright (C) 2020  Anderson dos Santos Silva
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.andersonstv.listener;

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
