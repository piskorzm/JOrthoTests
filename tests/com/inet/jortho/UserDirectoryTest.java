package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class UserDirectoryTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testUserDirectory() throws Exception{
        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);

        String word = "errrr";

        text.setText(word);

        Tokenizer tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expected one invalid word", word, tok.nextInvalidWord());
        SpellChecker.getCurrentDictionary().add(word);

        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertNull("No invalid words after adding to dictionary", tok.nextInvalidWord());

        JMenu languagesMenu = SpellChecker.createLanguagesMenu();
        JRadioButtonMenuItem language_1 = (JRadioButtonMenuItem)languagesMenu.getItem( 0 );
        JRadioButtonMenuItem language_2 = (JRadioButtonMenuItem)languagesMenu.getItem( 1 );

        JRadioButtonMenuItem notSelected = language_1.isSelected() ? language_2 : language_1;

        notSelected.doClick(0);

        Thread.sleep( 10 );

        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertNull("No invalid words after changing language", tok.nextInvalidWord());
    }
}
