package optum.challenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChallengeOneTest {

    @Test
    public void testOne() {

        // << EXPECTED OUTPUT: div_y6i_nuo_4f >>

        assertEquals("div_y6i_nuo_4f", ChallengeOne.StringChallenge("<div><div><b></b></div></p>"));
    }

    @Test
    public void testTwo() {

        // << EXPECTED OUTPUT: i2y_icn_ob4_ >>

        assertEquals("i2y_icn_ob4_", ChallengeOne.StringChallenge("<div>abc</div><p><em><i>test test test</b></em></p>"));
    }
}
