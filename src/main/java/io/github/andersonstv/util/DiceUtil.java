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


import com.bernardomg.tabletop.dice.Dice;
import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.history.RollResult;
import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class DiceUtil {

    static private Iterable<RollResult> parseAndRoll(String expression){
        DiceParser parser = new DefaultDiceParser();
        DiceRoller roller = new DiceRoller();

        RollHistory history = roller.transform(parser.parse(expression));
        return history.getRollResults();
    }

    static private String printAllRolls(Iterable<RollResult> results){
        StringBuilder allRolls = new StringBuilder();
        for (RollResult result : results) {
            Dice die = result.getDice();
            allRolls.append(die.getQuantity()).append("d").append(die.getSides());
            allRolls.append(" ").append(result.getAllRolls());
            if (results.iterator().hasNext()){
                allRolls.append(" ");
            }
        }
        return allRolls.toString();
    }

    static public String simple(String messageContent){
        String expression = messageContent.replaceAll("\\$roll| ", "");
        return FormatUtil.validateDiscordLimit(simpleRoll(expression));
    }
    static public String simpleRoll(String expression){
        Iterable<RollResult> results = parseAndRoll(expression);

        StringBuilder response = new StringBuilder("**Result:** ");
        response.append(printAllRolls(results));

        if (response.length() >= 2000){
            response = new StringBuilder("Error: Result surpasses Discord character limit");
        }
        return response.toString();
    }

    static public String wod(String messageContent){
        String result;
        int quantity;
        int difficulty;
        String[] inputArray = messageContent.split(" ");

        switch (inputArray.length) {
            case 3 -> {
                quantity = FormatUtil.isInteger(inputArray[1]) ? Integer.parseInt(inputArray[1]) : 1;
                difficulty = FormatUtil.isInteger(inputArray[2]) ? Integer.parseInt(inputArray[2]) : 8;
                result = wodRoll(quantity, difficulty);
            }
            case 2 -> {
                quantity = FormatUtil.isInteger(inputArray[1]) ? Integer.parseInt(inputArray[1]) : 1;
                result = wodRoll(quantity);
            }
            case 1 -> result = wodRoll(1);
            default -> result = "Invalid Input: Try $wod <number of dice> <success difficulty>." +
                    "Ex: $wod 6 8";
        }
        return result;
    }
    static public String wodRoll(int quantity, int difficulty){
        int countSuccess = 0;
        int countFail = 0;
        StringBuilder response = new StringBuilder("**Result:** ");
        Iterable<RollResult> results = parseAndRoll(quantity + "d10");

        for (RollResult result : results) {
            Dice die = result.getDice();
            response.append(die.getQuantity()).append("d").append(die.getSides());
            response.append(" ").append(result.getAllRolls());
            if (results.iterator().hasNext()){
                response.append(" ");
            }
            Iterable<Integer> allRolls = result.getAllRolls();
            for (Integer roll : allRolls) {
                if (roll >= difficulty){
                    countSuccess += 1;
                } else if( roll <= 1){
                    countFail += 1;
                }
            }
        }
        response.append(FormatUtil.sep).append("**Total Successes:** ").append(countSuccess - countFail);
        response.append(FormatUtil.sep).append("**Successes:** ").append(countSuccess);
        response.append(FormatUtil.sep).append("**Failures:** ").append(countFail);
        return response.toString();
    }
    static public String wodRoll(int quantity){
        return wodRoll(quantity, 8);
    }
    static public String coc(String messageContent){
        String result;
        String[] inputArray = messageContent.split(" ");

        if (inputArray.length == 2 && FormatUtil.isInteger(inputArray[1])){
            int challenge = Integer.parseInt(inputArray[1]);
            result = cocRoll(challenge);
        } else if (inputArray.length == 3 && FormatUtil.isInteger(inputArray[1])){
            int challenge = Integer.parseInt(inputArray[1]);
            result = switch (inputArray[2]) {
                case "+" -> cocRoll(challenge, true);
                case "-" -> cocRoll(challenge, false);
                default -> cocRoll(challenge);
            };
        } else {
            result = "Invalid Input: Try $coc <skill level>." +
                    "Ex: $cod 45 ";
        }
        return result;
    }

    static public String cocRoll(int challenge){
        int fumble = challenge < 50 ? 96 : 100;
        RollResult result = parseAndRoll("2d10").iterator().next();
        Iterator<Integer> iter = result.getAllRolls().iterator();
        int tens = (iter.next() - 1) * 10;
        int units = iter.next() - 1;
        int total = tens + units;
        total = total == 0 ? 100 : total;
        StringBuilder response = new StringBuilder("**Results:** ");
        response.append(total).append(" [").append(tens).append("] [").append(units).append("]").append(FormatUtil.sep);

        return cocString(challenge, fumble, total, response);
    }
    static public String cocRoll(int challenge, boolean bonus){
        int fumble = challenge < 50 ? 96 : 100;
        RollResult result = parseAndRoll("3d10").iterator().next();
        Iterator<Integer> iter = result.getAllRolls().iterator();
        int tens = (iter.next() - 1) * 10;
        int units = iter.next() - 1;
        int bonusDice = (iter.next() - 1) * 10;
        int finalTen;
        if (bonus){
            finalTen = Math.max(tens, bonusDice);
        } else {
            finalTen = Math.min(tens, bonusDice);
        }
        int total = finalTen + units;
        total = total == 0 ? 100 : total;
        StringBuilder response = new StringBuilder("**Results:** ");
        response.append(total).append(" [").append(tens).append("] [").append(units).append("]").append(FormatUtil.sep);
        response.append(" [").append(bonusDice).append("]").append(FormatUtil.sep);
        return cocString(challenge, fumble, total, response);
    }

    private static String cocString(int challenge, int fumble, int total, StringBuilder response) {
        if(total <= challenge){
            if( total == 1){
                response.append("Critical");
            }
            else if (total <= challenge/5){
                response.append("Extreme");
            } else if(total <= challenge/2) {
                response.append("Hard");
            } else{
                response.append("Regular");
            }
            response.append(" Success!");
        } else{
            if (total >= fumble){
                response.append("Fumble!");
            } else {
                response.append("Failure!");
            }
        }
        return response.toString();
    }
}
