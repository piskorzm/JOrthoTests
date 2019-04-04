package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class SpecialCharactersTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testWordsWithNumbers(){
        String partOfWord = "'`";
        String separators = "!@#$%^&*()+={}|:<>?/,;[]\\~._-\"";
        String invalid_word_1 = "onne";
        String invalid_word_2 = "twwo";
        String input;
        Tokenizer tok;


        JEditorPane text = new JTextPane();
        SpellChecker.register(text);

        for (char character: partOfWord.toCharArray()) {
            String expected;
            input = invalid_word_1 + character + invalid_word_2;
            if (character == '`') {
                expected = invalid_word_1 + '\'' + invalid_word_2;
            }
            else {
                expected = input;
            }
            text.setText(input);
            tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

            assertEquals("Expected input \"" + input + "\" to be recognized as an error" , expected, tok.nextInvalidWord());
        }

        for (char character: separators.toCharArray()) {
            input = invalid_word_1 + character + invalid_word_2;
            text.setText(input);
            tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

            assertEquals("Expected the first invalid word with input \"" + input + "\" to be equal \"" + invalid_word_1 + "\"" , invalid_word_1, tok.nextInvalidWord());
            assertEquals("Expected the second invalid word with input \"" + input + "\" to be equal \"" + invalid_word_2 + "\"" , invalid_word_2, tok.nextInvalidWord());
        }
    }
}
