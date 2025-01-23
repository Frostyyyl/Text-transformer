package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberToTextTransformerTest {
    @Test
    void TestNumbersWithSpaces() {
        String inputText = "1 2 4 10 11";
        String expectedText = "jeden dwa cztery dziesięć jedenaście";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new NumberToTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void TestDecimalNumber() {
        String inputText = "1,9";
        String expectedText = "jeden i dziewięć dziesiątych";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new NumberToTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void TestFloatNumber() {
        String inputText = "100,02";
        String expectedText = "sto i dwie setne";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new NumberToTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void TestSpecificPolishSuffixes() {
        String inputText = "200 15 1000";
        String expectedText = "dwieście piętnaście tysiąc";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new NumberToTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void TestWithComma() {
        String inputText = "10, 1";
        String expectedText = "dziesięć, jeden";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new NumberToTextTransformer(textTransformerMock).transform(inputText));
    }

    @Test
    void TestWithWords() {
        String inputText = "12 test test";
        String expectedText = "dwanaście test test";
        TextTransformer textTransformerMock = mock(TextTransformer.class);
        when(textTransformerMock.transform(inputText)).thenReturn(inputText);
        assertEquals(expectedText, new NumberToTextTransformer(textTransformerMock).transform(inputText));
    }
}