package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExpandShortcutsTextTransformer extends TransformerDecorator {
    private static final Map<String, String> KEYWORD_MAP = new HashMap<>();
    static {
        KEYWORD_MAP.put("Dr", "Doktor");
        KEYWORD_MAP.put("Prof.", "Profesor");
        KEYWORD_MAP.put("np.", "na przykład");
        KEYWORD_MAP.put("itd.", "i tak dalej");
        KEYWORD_MAP.put("itp.", "i tym podobne");
    }

    public ExpandShortcutsTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        text = transformer.transform(text);

        for (Map.Entry<String, String> entry : KEYWORD_MAP.entrySet()) {
            String keyword = entry.getKey();
            String expansion = entry.getValue();
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "(?=\\s|\\.|,|$)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);

            StringBuffer result = new StringBuffer();
            while (matcher.find()) {
                String match = matcher.group();
                String adjustedExpansion = adjustCase(expansion, match);
                matcher.appendReplacement(result, adjustedExpansion);
            }
            matcher.appendTail(result);
            text = result.toString();
        }

        return text;
    }

    private String adjustCase(String expansion, String keyword) {
        if (keyword.equals(keyword.toUpperCase())) {
            return expansion.toUpperCase();
        } else if (Character.isUpperCase(keyword.charAt(0))) {
            return Character.toUpperCase(expansion.charAt(0)) + expansion.substring(1).toLowerCase();
        } else {
            return expansion.toLowerCase();
        }
    }
}