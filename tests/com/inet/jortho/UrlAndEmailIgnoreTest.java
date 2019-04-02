package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class UrlAndEmailIgnoreTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testWordsWithNumbers(){
        String input = "https://sourceforge.net/projects/jortho/ jortho@jortho.com";
        Tokenizer tok;

        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);

        text.setText(input);
        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertNull("Expected no invalid words with input \"" + input + "\"", tok.nextInvalidWord());

    }
}
