package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        for (String transform : transforms) {
            switch (transform) {
                case "upper":
                    text = text.toUpperCase();
                    break;
                default:
                    break;

            }
        }

        return text;
    }
}
