import com.enums.CompetitionLevel;

public class Implementation {
    public static void main(String[] args) {
        ValCompetitor competitor = new ValCompetitor(1, "Saurav", "","Koirala", "SK");
        competitor.setName(new Name("Saurav","","Koirala","SK"));
        competitor.setCountry("India");
        competitor.setAge(25);
        competitor.setGender("Male");
        competitor.setLevel(CompetitionLevel.competitionLevel.Beginner);
        competitor.setOverAllScore(5);
        System.out.println(competitor.getFullDetails());
        System.out.println(competitor.getShortDetails());

    }
}
