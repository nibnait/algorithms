package A_ReadingNotes.ASD_PPP.BowlingBall;

public class Game {
    //作为全局变量，方便写测试用例
    protected static final int ROUND = 10;  //只投掷10轮（每轮2次投掷机会）
    private static int totalRoundCount = 0;  //本次游戏共有多少楼
    protected static int STRIKE_BASE_SCORE = 10;  //全中的得分
    private static boolean isFirstThrow = true; //是不是当前轮的第一次投掷
    private static Scorer scorer = new Scorer();


    public static int score() {
        return scorer.scoreForRound(totalRoundCount);
    }

    public static int scoreForRound(int roundNum) throws Exception {
        if (roundNum > totalRoundCount) {
            throw new Exception(String.format("目前只进行了%s轮", totalRoundCount));
        }
        return scorer.scoreForRound(roundNum);
    }

    public static void initItsThrows(int[] throwsIn) {
        for (int i = 0; i < throwsIn.length; i++) {
            scorer.addThrows(throwsIn[i]);
            adjustCurrentRound(throwsIn[i]);
        }
    }

    private static void adjustCurrentRound(int pins) {
        if (isStrike(pins) || !isFirstThrow) {         //全中 或 第二次投掷，本轮结束
            totalRoundCount = Math.min(ROUND, totalRoundCount + 1);
            isFirstThrow = true;
        } else {
            isFirstThrow = false;
        }
    }

    private static void adjustCurrentRound_V1(int pins) {
        if (isFirstThrow) {
            if (isStrike(pins)) {
                totalRoundCount++;
            } else {
                isFirstThrow = false;
            }
        } else {
            isFirstThrow = true;    //下一次，肯定就是下一轮的第一次投掷了
            totalRoundCount++;
        }
        totalRoundCount = Math.min(ROUND, totalRoundCount);
    }

    private static boolean isStrike(int pins) {
        return pins == STRIKE_BASE_SCORE;
    }
}
