package A_ReadingNotes.ASD_PPP.BowlingBall;

public class Game {
    //作为全局变量，方便写测试用例
    private static int ROUND = 10;  //只投掷10轮（每轮2次投掷机会）
    private static int STRIKE_BASE_SCORE = 10;  //全中的得分
    private static int[] itsThrows = new int[2 * ROUND + 2];  //10轮比赛，最多投掷22次

    public static void initItsThrows(int... throwsIn) {
        int index = 0;
        for (int i = 0; i < throwsIn.length; ) {
            itsThrows[index++] = throwsIn[i++];
        }
        ROUND = getRoundNum(throwsIn);
    }

    private static int getRoundNum(int[] throwsIn) {
        int length = throwsIn.length;
        if (length > 2) {
            if (throwsIn[length - 3] == STRIKE_BASE_SCORE
                    || ((length > 3 && throwsIn[length - 4] == STRIKE_BASE_SCORE)) && throwsIn[length - 3] + throwsIn[length - 2] != STRIKE_BASE_SCORE) {
                return (length - 2) / 2;
            } else if ((throwsIn[length - 2] + throwsIn[length - 3] == STRIKE_BASE_SCORE && length % 2 == 1)    //长度是奇数
                    || (throwsIn[length - 3] + throwsIn[length - 4] == STRIKE_BASE_SCORE && length % 2 == 0)) {   //长度是偶数
                return (length - 1) / 2;
            }
        }
        return length / 2;
    }

    public static int score() {
        int totalScore = 0;
        for (int roundNum = 1; roundNum <= ROUND; roundNum++) {
            totalScore = scoreForFrame(roundNum);
        }
        return totalScore;
    }

    public static int scoreForFrame(int roundNum) {
        if (roundNum <= 0) {
            return 0;
        }
        if (isStrike(roundNum)) {
            return STRIKE_BASE_SCORE + itsThrows[roundNum * 2] + itsThrows[roundNum * 2 + 1] + scoreForFrame(roundNum - 1);
        }
        if (isSpare(roundNum)) {
            return STRIKE_BASE_SCORE + itsThrows[roundNum * 2] + scoreForFrame(roundNum - 1);
        }
        return itsThrows[roundNum * 2 - 2] + itsThrows[roundNum * 2 - 1] + scoreForFrame(roundNum - 1);
    }

    //补中
    private static boolean isSpare(int roundNum) {
        return itsThrows[roundNum * 2 - 2] + itsThrows[roundNum * 2 - 1] == 10;
    }

    //全中
    private static boolean isStrike(int roundNum) {
        return itsThrows[roundNum * 2 - 2] == 10 || itsThrows[roundNum * 2 - 1] == 10;
    }
}
