package pl.put.poznan.transformer.logic;

/**
 * A decorator implementation of the {@link Transformer} interface
 * that inverses the input text while maintaining the position of
 * upper and lower case letters.
 * This class extends {@link TransformerDecorator} and modifies the behavior
 * of the wrapped {@link Transformer}.
 */
public class InverseTextTransformer extends TransformerDecorator {

    /**
     * Constructs an {@code InverseTextTransformer} with the specified {@link Transformer}.
     *
     * @param transformer the {@link Transformer} instance to be wrapped
     */
    public InverseTextTransformer(Transformer transformer) {
        super(transformer);
    }

    /**
     * Transforms the input text by first applying the transformation of the wrapped {@link Transformer}
     * and then inverting the result while maintaining the position of
     * upper and lower case letters.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        text = transformer.transform(text);

        StringBuilder newText = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            newText.append(text.charAt(i));
        }
        return adjustCase(text, newText.toString());
    }

    /**
     * Adjusts positions of upper and lower case letters.
     *
     * @param originalText text containing original positions
     * @param invertedText transformed text for correction
     * @return text with correct positions of upper and lower case letters
     */
    private String adjustCase(String originalText, String invertedText) {
        StringBuilder adjustedText = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            if (Character.isUpperCase(originalText.charAt(i)))
                adjustedText.append(Character.toUpperCase(invertedText.charAt(i)));
            if (Character.isLowerCase(originalText.charAt(i)))
                adjustedText.append(Character.toLowerCase(invertedText.charAt(i)));
        }
        return adjustedText.toString();
    }
}
