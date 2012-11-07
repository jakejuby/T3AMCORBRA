/**
 * 
 */
package model.data;

import java.util.ArrayList;

/**
 * @author Knoxie
 * 
 */
public class Patient {

	private String firstName = "John";
	private String lastName = "Doe";
	private String birthdate = "Jan 23, 1996";
	private ArrayList<String> allergies = new ArrayList<String>() {
		{
			add("Latex");
			add("Penicillin");
		}
	};
	private String bloodType = "A+";

	public Patient(){
		
	}
	/**
	 * 
	 */
	public Patient(String first, String last, String birthdate, ArrayList<String> allergies) {
		this.setFirstName(first);
		this.setLastName(last);
		this.setBirthdate(birthdate);
		this.setAllergies(allergies);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the allergies
	 */
	public ArrayList<String> getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies
	 *            the allergies to set
	 */
	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}

	/**
	 * Add allergy to patient info
	 * 
	 * @param allergy
	 */
	public void addAllergy(String allergy) {
		this.allergies.add(allergy);
	}

	/**
	 * 
	 * @param allergy
	 *            The allergy to remove
	 * @return True if removed
	 */
	public boolean removeAllergy(String allergy) {
		return this.allergies.remove(allergy);
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
}
