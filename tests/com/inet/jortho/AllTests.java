package com.inet.jortho;

import junit.framework.Test;
import java.util.Locale;

public class AllTests {

    private static boolean isInit;

    /**
     * register the dictionaries
     */
    public static void init() {
        if( !isInit ) {
            isInit = true;
            int threadCount = Thread.activeCount();
            SpellChecker.registerDictionaries( null, null );
            SpellChecker.setCurrentLocale(new Locale("en"));

            // wait until the dictionaries are loaded.
            for( int i = 0; i < 50; i++ ) {
                if( threadCount >= Thread.activeCount() ) {
                    break;
                }
                try {
                    Thread.sleep( 100 );
                } catch( InterruptedException e ) {
                    break;
                }
            }
        }
    }

    public static Test suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite( "JOrtho Tests" );

        suite.addTestSuite( WordsWithNumbersTest.class );
        suite.addTestSuite( SpecialCharactersTest.class );
        suite.addTestSuite( UrlAndEmailIgnoreTest.class );
        suite.addTestSuite( SuggestionsTest.class );
        suite.addTestSuite( HighlighterUpdateTest.class );
        suite.addTestSuite( UserDirectorySaveTest.class );
        suite.addTestSuite( AddToDictionaryTest.class );
        return suite;
    }
}
