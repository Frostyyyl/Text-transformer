package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.UpperTextTransformer;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper, escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        String result = performTransformation(text, transforms);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        String result = performTransformation(text, transforms);
        return result;
    }

    private String performTransformation(String text, String[] transforms){
        TextTransformer textTransformer = new TextTransformer();
        String result = text;

        for (String transform : transforms) {
            switch (transform) {
                case "upper":
                    UpperTextTransformer upperTextTransformer = new UpperTextTransformer(textTransformer);
                    result = upperTextTransformer.transform(result);
                    break;
                case "lower":
                    result = result.toLowerCase();
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}
