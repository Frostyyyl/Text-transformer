package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Response get(@PathVariable String text,
                        @RequestParam(value = "transforms", defaultValue = "upper, escape") String[] transforms) {

        // log the parameters
        logger.debug("The 'GET' method input text: {}.", text);
        logger.debug("The 'GET' method input transforms: {}", Arrays.toString(transforms));

        String transformedText = performTransformation(text, transforms);

        // log the result
        logger.debug("The 'GET' method output text: {}", transformedText);

        return new Response(transformedText);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Response post(@PathVariable String text,
                         @RequestBody String[] transforms) {

        // log the parameters
        logger.debug("The 'POST' method input text: {}.", text);
        logger.debug("The 'POST' method input transforms: {}", Arrays.toString(transforms));

        String transformedText = performTransformation(text, transforms);

        // log the result
        logger.debug("The 'POST' method output text: {}", transformedText);

        return new Response(transformedText);
    }

    private String performTransformation(String text, String[] transforms) {
        TextTransformer textTransformer = new TextTransformer();
        String result = text;

        for (String transform : transforms) {
            switch (transform) {
                case "upper":
                    UpperTextTransformer upperTextTransformer = new UpperTextTransformer(textTransformer);
                    result = upperTextTransformer.transform(result);
                    logger.info("Performed the 'upper' transformation.");
                    break;
                case "lower":
                    LowerTextTransformer lowerTextTransformer = new LowerTextTransformer(textTransformer);
                    result = lowerTextTransformer.transform(result);
                    logger.info("Performed the 'lower' transformation.");
                    break;
                case "capitalize":
                    CapitalizeTextTransformer capitalizeTextTransformer = new CapitalizeTextTransformer(textTransformer);
                    result = capitalizeTextTransformer.transform(result);
                    logger.info("Performed the 'capitalize' transformation.");
                    break;
                case "inverse":
                    InverseTextTransformer inverseTextTransformer = new InverseTextTransformer(textTransformer);
                    result = inverseTextTransformer.transform(result);
                    logger.info("Performed the 'inverse' transformation.");
                    break;
                case "numbers_to_text":
                    NumberToTextTransformer numberToTextTransformer = new NumberToTextTransformer(textTransformer);
                    result = numberToTextTransformer.transform(result);
                    logger.info("Performed the 'numbers_to_text' transformation.");
                    break;
                case "expand_shortcuts":
                    ExpandShortcutsTextTransformer expandShortcutsTextTransformer = new ExpandShortcutsTextTransformer(textTransformer);
                    result = expandShortcutsTextTransformer.transform(result);
                    logger.info("Performed the 'expand_shortcuts' transformation.");
                    break;
                case "abbreviate":
                    AbbreviateTextTransformer abbreviateTextTransformer = new AbbreviateTextTransformer(textTransformer);
                    result = abbreviateTextTransformer.transform(result);
                    logger.info("Performed the 'abbreviate' transformation.");
                    break;
                case "remove_duplicates":
                    RemoveDuplicatesTextTransformer removeDuplicatesTextTransformer = new RemoveDuplicatesTextTransformer(textTransformer);
                    result = removeDuplicatesTextTransformer.transform(result);
                    logger.info("Performed the 'remove_duplicates' transformation.");
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    public static class Response {
        private String text;

        public Response(String text) {
            this.text = text;
        }

        // Those classes are for Spring Boot to show data

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
