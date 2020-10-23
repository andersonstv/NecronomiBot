package io.github.andersonstv;

import java.util.Arrays;
import java.util.Random;

public class DiceController {
    public String rollExpression(String input){
        String result;
        int diceAmount;
        int diceSides;
        String[] expression = input.split("d");
        String regex = "^[0-9]*[1-9][0-9]*$";
        if (expression.length != 2){
            return "**Invalid Input:** Dice Expression does not match XdY format.";
        }
        String firstTerm = expression[0];
        String secondTerm = expression[1];
        if( firstTerm.equals("") && secondTerm.matches(regex)){
            diceAmount = 1;
            diceSides = Integer.parseInt(expression[1]);
        } else if (firstTerm.matches(regex) && secondTerm.matches(regex)){
            diceAmount = Integer.parseInt(expression[0]);
            diceSides = Integer.parseInt(expression[1]);
        } else {
            return "**Invalid Input:** Dice Expression does not match XdY format.";
        }

        int sum = 0;
        Integer[] rollSum = roll(diceAmount, diceSides);
        for (int i = 0; i < rollSum.length; i++) {
            sum += rollSum[i];
        }
        result = "**Result:** " + Arrays.toString(rollSum) + System.lineSeparator() + "**Total:** " + sum;
        if (result.length() > 2000){
            Integer[] reducedRolls = Arrays.copyOf(rollSum, 20);
            result = "**Result:** " + Arrays.toString(reducedRolls) + "..." + System.lineSeparator() + "**Total:** " + sum;
        }
        return result;
    }
    public Integer[] roll(int quant, int sides){
        Integer[] result = new Integer[quant];
        Random generator = new Random();
        for (int i = 0; i < quant; i++) {
            result[i] = generator.nextInt(sides) + 1;
        }
        return result;
    }
}
