/********************************************************************************************************2*4*w*
 * File:  NewPhysicianView.java Course Materials CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 * Implemented by Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;
import databank.model.PhysicianPojo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


/**
 * This class represents the scope of adding a new physician to the DB.
 */
@Named("newPhysician")
@ViewScoped //<
public class NewPhysicianView implements Serializable {
	/** explicit set serialVersionUID */
	private static final long serialVersionUID = 1L;

	protected String lastName;
    protected String firstName;
    protected String email;
    protected String phoneNumber;
    protected String specialty;
    protected LocalDateTime created;
	protected int version;

	@Inject
	@ManagedProperty("#{physicianController}")
	protected PhysicianController physicianController;

	public NewPhysicianView() {
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
        return email;
    }

	/**
	 * @param email
	 */
    public void setEmail(String email) {
        this.email = email;
    }

	/**
	 * @return phoneNumber
	 */
    public String getPhoneNumber() {
        return phoneNumber;
    }

	/**
	 * @param phoneNumber
	 */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	/**
	 * @return specialty
	 */
    public String getSpecialty() {
        return specialty;
    }

	/**
	 * @param specialty
	 */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

	/**
	 * @return created
	 */
    public LocalDateTime getCreated() {
        return created;
    }

	/**
	 * @param created
	 */
	public void setCreated(LocalDateTime created) {
        this.created = created;
    }

	/**
	 * @return version
	 */
	public int getVersion() { return version; }

	/**
	 * @param version
	 */
	public void setVersion(int version) { this.version = version; }


	public void addPhysician() {
		if (allNotNullOrEmpty(firstName, lastName,  email, phoneNumber, specialty, version	 /* TODO 11 - Don't forget to add the other variables that are not nullable */)) {

			PhysicianPojo theNewPhysician = new PhysicianPojo();
			//Call setters
			theNewPhysician.setFirstName(getFirstName());
			theNewPhysician.setLastName(getLastName());
			theNewPhysician.setEmail(getEmail());
            theNewPhysician.setPhoneNumber(getPhoneNumber());
            theNewPhysician.setSpecialty(getSpecialty());
            theNewPhysician.setCreated(LocalDateTime.now());
			theNewPhysician.setVersion(getVersion());
			
			physicianController.addNewPhysician(theNewPhysician);
			//Clean up
			//Set everything to null
			setFirstName(null);
			setLastName(null);
			setEmail(null);
            setPhoneNumber(null);
            setSpecialty(null);
            setCreated(null);
			setVersion(0);
            
            physicianController.toggleAdding();
		}
	}

	/**
	 * @param values
	 * @return boolean
	 */
	static boolean allNotNullOrEmpty(final Object... values) {
		if (values == null) {
			return false;
		}
		for (final Object val : values) {
			if (val == null) {
				return false;
			}
			if (val instanceof String) {
				String str = (String) val;
				if (str.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}
