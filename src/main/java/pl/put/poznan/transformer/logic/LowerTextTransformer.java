package pl.put.poznan.transformer.logic;

public class LowerTextTransformer extends TransformerDecorator {
    public LowerTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        return (transformer.transform(text)).toLowerCase();
    }
}
