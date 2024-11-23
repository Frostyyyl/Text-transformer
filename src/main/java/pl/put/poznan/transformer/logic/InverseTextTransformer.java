package pl.put.poznan.transformer.logic;

public class InverseTextTransformer extends TransformerDecorator{
    public InverseTextTransformer(Transformer transformer) {
        super(transformer);
    }

    public String transform(String text) {
        StringBuilder newText = new StringBuilder();
        for (int i = text.length() - 1; i >= 0 ; i--) {
            newText.append(text.charAt(i));
        }
        return newText.toString();
    }
}
