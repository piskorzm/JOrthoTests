package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;
import javax.swing.text.Highlighter;
import java.nio.file.DirectoryStream;

public class WordsWithNumbersTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testWordsWithNumbers(){
        String inputWithNumber_1 = "input7";
        String inputWithNumber_2 = "inp7ut";
        String inputWithNumber_3 = "7input";
        Tokenizer tok;

        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);
        SpellChecker.setUserDictionaryProvider( new FileUserDictionary() );

        text.setText(inputWithNumber_1);
        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expected an invalid word with input '" + inputWithNumber_1 + "'", inputWithNumber_1, tok.nextInvalidWord());

        text.setText(inputWithNumber_2);
        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expected an invalid word with input '" + inputWithNumber_2 + "'", inputWithNumber_2, tok.nextInvalidWord());

        text.setText(inputWithNumber_3);
        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expected an invalid word with input '" + inputWithNumber_3 + "'", inputWithNumber_3, tok.nextInvalidWord());
    }
}
