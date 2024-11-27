package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

public class AbbreviateTextTransformer extends TransformerDecorator {
    private static final Map<String, String> KEYWORD_MAP = new HashMap<>();
    static {
        KEYWORD_MAP.put("bułki i chleb", "pieczywo");
        KEYWORD_MAP.put("herbata i kawa.", "napoje");
        KEYWORD_MAP.put("koty i psy", "zwierzęta");
        KEYWORD_MAP.put("motory i auta", "pojazdy");
        KEYWORD_MAP.put("między innymi", "m.in.");
        KEYWORD_MAP.put("i tym podobne", "itp.");
        KEYWORD_MAP.put("i tak dalej", "itpd");
    }

    public AbbreviateTextTransformer(Transformer transformer) {
        super(transformer);
    }

    @Override
    public String transform(String text) {
        text = transformer.transform(text);

        for (Map.Entry<String, String> entry : KEYWORD_MAP.entrySet()) {
            String keyword = entry.getKey();
            String abbreviation = entry.getValue();
            String lowerCaseText = text.toLowerCase();
            String lowerCaseKeyword = keyword.toLowerCase();

            int index = lowerCaseText.indexOf(lowerCaseKeyword);

            while (index != -1) {
                text = text.substring(0, index) + abbreviation + text.substring(index + keyword.length());
                lowerCaseText = text.toLowerCase();
                index = lowerCaseText.indexOf(lowerCaseKeyword, index + abbreviation.length());
            }
        }
        return text;
    }
}