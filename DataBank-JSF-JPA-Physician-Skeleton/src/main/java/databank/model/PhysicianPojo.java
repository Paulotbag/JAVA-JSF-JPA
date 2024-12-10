/********************************************************************************************************2*4*w*
 * File:  PhysicianPojo.java Course Materials CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 * Implemented by Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.*;

/**
 * Model POJO class for physicians
 */
@ViewScoped
@Entity(name = "Physician")
@EntityListeners(PhysicianPojoListener.class)
@Table(name = "PHYSICIAN", catalog = "databank", schema = "")
@Access(AccessType.FIELD)
@NamedQuery(name = PhysicianPojo.PHYSICIAN_FIND_ALL, query = "SELECT p FROM Physician p")
@NamedQuery(name = PhysicianPojo.PHYSICIAN_FIND_ID, query = "SELECT p FROM Physician p WHERE p.id = :id")
public class PhysicianPojo implements Serializable {


	private static final long serialVersionUID = 1L;

	public static final String PHYSICIAN_FIND_ALL = "Physician.findAll";
	public static final String PHYSICIAN_FIND_ID = "Physician.findById";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;


    @Column(name = "last_name")
	protected String lastName;
	

    @Column(name = "first_name")
	protected String firstName;


    @Column(name = "email")
	protected String email;


    @Column(name = "phone")
	protected String phoneNumber;


    @Column(name = "specialty")
    protected String specialty;


    @Column(name = "created")
    protected LocalDateTime created;


    @Column(name = "updated")
    protected LocalDateTime updated;

    @Version
    @Column(name = "version")
	protected int version = 1;

	@Transient
	protected boolean editable;

	public PhysicianPojo() {
		super();
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
	 * @return editable
	 */
	public boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id new value for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the value for lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the value for firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName
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
	 * @return updated
	 */
	public LocalDateTime getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 */
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	/**
	 * @return version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Very important:  Use getter's for member variables because JPA sometimes needs to intercept those calls<br/>
	 * and go to the database to retrieve the value.
	 * @return prime * result + Objects.hash(getId())
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		// Only include member variables that really contribute to an object's identity
		// i.e. if variables like version/updated/name/etc. change throughout an object's lifecycle,
		// they shouldn't be part of the hashCode calculation
		return prime * result + Objects.hash(getId());
	}

	/**
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		/* Enhanced instanceof - yeah!
		 * As of JDK 14, no need for additional 'silly' cast:
		    if (animal instanceof Cat) {
		        Cat cat = (Cat) animal;
		        cat.meow();
                // Other class Cat operations ...
            }
         * Technically, 'otherPhysicianPojo' is a <i>pattern</i> that becomes an in-scope variable binding.
         * Note:  Need to watch out just-in-case there is already a 'otherPhysicianPojo' variable in-scope!
		 */
		if (obj instanceof PhysicianPojo otherPhysicianPojo) {
			// See comment (above) in hashCode():  compare using only member variables that are
			// truly part of an object's identity.
			return Objects.equals(this.getId(), otherPhysicianPojo.getId());
		}
		return false;
	}

	/**
	 * @return builder.toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Physician [id = ").append(getId());
		if (getLastName() != null) {
			builder.append(", ").append("lastName = ").append(getLastName());
		}
		if (getFirstName() != null) {
			builder.append(", ").append("firstName = ").append(getFirstName());
		}
		if (getPhoneNumber() != null) {
			builder.append(", ").append("phoneNumber = ").append(getPhoneNumber());
		}
		if (getEmail() != null) {
			builder.append(", ").append("email = ").append(getEmail());
		}
		if (getSpecialty() != null) {
			builder.append(", ").append("specialty = ").append(getSpecialty());
		}
		if (getCreated() != null) {
			builder.append(", ").append("created = ").append(getCreated());
		}
		if (getUpdated() != null) {
			builder.append(", ").append("updated = ").append(getUpdated());
		}
		builder.append("]");
		return builder.toString();
	}
	
	@PrePersist
	protected void setCreatedOnDate() {
	    this.created = LocalDateTime.now();
	    this.updated = this.created; 
	}

	@PreUpdate
	protected void setUpdatedOnDate() {
	    this.updated = LocalDateTime.now();
	}

}
