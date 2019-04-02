package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class WordsWithNumbersTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testWordsWithNumbers(){
        String[] inputs = {"input7", "inp7ut", "7input", "7input7", "7inp7ut"};
        Tokenizer tok;
        boolean firstIsInvalid = false;

        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);

        text.setText(inputs[0]);
        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        if (tok.nextInvalidWord() == inputs[0]) {
            firstIsInvalid = true;
        }

        for (int i = 1; i < inputs.length; i++) {
            text.setText(inputs[i]);
            tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

            if (firstIsInvalid) {
                assertEquals("Expected an invalid word with input \"" + inputs[i] + "\"", inputs[i], tok.nextInvalidWord());
            }
            else {
                assertNull("Expected no invalid words with input \"" + inputs[i] + "\"", tok.nextInvalidWord());
            }
        }
    }
}
