/********************************************************************************************************2*4*w*
 * File:  PhysicianDaoImpl.java Course Materials CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 * Implemented by Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import databank.model.PhysicianPojo;
import databank.service.PhysicianService;


/**
 * Description:  Implements the C-R-U-D API for the database
 */
@Named
@ApplicationScoped
public class PhysicianDaoImpl implements PhysicianDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	//Get the log4j2 logger for this class
	private static final Logger LOG = LogManager.getLogger();

	

	 // Inject PhysicianService with EJB for CRUD operations
	 @EJB
	 private PhysicianService physicianService;

	/**
	 * @return physicianService.readAllPhysicians()
	 */
	@Override
	 public List<PhysicianPojo> readAllPhysicians() {
	        LOG.debug("Delegating to PhysicianService to find all physicians");
	        return physicianService.readAllPhysicians();
	    }

	/**
	 * @param physician
	 * @return physician
	 */
     @Override
     @Transactional
	 public PhysicianPojo createPhysician(PhysicianPojo physician) {
	        LOG.debug("Delegating to PhysicianService to create a physician: {}", physician);
	        physicianService.createPhysician(physician);
	        return physician;
	    }

	/**
	 * @param physicianId
	 * @return physicianService.readPhysicianById(physicianId)
	 */
	  @Override
	  public PhysicianPojo readPhysicianById(int physicianId) {
	        LOG.debug("Delegating to PhysicianService to find physician by ID: {}", physicianId);
	        return physicianService.readPhysicianById(physicianId);
	  }

	/**
	 * @param physicianWithUpdates
	 * @return physicianService.updatePhysician(physicianWithUpdates)
	 */
	@Override
	  @Transactional
	  public PhysicianPojo updatePhysician(PhysicianPojo physicianWithUpdates) {
	        LOG.debug("Delegating to PhysicianService to update a physician: {}", physicianWithUpdates);
	        return physicianService.updatePhysician(physicianWithUpdates);
	}

	/**
 	 * @param physicianId
	 */
	@Override
	@Transactional
	public void deletePhysicianById(int physicianId) {
		LOG.debug("Delegating to PhysicianService to delete physician by ID: {}", physicianId);
		physicianService.deletePhysicianById(physicianId);
	}
}