package com.herald.models;

import com.herald.enums.CompetitionLevel;

/*
 *
 * @author Koira
 *
 */
import java.util.ArrayList;

/**
 * Class for competitor
 * @author Koira
 *
 */
public class Competitor extends Name {
	private int competitorId;
	private CompetitionLevel level;
	private int age;
	private String gender;
	private String country;
	private double overAllScore;

	private ArrayList<Integer> scores;

	/**
	 * Constructor for a competitor
	 * @param competitorId competitor id
	 * @param firstName first name
	 * @param middleName middle name
	 * @param lastName last name
	 */
	public Competitor(int competitorId, String firstName, String middleName, String lastName) {
		super(firstName, middleName, lastName);
		this.competitorId = competitorId;
	}

	/**
	 * Getter for competitor id
	 * @return competitor id
	 */
	public int getCompetitorId() {
		return competitorId;
	}

	/**
	 * Setter for competitor id
	 * @param competitorId competitor id
	 */
	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
	}

	/**
	 * Getter for competition level
	 * @return competition level
	 */
	public CompetitionLevel getLevel() {
		return level;
	}

	/**
	 * Setter for competition level
	 * @param level competition level
	 */
	public void setLevel(CompetitionLevel level) {
		this.level = level;
	}
	/**
	 * Getter for age
	 * @return age
	 */

	public int getAge() {
		return age;
	}
	/**
	 * Setter for age
	 * @param age age
	 */

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Getter for gender
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for gender
	 * @param gender gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Getter for country
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Setter for country
	 * @param country country
	 */

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Setter for score
	 * @param scores score
	 */
	public void setScore(ArrayList<Integer> scores) {
		this.scores = scores;
	}

	/**
	 * Getter for overall score
	 * @return overall score
	 */
	public double getOverAllScore() {
		int total = 0;
		for (int score : scores) {
			total += score;
		}
		return (double) total / scores.size();
	}

	/**
	 * Setter for overall score
	 * @param overAllScore overall score
	 */

	public void setOverAllScore(double overAllScore) {
		this.overAllScore = overAllScore;
	}

	/**
	 * Getter for score array
	 * @return score array
	 */
	public ArrayList<Integer> getScoreArray() {
		return scores;
	}

	/**
	 * Getter for full details
	 * @return full details
	 */

	public String getFullDetails() {
		return "Competitor Number:" + competitorId + "," + "Name " + getFirstName() + " " + getMiddleName() + " "
				+ getLastName() + ",\n" + "Country " + country + ".\n" + getFirstName() + " is a " + "Level " + level
				+ "," + "aged " + age + ", and has a overall score of " + String.format("%.2f",getOverAllScore()) + ".";
	}

	/**
	 * Getter for short details
	 * @return short details
	 */
	public String getShortDetails() {
		return "CN " + competitorId + "(" + getCredentials() + ") has a overall score " + String.format("%.2f",getOverAllScore()) + ".";
	}
}
