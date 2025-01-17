package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * A decorator implementation of the {@link Transformer} interface
 * that removes duplicates from the text.
 * This class extends {@link TransformerDecorator} and modifies the behavior
 * of the wrapped {@link Transformer}.
 */
public class RemoveDuplicatesTextTransformer extends TransformerDecorator {

    /**
     * Constructs a {@code RemoveDuplicatesTextTransformer} with the specified {@link Transformer}.
     *
     * @param transformer the {@link Transformer} instance to be wrapped
     */
    public RemoveDuplicatesTextTransformer(Transformer transformer) {
        super(transformer);
    }

    /**
     * Transforms the input text by first applying the transformation of the wrapped {@link Transformer}
     * and then removing duplicates form the result.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        // Transform the input text using the previous transformer
        text = transformer.transform(text);

        // Split the text into words
        String[] words = text.split("\\s+");
        List<String> result = new ArrayList<>();

        // Remove consecutive duplicate words
        String previousWord = null;
        for (String word : words) {
            if (!word.equals(previousWord)) {
                result.add(word);
            }
            previousWord = word;
        }

        // Join the result back into a single string
        return String.join(" ", result);
    }
}