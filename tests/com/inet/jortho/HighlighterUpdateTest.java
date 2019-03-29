package com.inet.jortho;

import javax.swing.*;
import javax.swing.text.Highlighter;

import junit.framework.TestCase;

public class HighlighterUpdateTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testDetectedErrorsUpdate() throws InterruptedException {
        int errors;
        String sentence = "There is an errorr.";
        JEditorPane text_1 = new JTextPane();
        JEditorPane text_2 = new JTextPane();

        SpellChecker.register(text_1);
        SpellChecker.enableAutoSpell(text_1, true);

        text_1.setText(sentence);
        text_2.setText(sentence);

        Highlighter highlighter_1 = text_1.getHighlighter();
        Highlighter highlighter_2 = text_2.getHighlighter();

        errors = highlighter_1.getHighlights().length;

        assertEquals("Expect one highlight before correction.",1, errors);

        text_1.select(16,17);
        text_1.replaceSelection("");

        errors = highlighter_1.getHighlights().length;

        assertEquals("Expect no highlights after removing the error.",0, errors);

        SpellChecker.register(text_2);
        SpellChecker.enableAutoSpell(text_2, true);
        AutoSpellChecker.refresh(text_2);

        Thread.sleep(15);

        errors = highlighter_2.getHighlights().length;
        assertEquals("Expect one highlight after registering new JText element.",1, errors);
    }
}
