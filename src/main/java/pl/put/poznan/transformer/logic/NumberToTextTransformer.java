package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;

public class NumberToTextTransformer extends TransformerDecorator{
    Map<Integer, String> numbersTranslation = new HashMap<Integer, String>();
    List<String> numberSuffixes = new ArrayList<String>();
    Map<Integer, String> specialFractionNumber = new HashMap<Integer, String>();


    public NumberToTextTransformer(Transformer transformer) {
        super(transformer);

        // Add translations to dictionary
        numbersTranslation.put(0, "zero");
        numbersTranslation.put(1, "jeden");
        numbersTranslation.put(2, "dwa");
        numbersTranslation.put(3, "trzy");
        numbersTranslation.put(4, "cztery");
        numbersTranslation.put(5, "pięć");
        numbersTranslation.put(6, "sześć");
        numbersTranslation.put(7, "siedem");
        numbersTranslation.put(8, "osiem");
        numbersTranslation.put(9, "dziewięć");
        numbersTranslation.put(10, "dziesięć");
        numbersTranslation.put(11, "jedenaście");
        numbersTranslation.put(15, "piętnaście");
        numbersTranslation.put(16, "szesnaście");
        numbersTranslation.put(19, "dziewiętnaście");
        numbersTranslation.put(20, "dwadzieścia");
        numbersTranslation.put(30, "trzydzieści");
        numbersTranslation.put(40, "czterdzieści");
        numbersTranslation.put(100, "sto");
        numbersTranslation.put(200, "dwieście");
        numbersTranslation.put(300, "trzysta");
        numbersTranslation.put(400, "czterysta");
        numbersTranslation.put(1000, "tysiąć");

        numberSuffixes.add("naście");
        numberSuffixes.add("dziesiąt");
        numberSuffixes.add("set");
        numberSuffixes.add("dziesiąta");
        numberSuffixes.add("dziesiąte");
        numberSuffixes.add("dziesiątych");
        numberSuffixes.add("setna");
        numberSuffixes.add("setne");
        numberSuffixes.add("setnych");

        specialFractionNumber.put(1, "jedna");
        specialFractionNumber.put(2, "dwie");
    }

    public String transform(String text) {
        StringBuilder newText = new StringBuilder();

        int i = 0;
        while(i < text.length()) {
            if(Character.isDigit(text.charAt(i))) {
                StringBuilder numberText = new StringBuilder();
                while(Character.isDigit(text.charAt(i)) || text.charAt(i) == ',') {
                    numberText.append(text.charAt(i));
                    i++;
                    if(i == text.length()) { break; }
                }
                i--;
                if(numberText.charAt(numberText.length() - 1) == ',') {
                    newText.append(numberToText(numberText.substring(0, numberText.length() - 1))).append(",");
                }
                else newText.append(numberToText(numberText.toString()));
            }
            else {
                newText.append(text.charAt(i));
            }
            i++;
        }
        return newText.toString();
    }

    private String numberToText(String numberText) {
        System.out.println("Changing number: " + numberText);

        StringBuilder newText = new StringBuilder();
        int integerLength = numberText.split(",", 2)[0].length(), fractionLength = 0;
        if(numberText.contains(",")){
            fractionLength = numberText.split(",", 3)[1].length();
        }

        for (int i = 0; i < integerLength; i++) {
            int number = (int) (Character.getNumericValue(numberText.charAt(i)) * pow(10, integerLength - (i + 1)));
            if(number == 0) {
                continue;
            }
            if(number == 10){
                i++;
                number += Character.getNumericValue(numberText.charAt(i));
            }
            newText.append(findTranslation(number));
            newText.append(" ");
        }

        if(fractionLength > 0){
            if(Integer.parseInt(numberText.substring(integerLength + 1)) == 0) {
                int checkZero = Integer.parseInt(numberText.substring(0, integerLength));
                if(checkZero == 0){
                    return numbersTranslation.get(checkZero);
                }
                return newText.toString().trim();
            }
            if(!newText.toString().isEmpty()) {
                newText.append("i ");
            }

            int fraction = 0;
            for (int i = integerLength + 1; i < numberText.length(); i++) {
                int number = (int) (Character.getNumericValue(numberText.charAt(i)) * pow(10, fractionLength - (i - integerLength)));
                if(number == 0){ continue; }

                if(number == 10){
                    i++;
                    number += Character.getNumericValue(numberText.charAt(i));
                }

                fraction += number;

                if(specialFractionNumber.containsKey(number) && fraction < 10) {
                   newText.append(specialFractionNumber.get(number));
                }
                else newText.append(findTranslation(number));
                newText.append(" ");
            }
            if(fractionLength == 1){
                if(fraction == 1) {
                    newText.append(numberSuffixes.get(3));
                }
                else if (fraction <= 4) {
                    newText.append(numberSuffixes.get(4));
                }
                else {
                    newText.append(numberSuffixes.get(5));
                }
            }
            else {
                if(fraction == 1) {
                    newText.append(numberSuffixes.get(6));
                }
                else if (fraction % 10 <= 4 && fraction % 10 > 1) {
                    newText.append(numberSuffixes.get(7));
                }
                else {
                    newText.append(numberSuffixes.get(8));
                };
            }
        }

        return newText.toString().trim();
    }

    private String findTranslation(int number){
        if(numbersTranslation.containsKey(number)) {
            return numbersTranslation.get(number);
        }
        else {
            if(number <= 19){
                return numbersTranslation.get(number % 10) + numberSuffixes.get(0);
            }
            else if(number <= 90){
                return numbersTranslation.get(number / 10) + numberSuffixes.get(1);
            }
            else if(number <= 1000){
                return numbersTranslation.get(number / 100) + numberSuffixes.get(2);
            }
        }
        return String.valueOf(number);
    }
}
