import com.enums.CompetitionLevel;

public class ValCompetitor extends Name implements CompetitionLevel {
    private int  CompetitorId;
    private Name Name;
    private competitionLevel level;
    private int age;
    private String gender;
    private String country;
    private double overAllScore;

    public ValCompetitor(int CompetitorId, String firstName, String middleName,String lastName,String credentials) {
        super(firstName,middleName,lastName,credentials);
        this.CompetitorId = CompetitorId;
    }

    public int getCompetitorId() {
        return CompetitorId;
    }

    public void setCompetitorId(int competitorId) {
        CompetitorId = competitorId;
    }
    public Name getName() {
        return Name;
    }
    public void setName(Name name) {
        Name = name;
    }
    public competitionLevel getLevel() {
        return level;
    }
    public void setLevel(competitionLevel level) {
        this.level = level;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public double getOverAllScore(){
        return 5;
    }
    public void setOverAllScore(double overAllScore) {
        this.overAllScore = overAllScore;
    }
    public String getFullDetails(){
        return "Competitor Number" + CompetitorId + ","
                + "Name " + this.getFirstName() + " " + this.getMiddleName() + " " +
                this.getLastName() + ","+ "Country " + country + ".\n"
                +Name.getFirstName() + " is a "
                + "Level " + level + ","
                + "aged " + age + ", and has a overall score of " + overAllScore;
    }
    public String getShortDetails(){
        return "CN "+ CompetitorId + "(" +Name.getCredentials()+") has a overall score "+ overAllScore;
    }
}
