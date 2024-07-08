public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnDeque = new LinkedListDeque<>();
        int lenth  = word.length();
        for (int i = 0; i < lenth; i += 1){
            returnDeque.addLast(word.charAt(i));
        }
        return returnDeque;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> a =  wordToDeque(word);
        if (a.size() == 1 || a.isEmpty()) {
            return true;
        }
        while (a.size() > 1) {
            char fi = a.removeFirst();
            char la = a.removeLast();
            if (fi != la) {
                return false;
            }
        }
        return true;
    }
    public  boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> a =  wordToDeque(word);
        if (a.size() == 1) {
            return true;
        }
        while (a.size() > 1) {
            char firest = a.removeFirst();
            char last = a.removeLast();
            if ( ! cc.equalChars(firest, last)){
                return false;
            }
        }
        return true;
    }


}
