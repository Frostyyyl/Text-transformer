package pl.put.poznan.transformer.logic;

/**
 * A decorator implementation of the {@link Transformer} interface
 * that converts the text to uppercase.
 * This class extends {@link TransformerDecorator} and modifies the behavior
 * of the wrapped {@link Transformer}.
 */
public class UpperTextTransformer extends TransformerDecorator {

    /**
     * Constructs an {@code UpperTextTransformer} with the specified {@link Transformer}.
     *
     * @param transformer the {@link Transformer} instance to be wrapped
     */
    public UpperTextTransformer(Transformer transformer) {
        super(transformer);
    }

    /**
     * Transforms the input text by first applying the transformation of the wrapped {@link Transformer}
     * and then converting the result to uppercase.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        return (transformer.transform(text)).toUpperCase();
    }
}
