package pl.put.poznan.transformer.logic;

/**
 * Implementation of converting text to upper case
 */
public class UpperTextTransformer extends TransformerDecorator{
    public UpperTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        return (transformer.transform(text)).toUpperCase();
    }
}
