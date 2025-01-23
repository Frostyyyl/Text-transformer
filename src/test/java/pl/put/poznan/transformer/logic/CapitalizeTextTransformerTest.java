package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CapitalizeTextTransformerTest {
    @Test
    void testCapitalize() {
        String inputText = "test test test";
        String expectedText = "Test Test Test";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new CapitalizeTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void testCapitalizeWithEmptyString() {
        String inputText = "";
        String expectedText = "";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new CapitalizeTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void testAlreadyCapitalized() {
        String inputText = "Test Test Test";
        String expectedText = "Test Test Test";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new CapitalizeTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void testWithNumbers() {
        String inputText = "test 123 test";
        String expectedText = "Test 123 Test";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new CapitalizeTextTransformer(textTransformerMock).transform(inputText));
    }
}