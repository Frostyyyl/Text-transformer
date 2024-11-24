package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicateTextTransformer extends TransformerDecorator {

    public RemoveDuplicateTextTransformer(Transformer transformer) {
        super(transformer);
    }

    @Override
    public String transform(String text) {
        // Transform the input text using the previous transformer
        String transformedText = transformer.transform(text);

        // Split the text into words
        String[] words = transformedText.split("\\s+");
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