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

package io.github.andersonstv.dice;


import com.bernardomg.tabletop.dice.Dice;
import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.history.RollResult;
import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;
import io.github.andersonstv.Util;

public class DiceController {
    final private DiceParser parser;
    final private DiceRoller roller;


    public DiceController() {
        parser = new DefaultDiceParser();
        roller = new DiceRoller();
    }

    public Iterable<RollResult> parseAndRoll(String expression){
        RollHistory history = roller.transform(parser.parse(expression));
        return history.getRollResults();
    }
    private String printAllRolls(Iterable<RollResult> results){
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

    public String simpleExpression(String messageContent){
        String input = messageContent.replace(" ", "");
        String result;
        result = simpleRoll(input.replace("$roll", ""));
        return result;
    }
    public String simpleRoll(String expression){
        Iterable<RollResult> results = parseAndRoll(expression);

        StringBuilder response = new StringBuilder("**Result:** ");
        response.append(printAllRolls(results));

        if (response.length() >= 2000){
            response = new StringBuilder("Error: Result surpasses Discord character limit");
        }
        return response.toString();
    }
    
    public String wodExpression(String messageContent){
        String result;
        String[] inputArray = messageContent.split(" ");

        if (inputArray.length >= 2 && Util.isInteger(inputArray[1])){
            int quantity = Integer.parseInt(inputArray[1]);
            int difficulty;
            if (inputArray.length == 3 && Util.isInteger(inputArray[2])){
                difficulty = Integer.parseInt(inputArray[2]);
            } else{
                difficulty = 8;
            }
            result = wodRoll(quantity, difficulty);
        } else {
            result = "Invalid Input: Try $wod <number of dice> <success difficulty>." +
                    "Ex: $wod 6 8";
        }
        return result;
    }
    public String wodRoll(int quantity, int difficulty){
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
        response.append(Util.sep).append("**Total Successes:** ").append(countSuccess - countFail);
        response.append(Util.sep).append("**Successes:** ").append(countSuccess);
        response.append(Util.sep).append("**Failures:** ").append(countFail);
        return response.toString();
    }
    public String cocExpression(String messageContent){
        String result;
        String[] inputArray = messageContent.split(" ");

        if (inputArray.length == 2 && Util.isInteger(inputArray[1])){
            int challenge = Integer.parseInt(inputArray[1]);
            result = cocRoll(challenge);
        } else {
            result = "Invalid Input: Try $coc <skill level>." +
                    "Ex: $cod 45 ";
        }
        return result;
    }
    public String cocRoll(int challenge){
        RollResult result = parseAndRoll("1d100").iterator().next();
        int total = result.getTotalRoll();
        int fumble = challenge < 50 ? 96 : 100;
        StringBuilder response = new StringBuilder("**Results:** ");
        response.append(result.getAllRolls()).append(Util.sep);

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
