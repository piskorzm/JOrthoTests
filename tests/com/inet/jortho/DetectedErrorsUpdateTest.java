package com.inet.jortho;

import javax.swing.*;
import javax.swing.text.Highlighter;

import junit.framework.TestCase;

public class DetectedErrorsUpdateTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testDetectedErrorsUpdate(){
        int errors;
        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);
        text.setText("There is an errorr.");
        Highlighter highlighter = text.getHighlighter();

        errors = highlighter.getHighlights().length;
        assertEquals(1, errors);
        text.select(16,17);
        text.replaceSelection("");

        errors = highlighter.getHighlights().length;

        assertEquals(0, errors);
    }
}
