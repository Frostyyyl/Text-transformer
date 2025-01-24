package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ExpandShortcutsTextTransformerTest {

    private ExpandShortcutsTextTransformer transformer;

    @BeforeEach
    public void setUp() {
        Transformer baseTransformer = text -> text; // Base transformer that performs no transformation.
        transformer = new ExpandShortcutsTextTransformer(baseTransformer);
    }

    @Test
    public void testExpandSingleShortcut() {
        String input = "Dr Smith";
        String expected = "Doktor Smith";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testExpandMultipleShortcuts() {
        String input = "Dr Smith i np. kot.";
        String expected = "Doktor Smith i na przykład kot.";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testShortcutWithDifferentCases() {
        String input = "dr Smith i DR Jones.";
        String expected = "doktor Smith i DOKTOR Jones.";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testExpandShortcutAtEndOfSentence() {
        String input = "To jest np.";
        String expected = "To jest na przykład";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testShortcutWithTrailingPunctuation() {
        String input = "Oto przykład: np., itd.";
        String expected = "Oto przykład: na przykład, i tak dalej";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testNoShortcutInText() {
        String input = "Brak skrótów w tekście.";
        String expected = "Brak skrótów w tekście.";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testExpandItp() {
        String input = "Lista zawiera: jabłka, gruszki itp.";
        String expected = "Lista zawiera: jabłka, gruszki i tym podobne";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testExpandItpInUpperCase() {
        String input = "ITP. jest używane.";
        String expected = "I TYM PODOBNE jest używane.";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testExpandShortcutInComplexText() {
        String input = "Dr Jones np. preferuje itd., ale itp.";
        String expected = "Doktor Jones na przykład preferuje i tak dalej, ale i tym podobne";
        assertEquals(expected, transformer.transform(input));
    }

    @Test
    public void testExpandWithBaseTransformer() {
        Transformer baseTransformer = text -> text.toUpperCase();
        ExpandShortcutsTextTransformer customTransformer = new ExpandShortcutsTextTransformer(baseTransformer);
        String input = "Dr Smith i np.";
        String expected = "DOKTOR SMITH I NA PRZYKŁAD";
        assertEquals(expected, customTransformer.transform(input));
    }
}
