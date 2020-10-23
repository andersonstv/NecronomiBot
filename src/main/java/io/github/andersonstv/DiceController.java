package io.github.andersonstv;

import java.util.Arrays;
import java.util.Random;

public class DiceController {
    public String rollExpression(String input){
        String result;
        int diceAmount;
        int diceSides;
        String[] expression = input.split("d");
        if (expression.length != 2){
            return "**Invalid Input:** Dice Expression does not match XdY format.";
        } else if( expression[0].equals("")){
            diceAmount = 1;
            diceSides = Integer.parseInt(expression[1]);
        } else{
            diceAmount = Integer.parseInt(expression[0]);
            diceSides = Integer.parseInt(expression[1]);
        }

        int sum = 0;
        Integer[] rollSum = roll(diceAmount, diceSides);
        for (int i = 0; i < rollSum.length; i++) {
            sum += rollSum[i];
        }
        result = "**Result:** " + Arrays.toString(rollSum) + System.lineSeparator() + "**Total:** " + sum;

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
