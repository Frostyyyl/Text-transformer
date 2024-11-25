package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;

public class NumberToTextTransformer extends TransformerDecorator{
    private static final Map<Integer, String> NUMBER_TRANSLATIONS = new HashMap<Integer, String>();
    private static final List<String> NUMBER_SUFFIXES = new ArrayList<String>();
    private static final Map<Integer, String> SPECIAL_FRACTION_NUMBERS = new HashMap<Integer, String>();
    static {
        // Add translations to dictionaries
        NUMBER_TRANSLATIONS.put(0, "zero");
        NUMBER_TRANSLATIONS.put(1, "jeden");
        NUMBER_TRANSLATIONS.put(2, "dwa");
        NUMBER_TRANSLATIONS.put(3, "trzy");
        NUMBER_TRANSLATIONS.put(4, "cztery");
        NUMBER_TRANSLATIONS.put(5, "pięć");
        NUMBER_TRANSLATIONS.put(6, "sześć");
        NUMBER_TRANSLATIONS.put(7, "siedem");
        NUMBER_TRANSLATIONS.put(8, "osiem");
        NUMBER_TRANSLATIONS.put(9, "dziewięć");
        NUMBER_TRANSLATIONS.put(10, "dziesięć");
        NUMBER_TRANSLATIONS.put(11, "jedenaście");
        NUMBER_TRANSLATIONS.put(15, "piętnaście");
        NUMBER_TRANSLATIONS.put(16, "szesnaście");
        NUMBER_TRANSLATIONS.put(19, "dziewiętnaście");
        NUMBER_TRANSLATIONS.put(20, "dwadzieścia");
        NUMBER_TRANSLATIONS.put(30, "trzydzieści");
        NUMBER_TRANSLATIONS.put(40, "czterdzieści");
        NUMBER_TRANSLATIONS.put(100, "sto");
        NUMBER_TRANSLATIONS.put(200, "dwieście");
        NUMBER_TRANSLATIONS.put(300, "trzysta");
        NUMBER_TRANSLATIONS.put(400, "czterysta");
        NUMBER_TRANSLATIONS.put(1000, "tysiąć");

        NUMBER_SUFFIXES.add("naście");
        NUMBER_SUFFIXES.add("dziesiąt");
        NUMBER_SUFFIXES.add("set");
        NUMBER_SUFFIXES.add("dziesiąta");
        NUMBER_SUFFIXES.add("dziesiąte");
        NUMBER_SUFFIXES.add("dziesiątych");
        NUMBER_SUFFIXES.add("setna");
        NUMBER_SUFFIXES.add("setne");
        NUMBER_SUFFIXES.add("setnych");

        SPECIAL_FRACTION_NUMBERS.put(1, "jedna");
        SPECIAL_FRACTION_NUMBERS.put(2, "dwie");
    }

    public NumberToTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        text = transformer.transform(text);
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
                    return NUMBER_TRANSLATIONS.get(checkZero);
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

                if(SPECIAL_FRACTION_NUMBERS.containsKey(number) && fraction < 10) {
                   newText.append(SPECIAL_FRACTION_NUMBERS.get(number));
                }
                else newText.append(findTranslation(number));
                newText.append(" ");
            }
            if(fractionLength == 1){
                if(fraction == 1) {
                    newText.append(NUMBER_SUFFIXES.get(3));
                }
                else if (fraction <= 4) {
                    newText.append(NUMBER_SUFFIXES.get(4));
                }
                else {
                    newText.append(NUMBER_SUFFIXES.get(5));
                }
            }
            else {
                if(fraction == 1) {
                    newText.append(NUMBER_SUFFIXES.get(6));
                }
                else if (fraction % 10 <= 4 && fraction % 10 > 1) {
                    newText.append(NUMBER_SUFFIXES.get(7));
                }
                else {
                    newText.append(NUMBER_SUFFIXES.get(8));
                };
            }
        }

        return newText.toString().trim();
    }

    private String findTranslation(int number){
        if(NUMBER_TRANSLATIONS.containsKey(number)) {
            return NUMBER_TRANSLATIONS.get(number);
        }
        else {
            if(number <= 19){
                return NUMBER_TRANSLATIONS.get(number % 10) + NUMBER_SUFFIXES.get(0);
            }
            else if(number <= 90){
                return NUMBER_TRANSLATIONS.get(number / 10) + NUMBER_SUFFIXES.get(1);
            }
            else if(number <= 1000){
                return NUMBER_TRANSLATIONS.get(number / 100) + NUMBER_SUFFIXES.get(2);
            }
        }
        return String.valueOf(number);
    }
}