package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of converting text to upper case
 */
public class CapitalizeTextTransformer extends TransformerDecorator{
    public CapitalizeTextTransformer(Transformer transformer) { super(transformer); }

    public String transform(String text) {
        text = transformer.transform(text);

        // Split the text into words
        String[] words = text.split("\\s+");
        List<String> result = new ArrayList<>();

        // Capitalize each word
        for (String word : words) {
            result.add(word.substring(0, 1).toUpperCase() + word.substring(1));
        }

        // Join the result back into a single string
        return String.join(" ", result);
    }
}
