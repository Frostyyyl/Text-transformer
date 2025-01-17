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
}
