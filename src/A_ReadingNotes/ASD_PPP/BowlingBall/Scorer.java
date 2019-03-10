package A_ReadingNotes.ASD_PPP.BowlingBall;

public class Scorer {
    private static int[] itsThrows = new int[2 * Game.ROUND + 2];  //10轮比赛，最多投掷22次
    private static int ballIndex = 0;     //itsThrows数组 第几球的下标

    public static int scoreForRound(int totalRoundCount) {
        ballIndex = 0;
        int score = 0;
        for (int i = 1; i <= totalRoundCount; i++) {
            if (isStrike()) {
                score += Game.STRIKE_BASE_SCORE + nextTwoBalls();
                ballIndex += 1;
            } else if (isSpare()) {
                score += Game.STRIKE_BASE_SCORE + nextOneBall();
                ballIndex += 2;
            } else {
                score += currentRoundTwoBalls();
                ballIndex += 2;
            }
        }
        return score;
    }

    private static int currentRoundTwoBalls() {
        return itsThrows[ballIndex] + itsThrows[ballIndex + 1];
    }

    private static int nextOneBall() {
        return itsThrows[ballIndex + 2];
    }

    private static int nextTwoBalls() {
        return itsThrows[ballIndex + 1] + itsThrows[ballIndex + 2];
    }

    //补中
    private static boolean isSpare() {
        return itsThrows[ballIndex] + itsThrows[ballIndex + 1] == Game.STRIKE_BASE_SCORE;
    }

    private static int add(int a, int b) throws Exception {
        int result = a + b;
        if (result > Game.STRIKE_BASE_SCORE) {
            throw new Exception("数据记录有误");
        }
        return result;
    }

    //全中
    private static boolean isStrike() {
        return itsThrows[ballIndex] == Game.STRIKE_BASE_SCORE;
    }

    public static void addThrows(int currentBalls) {
        itsThrows[ballIndex++] = currentBalls;
    }
}
