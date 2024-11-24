package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.InverseTextTransformer;
import pl.put.poznan.transformer.logic.NumberToTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.UpperTextTransformer;
import pl.put.poznan.transformer.logic.ExpandTextTransformer;
import pl.put.poznan.transformer.logic.ShortenTextTransformer;

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
                case "inverse":
                    InverseTextTransformer inverseTextTransformer = new InverseTextTransformer(textTransformer);
                    result = inverseTextTransformer.transform(result);
                    break;
                case "number_to_word":
                    NumberToTextTransformer numberToTextTransformer = new NumberToTextTransformer(textTransformer);
                    result = numberToTextTransformer.transform(result);
                    break;
                case "expand":
                    ExpandTextTransformer ExpandTextTransformer = new ExpandTextTransformer(textTransformer);
                    result = ExpandTextTransformer.transform(result);
                    break;
                case "shorten":
                    ShortenTextTransformer ShortenTextTransformer = new ShortenTextTransformer(textTransformer);
                    result = ShortenTextTransformer.transform(result);
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}
