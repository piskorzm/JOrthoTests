package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import static com.inet.jortho.SpellChecker.getUserDictionaryProvider;

public class UserDirectorySaveTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testUserDirectorySave() throws InterruptedException {

        JEditorPane text = new JTextPane();
        SpellChecker.register(text);
        SpellChecker.enableAutoSpell(text, true);
        SpellChecker.setUserDictionaryProvider( new FileUserDictionary() );
        Iterator<String> userWords;
        String word = "missspel";

        getUserDictionaryProvider().getWords(SpellChecker.getCurrentLocale());
        userWords = SpellChecker.getUserDictionaryProvider().getWords(SpellChecker.getCurrentLocale());

        assertNull("Expect empty user dictionary", userWords);

        AddWordAction addWordAction = new AddWordAction(text, word);
        ActionEvent actionEvent = new ActionEvent(ActionEvent.ACTION_PERFORMED, 0, "Add to dictionary");
        addWordAction.actionPerformed(actionEvent);
        userWords = SpellChecker.getUserDictionaryProvider().getWords(SpellChecker.getCurrentLocale());

        assertEquals("Expect first word in the user directory to be \"" + word + "\"", word, userWords.next());

        File file = new File("UserDictionary_" + SpellChecker.getCurrentLocale() + ".txt" );

        assertTrue("UserDictionary_" + SpellChecker.getCurrentLocale() + ".txt file has not been created.", file.delete());
    }
}
