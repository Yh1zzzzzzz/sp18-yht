public class OffByOne implements CharacterComparator {

    public boolean equalChars(char a, char b) {
        int r = Math.abs(a - b);
        return r == 1;
    }
}
