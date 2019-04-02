package com.inet.jortho;

import junit.framework.TestCase;
import javax.swing.*;

public class SuggestionsTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testSuggestionReplacement(){
        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);

        String word = "errrr";

        text.setText(word);

        Tokenizer tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expect invalid word before using suggestion", word, tok.nextInvalidWord());

        String suggestion = SpellChecker.getCurrentDictionary().searchSuggestions(word).get(0).getWord();
        text.setText(suggestion);

        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertNull("Expect no invalid words after replacing it with a suggestion", tok.nextInvalidWord());
    }
}
