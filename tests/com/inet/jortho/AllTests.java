package com.inet.jortho;

import junit.framework.Test;
import junit.framework.TestSuite;
import com.inet.jortho.SpellChecker;

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
        suite.addTestSuite( DetectedErrorsUpdateTest.class );
        suite.addTestSuite( SuggestionReplacementTest.class );
        suite.addTestSuite( UserDirectoryTest.class );
        return suite;
    }
}
