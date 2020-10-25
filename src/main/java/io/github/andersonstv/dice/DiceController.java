package io.github.andersonstv.dice;

import com.bernardomg.tabletop.dice.DefaultDice;
import com.bernardomg.tabletop.dice.Dice;
import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.history.RollResult;
import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;

public class DiceController {
    final private DiceParser parser;
    final private DiceRoller roller;
    final public String sep;

    public DiceController() {
        parser = new DefaultDiceParser();
        roller = new DiceRoller();
        sep = System.lineSeparator();
    }

    public Iterable<RollResult> parseAndRoll(String expression){
        RollHistory history = roller.transform(parser.parse(expression));
        return history.getRollResults();
    }
    public String simpleRoll(String input){
        Iterable<RollResult> results = parseAndRoll(input);

        StringBuilder response = new StringBuilder("**Result:** ");
        response.append(printAllRolls(results));

        if (response.length() >= 2000){
            response = new StringBuilder("Error: Result surpasses Discord character limit");
        }
        return response.toString();
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

    public String nwodRoll(int quantity, int difficulty){
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
        response.append(sep).append("**Total Successes:** ").append(countSuccess - countFail);
        response.append(sep).append("**Successes:** ").append(countSuccess);
        response.append(sep).append("**Failures:** ").append(countFail);
        return response.toString();
    }
    public String cocRoll(int challenge){
        RollResult result = parseAndRoll("1d100").iterator().next();
        int total = result.getTotalRoll();
        int fumble = challenge < 50 ? 96 : 100;
        StringBuilder response = new StringBuilder("**Results:** ");
        response.append(result.getAllRolls()).append(sep);
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
