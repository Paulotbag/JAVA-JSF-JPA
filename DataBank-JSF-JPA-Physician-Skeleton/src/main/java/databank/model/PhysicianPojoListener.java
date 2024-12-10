/********************************************************************************************************2*4*w*
 * File:  PhysicianPojoListener.java Course Materials CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 * Implemented by Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.model;

import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


/**
 * Listener Class for PhysicianPojo
 * @see PhysicianPojo
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/entity-listeners.html<br>
 */
public class PhysicianPojoListener {

	/**
	 * @param physician
	 */
	@PrePersist
	public void setCreatedOnDate(PhysicianPojo physician) {
		LocalDateTime now = LocalDateTime.now();
		physician.setCreated(now);
		//Might as well call setUpdated as well
		physician.setUpdated(now);
	}

	/**
	 * @param physician
	 */
	@PreUpdate
	public void setUpdatedDate(PhysicianPojo physician) {
		physician.setUpdated(LocalDateTime.now());
	}

}
