import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.


    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void test2(){

        OffByOne cc = new OffByOne();
        Palindrome a = new Palindrome();
        assertTrue(a.isPalindrome("a",cc));
        assertTrue(a.isPalindrome("aba",cc));
        assertTrue(a.isPalindrome("flake",cc));


    }
}
