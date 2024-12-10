/**
 * @author Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.service;

import databank.model.PhysicianPojo;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * Singleton service class that works with PhysicianDaoImpl
 * @see databank.dao.PhysicianDaoImpl
 */
@Singleton
public class PhysicianService {
	
	private static final Logger LOG = LogManager.getLogger();

	//PU_DataBank is defined in the persistence.xml
    @PersistenceContext(unitName = "PU_DataBank")
    private EntityManager em;

    /**
     * Create a new physician
     * @param physician
     */
    public void createPhysician(PhysicianPojo physician) {
    	LOG.debug("Persisting physician: {}", physician);
        em.persist(physician);
    }

    /**
     * Read all physicians
     * @return allPhysiciansQuery.getResultList()
     */
    public List<PhysicianPojo> readAllPhysicians() {
    	LOG.debug("Fetching all physicians");
    	TypedQuery<PhysicianPojo> allPhysiciansQuery = em.createNamedQuery(PhysicianPojo.PHYSICIAN_FIND_ALL, PhysicianPojo.class);
    	return allPhysiciansQuery.getResultList();
    }

    /**
     * Read physician by ID
     * @param physicianId
     * @return em.find(PhysicianPojo.class, physicianId)
     */
    public PhysicianPojo readPhysicianById(int physicianId) {
    	LOG.debug("Finding physician by ID: {}", physicianId);
        return em.find(PhysicianPojo.class, physicianId);
    }

    /**
     * Update an existing physician
     * @param physicianWithUpdates
     * @return em.merge(physicianWithUpdates)
     */
    public PhysicianPojo updatePhysician(PhysicianPojo physicianWithUpdates) {
    	LOG.debug("Merging physician: {}", physicianWithUpdates);
        try{
            return em.merge(physicianWithUpdates);
        }catch(OptimisticLockException e){
            return null;
        }

    }

    /**
     * Delete a physician
     * @param physicianId
     */
    public void deletePhysicianById(int physicianId) {
    	LOG.debug("Deleting physician by ID: {}", physicianId);
        PhysicianPojo physician = readPhysicianById(physicianId);
        if (physician != null) {
            em.remove(physician);
        } else {
            LOG.warn("Physician with ID {} not found for deletion", physicianId);
        }
    }
}
