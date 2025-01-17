package pl.put.poznan.transformer.logic;

/**
 * This interface represents an operation of text transformation.
 * Classes implementing this interface are expected to provide a specific implementation
 * for transforming a given text input into its transformed version.
 */
public interface Transformer {

    /**
     * Transforms the input text according to the specific implementation of the transformer.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    String transform(String text);
}
