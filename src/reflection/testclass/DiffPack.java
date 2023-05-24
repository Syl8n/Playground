package reflection.testclass;

public class DiffPack {
    public int publicI = 1;
    protected int protectedI = 2;
    private int privateI = 3;
    int defaultI = 4;

    private int sum(){
        return publicI + protectedI + privateI + defaultI;
    }
}
