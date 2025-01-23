package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * A decorator implementation of the {@link Transformer} interface
 * that converts the text to be capitalized.
 * This class extends {@link TransformerDecorator} and modifies the behavior
 * of the wrapped {@link Transformer}.
 */
public class CapitalizeTextTransformer extends TransformerDecorator {

    /**
     * Constructs a {@code CapitalizeTextTransformer} with the specified {@link Transformer}.
     *
     * @param transformer the {@link Transformer} instance to be wrapped
     */
    public CapitalizeTextTransformer(Transformer transformer) {
        super(transformer);
    }

    /**
     * Transforms the input text by first applying the transformation of the wrapped {@link Transformer}
     * and then converting the result to be capitalized.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        if(text.isEmpty()) return text;
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
