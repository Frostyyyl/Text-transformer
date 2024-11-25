package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ExpandShortcutsTextTransformer extends TransformerDecorator {
    private static final Map<String, String> KEYWORD_MAP = new HashMap<>();
    static {
        KEYWORD_MAP.put("Dr", "Doktor");
        KEYWORD_MAP.put("Prof.", "Profesor");
        KEYWORD_MAP.put("np.", "na przykład");
        KEYWORD_MAP.put("itd.", "i tak dalej");
    }

    public ExpandShortcutsTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        text = transformer.transform(text);

        for (Map.Entry<String, String> entry : KEYWORD_MAP.entrySet()) {
            String keyword = entry.getKey();
            String expansion = entry.getValue();
            text = text.replaceAll("(?i)\\b" + Pattern.quote(keyword) + "(?=\\s|\\.|,|$)", expansion);
        }
        return text;
    }
}