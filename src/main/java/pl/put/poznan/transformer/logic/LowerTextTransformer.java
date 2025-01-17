package pl.put.poznan.transformer.logic;

/**
 * A decorator implementation of the {@link Transformer} interface
 * that converts the text to lowercase.
 * This class extends {@link TransformerDecorator} and modifies the behavior
 * of the wrapped {@link Transformer}.
 */
public class LowerTextTransformer extends TransformerDecorator {

    /**
     * Constructs a {@code LowerTextTransformer} with the specified {@link Transformer}.
     *
     * @param transformer the {@link Transformer} instance to be wrapped
     */
    public LowerTextTransformer(Transformer transformer) {
        super(transformer);
    }

    /**
     * Transforms the input text by first applying the transformation of the wrapped {@link Transformer}
     * and then converting the result to lowercase.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        return (transformer.transform(text)).toLowerCase();
    }
}
