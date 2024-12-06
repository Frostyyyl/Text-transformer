package pl.put.poznan.transformer.logic;

/**
 * Interface that every transformer implements
 */
public interface Transformer{
    /**
     * Declaration of method for transform
     * @param text Text to transform
     * @return Transformed text
     */
    String transform(String text);
}