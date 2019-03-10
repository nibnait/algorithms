package A_ReadingNotes.ASD_PPP.BowlingBall;

import junit.framework.TestCase;
import org.junit.Test;

public class TestGame extends TestCase {
    private static Game g = new Game();
    private static int[] throwsIn = new int[22];

    @Test
    public static void testSampleGame() {
        throwsIn = new int[]{1,4, 4,5, 6,4, 5,5, 10, 0,1, 7,3, 6,4, 10, 2,8, 6};
        g.initItsThrows(throwsIn);
        assertEquals(133, g.score());
    }

    @Test
    public static void testEndOfArray() {
        int i = 0;
        for (; i < 18;) {
            throwsIn[i++] = 0;
            throwsIn[i++] = 0;
        }
        throwsIn[i++] = 2;
        throwsIn[i++] = 8;
        throwsIn[i++] = 10;
        g.initItsThrows(throwsIn);
        assertEquals(20, g.score());
    }

    @Test
    public static void testPerfectGame() {
        for (int i = 0; i < 12;) {
            throwsIn[i++] = 10;
        }
        g.initItsThrows(throwsIn);
        assertEquals(300, g.score());
    }

    @Test
    public static void testSimpleStrike() throws Exception {
        throwsIn = new int[]{10, 3, 6};
        g.initItsThrows(throwsIn);
        assertEquals(28, g.score());
        assertEquals(19, g.scoreForRound(1));
        assertEquals(28, g.scoreForRound(2));
    }

    @Test
    public static void testSimpleFrameAfterSpare() throws Exception {
        throwsIn = new int[]{3, 7, 3, 2};
        g.initItsThrows(throwsIn);
        assertEquals(18, g.score());
        assertEquals(13, g.scoreForRound(1));
        assertEquals(18, g.scoreForRound(2));
    }
    @Test
    public static void testSimpleSpare() throws Exception {
        throwsIn = new int[]{3, 7, 3};
        g.initItsThrows(throwsIn);
        assertEquals(13, g.score());
        assertEquals(13, g.scoreForRound(1));
    }

    @Test
    public static void testFourThrowsNoMark() throws Exception {
        throwsIn = new int[]{5, 4, 7, 2};
        g.initItsThrows(throwsIn);
        assertEquals(18, g.score());
        assertEquals(9, g.scoreForRound(1));
        assertEquals(18, g.scoreForRound(2));
    }

    @Test
    public static void testTwoThrowsNoMark() {
        throwsIn = new int[]{5, 4};
        g.initItsThrows(throwsIn);
        assertEquals(9, g.score());
    }
}
