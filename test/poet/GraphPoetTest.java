package poet;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GraphPoetTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testPoemWithBridgeWords() throws IOException {
        File corpus = new File("test/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Seek to explore new and exciting synergies!";
        String expected = "Seek to explore strange new life and exciting synergies!";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testPoemWithoutBridgeWords() throws IOException {
        File corpus = new File("test/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "The quick brown fox jumps";
        String expected = "The quick brown fox jumps";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testEmptyInput() throws IOException {
        File corpus = new File("test/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "";
        String expected = "";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testSingleWordInput() throws IOException {
        File corpus = new File("test/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Hello";
        String expected = "Hello";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testCaseInsensitivity() throws IOException {
        File corpus = new File("test/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "seek TO Explore new And Exciting Synergies!";
        String expected = "seek TO Explore strange new life And Exciting Synergies!";
        assertEquals(expected, poet.poem(input));
    }
}