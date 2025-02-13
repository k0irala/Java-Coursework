package testCases;

import com.herald.models.Competitor;

import java.util.ArrayList;

public class TestCompetitor {
    private Competitor competitor;
    public void testOverAllScore() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        scores.add(100);
        scores.add(100);
        double expected = 100;
        competitor.setScore(scores);
        assert competitor.getOverAllScore() == expected;
    }
}
