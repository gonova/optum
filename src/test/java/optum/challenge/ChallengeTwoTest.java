package optum.challenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChallengeTwoTest {

    @Test
    public void testOne() {

        // Expected Output << EXPECTED OUTPUT: 2 >>

        assertEquals("2", ChallengeTwo.StringChallenge("abcabc"));
    }

    @Test
    public void testTwo() {

        // Expected Output << EXPECTED OUTPUT: 4 >>

        assertEquals("4", ChallengeTwo.StringChallenge("cccc"));
    }

}
