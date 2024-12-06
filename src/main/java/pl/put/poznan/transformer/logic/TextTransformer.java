package pl.put.poznan.transformer.logic;

/**
 * Main class for executing transforms on text
 */
public class TextTransformer implements Transformer {

    public TextTransformer(){ }

    /**
     * Implementation of transform method
     * @param text Text to transform
     * @return Unchanged text
     */
    public String transform(String text){
        return text;
    }
}
