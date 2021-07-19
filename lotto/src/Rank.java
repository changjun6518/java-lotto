public enum Rank {
    FIRST(6, 2_000_000_000), // 1등
    SECOND(5, 30_000_000), // 2등
    THIRD(5, 1_500_000), // 3등
    FOURTH(4, 50_000), // 4등
    FIFTH(3, 5_000), // 5등
    MISS(0, 0);

    private int matchCount;
    private int winningPrice;
    private static final int WINNING_MIN_COUNT = 3;

    private Rank(int matchCount, int winningPrice) {
        this.matchCount = matchCount;
        this.winningPrice = winningPrice;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(matchCount) && matchBonus) {
            return SECOND;
        }

        if (THIRD.matchCount(matchCount) && !matchBonus) {
            return THIRD;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(matchCount)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(matchCount + "는 유효하지 않은 값입니다.");
    }
    private boolean matchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
    public void printResult(Long totalCount) {
        System.out.println(matchCount + "개 일치 " + "(" + winningPrice + "원)-" + totalCount + "개");
    }
}
