package vsim.types;

public enum StartupLevel {
    GARAGE_STARTUP(1),
    TECH_STAR(5),
    UNICORN(10);

    private int levelVal;

    StartupLevel(int levelVal) {
        this.levelVal = levelVal;
    }

    public int getLevelVal() {
        return levelVal;
    }
}
