package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(value = "/{text}", method = RequestMethod.GET, produces = "application/json")
    public Response getByPath(@PathVariable String text,
                              @RequestParam(value = "transforms", defaultValue = "") String[] transforms) {

        // log the parameters
        logger.debug("The 'GET' method input text: {}.", text);
        logger.debug("The 'GET' method input transforms: {}", Arrays.toString(transforms));

        String transformedText = performTransformation(text, transforms);

        // log the result
        logger.debug("The 'GET' method output text: {}", transformedText);

        return new Response(transformedText);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Response getByParam(@RequestParam(value = "text", defaultValue = "") String text,
                               @RequestParam(value = "transforms", defaultValue = "") String[] transforms) {

        // log the parameters
        logger.debug("The 'GET' method input text: {}.", text);
        logger.debug("The 'GET' method input transforms: {}", Arrays.toString(transforms));

        String transformedText = performTransformation(text, transforms);

        // log the result
        logger.debug("The 'GET' method output text: {}", transformedText);

        return new Response(transformedText);
    }

    @RequestMapping(value = "/{text}", method = RequestMethod.POST, produces = "application/json")
    public Response postByPath(@PathVariable String text,
                         @RequestBody InputTransforms data) {

        String[] transforms = data.transforms;

        // log the parameters
        logger.debug("The 'POST' method input text: {}.", text);
        logger.debug("The 'POST' method input transforms: {}", Arrays.toString(transforms));

        String transformedText = performTransformation(text, transforms);

        // log the result
        logger.debug("The 'POST' method output text: {}", transformedText);

        return new Response(transformedText);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Response postByParam(@RequestBody InputData data) {

        String text = data.text;
        String[] transforms = data.transforms;

        // log the parameters
        logger.debug("The 'POST' method input text: {}.", text);
        logger.debug("The 'POST' method input transforms: {}", Arrays.toString(transforms));

        String transformedText = performTransformation(text, transforms);

        // log the result
        logger.debug("The 'POST' method output text: {}", transformedText);

        return new Response(transformedText);
    }

    private String performTransformation(String text, String[] transforms) {
        Transformer transformer = new TextTransformer();

        for (String transform : transforms) {
            switch (transform) {
                case "upper":
                    transformer = new UpperTextTransformer(transformer);
                    logger.info("Added the 'upper' operation to transformer.");
                    break;
                case "lower":
                    transformer = new LowerTextTransformer(transformer);
                    logger.info("Added the 'lower' operation to transformer.");
                    break;
                case "inverse":
                    transformer = new InverseTextTransformer(transformer);
                    logger.info("Added the 'inverse' operation to transformer.");
                    break;
                case "numbers_to_text":
                    transformer = new NumberToTextTransformer(transformer);
                    logger.info("Added the 'numbers_to_text' operation to transformer.");
                    break;
                case "expand_shortcuts":
                    transformer = new ExpandShortcutsTextTransformer(transformer);
                    logger.info("Added the 'expand_shortcuts' operation to transformer.");
                    break;
                case "abbreviate":
                    transformer = new AbbreviateTextTransformer(transformer);
                    logger.info("Added the 'abbreviate' operation to transformer.");
                    break;
                case "remove_duplicates":
                    transformer = new RemoveDuplicatesTextTransformer(transformer);
                    logger.info("Added the 'remove_duplicates' operation to transformer.");
                    break;
                case "capitalize":
                    transformer = new CapitalizeTextTransformer(transformer);
                    logger.info("Added the 'capitalize' operation to transformer.");
                    break;
                default:
                    break;
            }
        }

        return transformer.transform(text);
    }

    public static class Response {
        private String text;

        public Response(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class InputData {
        public String text;
        public String[] transforms;
    }

    public static class InputTransforms {
        public String[] transforms;
    }
}
