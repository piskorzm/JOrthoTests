package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;
public class AddToDictionaryTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testAddToDictionary() throws Exception{
        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);
        SpellChecker.setUserDictionaryProvider( new FileUserDictionary() );

        String word = "errrr";

        text.setText(word);

        Tokenizer tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expected an invalid word before adding to dictionary", word, tok.nextInvalidWord());

        SpellChecker.getCurrentDictionary().add(word);

        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertNull("No invalid words after adding to dictionary", tok.nextInvalidWord());

        JMenu languagesMenu = SpellChecker.createLanguagesMenu();
        assertTrue( "2 languages requied:" + languagesMenu.getItemCount(), languagesMenu.getItemCount() >= 2 );
        JRadioButtonMenuItem language_1 = (JRadioButtonMenuItem)languagesMenu.getItem( 0 );
        JRadioButtonMenuItem language_2 = (JRadioButtonMenuItem)languagesMenu.getItem( 1 );

        JRadioButtonMenuItem notSelected = language_1.isSelected() ? language_2 : language_1;
        notSelected.doClick(0);

        Thread.sleep( 2500 );

        tok = new Tokenizer(text, SpellChecker.getCurrentDictionary(), SpellChecker.getCurrentLocale(), SpellChecker.getOptions());

        assertEquals("Expect an invalid word after changing language", word, tok.nextInvalidWord());
    }
}