package models;

import enums.CompetitionLevel;

import java.util.ArrayList;
public class Competitor extends Name {
	private int competitorId;
	private CompetitionLevel level;
	private int age;
	private String gender;
	private String country;
	private double overAllScore;

	private ArrayList<Integer> scores;

	public Competitor(int competitorId, String firstName, String middleName, String lastName) {
		super(firstName, middleName, lastName);
		this.competitorId = competitorId;
	}

	public int getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
	}

	public CompetitionLevel getLevel() {
		return level;
	}

	public void setLevel(CompetitionLevel level) {
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
	public void setScore(ArrayList<Integer> scores) {
		this.scores = scores;
	}

	public double getOverAllScore() {
		int total = 0;
		for (int score : scores) {
			total += score;
		}
		return (double) total / scores.size();
	}

	public void setOverAllScore(double overAllScore) {
		this.overAllScore = overAllScore;
	}

	public ArrayList<Integer> getScoreArray() {
		return scores;
	}

	public String getFullDetails() {
		return "Competitor Number:" + competitorId + "," + "Name " + getFirstName() + " " + getMiddleName() + " "
				+ getLastName() + ",\n" + "Country " + country + ".\n" + getFirstName() + " is a " + "Level " + level
				+ "," + "aged " + age + ", and has a overall score of " + String.format("%.2f",getOverAllScore()) + ".";
	}

	public String getShortDetails() {
		return "CN " + competitorId + "(" + getCredentials() + ") has a overall score " + String.format("%.2f",getOverAllScore()) + ".";
	}
}
