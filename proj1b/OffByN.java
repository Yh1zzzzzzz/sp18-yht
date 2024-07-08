public class OffByN implements CharacterComparator {

    private int N;
    public OffByN(int a){
        this.N = a;
    }
    public boolean equalChars(char a, char b){
        int r = Math.abs('a' - 'b');
        return r == N;
    }
}
