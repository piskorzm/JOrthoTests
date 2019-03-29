package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class SpecialCharactersTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testWordsWithNumbers(){
        char hyphenSign = '-';
        String specialCharacters = "!@#$%^&*()+={}|:<>?/,;[]\\~._'\"`";
        String invalid_word_1 = "onne";
        String invalid_word_2 = "twwo";
        String input;
        Tokenizer tok;


        JEditorPane text = new JTextPane();
        SpellChecker.register(text);

        input = invalid_word_1 + hyphenSign + invalid_word_2;
        text.setText(input);
        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expected whole input '" + input + "' to be recognized as an error" , input, tok.nextInvalidWord());

        for (char character: specialCharacters.toCharArray()) {
            input = invalid_word_1 + character + invalid_word_2;
            text.setText(input);
            tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

            assertEquals("Expected the first invalid word with input '" + input + "' to be equal '" + invalid_word_1 + "'" , invalid_word_1, tok.nextInvalidWord());
            assertEquals("Expected the second invalid word with input '" + input + "' to be equal '" + invalid_word_2 + "'" , invalid_word_2, tok.nextInvalidWord());
        }
    }
}
