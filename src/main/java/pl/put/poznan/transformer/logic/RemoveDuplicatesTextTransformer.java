package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicatesTextTransformer extends TransformerDecorator {

    public RemoveDuplicatesTextTransformer(Transformer transformer) {
        super(transformer);
    }

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