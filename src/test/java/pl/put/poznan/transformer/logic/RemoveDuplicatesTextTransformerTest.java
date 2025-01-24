package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicatesTextTransformerTest {

    private RemoveDuplicatesTextTransformer transformer;

    @BeforeEach
    public void setUp() {
        Transformer baseTransformer = text -> text; // Base transformer that does nothing
        transformer = new RemoveDuplicatesTextTransformer(baseTransformer);
    }

    @Test
    void testRemoveDuplicates() {
        String inputText = "Text text text";
        String expectedText = "Text text";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testRemoveDuplicatesEmptyString() {
        String inputText = "";
        String expectedText = "";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testRemoveDuplicatesNoDuplicates() {
        String inputText = "test text";
        String expectedText = "test text";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testRemoveDuplicatesMultipleDuplicatedWords() {
        String inputText = "text text text2 text2";
        String expectedText = "text text2";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testRemoveDuplicatesMultipleDuplicates() {
        String inputText = "text text text text";
        String expectedText = "text";
        assertEquals(expectedText, transformer.transform(inputText));
    }

    @Test
    void testRemoveDuplicatesWithBaseTransformer() {
        Transformer baseTransformer = text -> text.toUpperCase();
        RemoveDuplicatesTextTransformer customTransformer = new RemoveDuplicatesTextTransformer(baseTransformer);

        String inputText = "text HELLO hello";
        String expectedText = "TEXT HELLO";
        assertEquals(expectedText, customTransformer.transform(inputText));
    }
}