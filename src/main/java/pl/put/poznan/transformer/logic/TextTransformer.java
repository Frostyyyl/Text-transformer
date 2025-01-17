package pl.put.poznan.transformer.logic;

/**
 * A basic implementation of the {@link Transformer} interface.
 * It defines the basic behavior, which can be altered by decorators.
 * This class performs no modifications to the input text.
 */
public class TextTransformer implements Transformer {

    /**
     * Constructs a new {@code TextTransformer}.
     */
    public TextTransformer() {
    }

    /**
     * Returns the input text without applying any transformation.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        return text;
    }
}
