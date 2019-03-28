package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class SuggestionReplacementTest extends TestCase {

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

        assertEquals(word, tok.nextInvalidWord());

        String suggestion = SpellChecker.getCurrentDictionary().searchSuggestions(word).get(0).getWord();
        text.setText(suggestion);

        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals(null, tok.nextInvalidWord());
    }
}
