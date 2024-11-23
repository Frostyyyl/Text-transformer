package pl.put.poznan.transformer.logic;

/**
 * An abstract decorator class for specific transform
 */
public abstract class TransformerDecorator implements Transformer {
    Transformer transformer;

    public TransformerDecorator(Transformer transformer) {
        this.transformer = transformer;
    }
    public String transform(String text){
        return transformer.transform(text);
    }
}
