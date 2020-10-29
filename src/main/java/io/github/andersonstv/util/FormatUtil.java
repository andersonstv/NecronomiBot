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

package io.github.andersonstv.util;



public class FormatUtil {
    final public static String integerRegex = "^\\d+$";
    final public static String quotesRegex = "\"([^\"]*)\"";
    final public static String sep = System.lineSeparator();

    public static boolean isInteger(String str){
        return str.matches(integerRegex);
    }
    public static String validateDiscordLimit(String response){
        if (response.length() >= 2000){
            return "Response exceedds Discord character limit";
        }else {
            return response;
        }
    }
}
