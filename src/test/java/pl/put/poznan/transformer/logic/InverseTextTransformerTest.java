package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InverseTextTransformerTest {

    private InverseTextTransformer transformer;

    @BeforeEach
    public void setUp() {
        Transformer baseTransformer = text -> text;
        transformer = new InverseTextTransformer(baseTransformer);
    }

    @Test
    void testInverseSimpleText() {
        String inputText = "hello";
        String expectedText = "olleh";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testInverseTextWithMixedCase() {
        String inputText = "HeLLo";
        String expectedText = "OlLEh";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testInverseEmptyString() {
        String inputText = "";
        String expectedText = "";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testInverseTextWithSpaces() {
        String inputText = "Hello World";
        String expectedText = "Dlrow Olleh";
        assertEquals(expectedText, transformer.transform(inputText));
    }
}