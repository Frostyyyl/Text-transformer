package pl.put.poznan.transformer.logic;

/**
 * Transformer for inverting text while maintaining the position of
 * upper and lower case letters
 */
public class InverseTextTransformer extends TransformerDecorator{
    public InverseTextTransformer(Transformer transformer) {
        super(transformer);
    }

    /**
     * Transforms text to inverse
     * @param text Text to transform
     * @return Transformed text with correct positions of upper and lower case letters
     */
    public String transform(String text) {
        text = transformer.transform(text);

        StringBuilder newText = new StringBuilder();
        for (int i = text.length() - 1; i >= 0 ; i--) {
            newText.append(text.charAt(i));
        }
        return adjustCase(text, newText.toString());
    }

    /**
     * Adjusts positions of upper and lower case letters
     * @param originalText Text containing original positions
     * @param invertedText Transformed text for correction
     * @return Text with correct positions of upper and lower case letters
     */
    private String adjustCase(String originalText, String invertedText) {
        StringBuilder adjustedText = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            if(Character.isUpperCase(originalText.charAt(i))) adjustedText.append(Character.toUpperCase(invertedText.charAt(i)));
            if(Character.isLowerCase(originalText.charAt(i))) adjustedText.append(Character.toLowerCase(invertedText.charAt(i)));
        }
        return adjustedText.toString();
    }
}
