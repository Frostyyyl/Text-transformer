package pl.put.poznan.transformer.logic;

public class InverseTextTransformer extends TransformerDecorator{
    public InverseTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        text = transformer.transform(text);

        StringBuilder newText = new StringBuilder();
        for (int i = text.length() - 1; i >= 0 ; i--) {
            newText.append(text.charAt(i));
        }
        return adjustCase(text, newText.toString());
    }

    private String adjustCase(String originalText, String invertedText) {
        StringBuilder adjustedText = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            if(Character.isUpperCase(originalText.charAt(i))) adjustedText.append(Character.toUpperCase(invertedText.charAt(i)));
            if(Character.isLowerCase(originalText.charAt(i))) adjustedText.append(Character.toLowerCase(invertedText.charAt(i)));
        }
        return adjustedText.toString();
    }
}
