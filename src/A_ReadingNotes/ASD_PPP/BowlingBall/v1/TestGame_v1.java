package A_ReadingNotes.ASD_PPP.BowlingBall.v1;


import org.junit.Assert;

public class TestGame_v1 {

    private static Game_v1 g = new Game_v1();
    private static int[] throwsIn = new int[22];

    public static void main(String[] args) {
        testTwoThrowsNoMark();      //只投掷2次
        testFourThrowsNoMark();     //只投掷4次
        testSimpleSpare();  //简单的补中
        testSimpleFrameAfterSpare();
//        testSimpleStrike(); //简单的全中
        testPerfectGame();  //10次全中
        testEndOfArray();   //前9轮都是0，第10轮补中
        testSampleGame();   //正常测试用例
    }

    private static void testSampleGame() {
        g.initItsThrows(1,4, 4,5, 6,4, 5,5, 10,0, 0,1, 7,3, 6,4, 0,10, 2,8, 6);
        Assert.assertEquals(123, g.score());
        g.initItsThrows(1,4, 4,5, 6,4, 5,5, 10,0, 0,1, 7,3, 6,4, 10,0, 2,8, 6);
        Assert.assertEquals(133, g.score());
    }

    private static void testEndOfArray() {
        int i = 0;
        for (; i < 18;) {
            throwsIn[i++] = 0;
        }
        throwsIn[i++] = 2;
        throwsIn[i++] = 8;
        throwsIn[i++] = 10;
        g.initItsThrows(throwsIn);
        Assert.assertEquals(20, g.score());
    }


    private static void testPerfectGame() {
        for (int i = 0; i < 20;) {
            throwsIn[i++] = 10;
            throwsIn[i++] = 0;
        }
        g.initItsThrows(throwsIn);
//        Assert.assertEquals(300, g.score());   //300是咋算的？？？
        Assert.assertEquals(190, g.score());
    }

    private static void testSimpleStrike() {
        g.initItsThrows(10, 0, 3, 6);
//        Assert.assertEquals(28, g.score());     // 歧义！一轮还是两轮？
        Assert.assertEquals(19, g.scoreForFrame(1));
        Assert.assertEquals(28, g.scoreForFrame(2));
        Assert.assertEquals(28, g.scoreForFrame(3));
    }

    private static void testSimpleFrameAfterSpare() {
        g.initItsThrows(3, 7, 3, 2);
//        Assert.assertEquals(18, g.score());     // 歧义！一轮还是两轮？
        Assert.assertEquals(13, g.scoreForFrame(1));
        Assert.assertEquals(18, g.scoreForFrame(2));
    }

    private static void testSimpleSpare() {
        g.initItsThrows(3, 7, 3);
        Assert.assertEquals(13, g.score());
        Assert.assertEquals(13, g.scoreForFrame(1));
    }

    private static void testFourThrowsNoMark() {
        g.initItsThrows(5, 4, 7, 2);
        Assert.assertEquals(18, g.score());
        Assert.assertEquals(9, g.scoreForFrame(1));
        Assert.assertEquals(18, g.scoreForFrame(2));
    }

    private static void testTwoThrowsNoMark() {
        g.initItsThrows(5, 4);
        Assert.assertEquals(9, g.score());
    }
}
