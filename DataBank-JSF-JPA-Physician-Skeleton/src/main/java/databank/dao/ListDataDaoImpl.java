/********************************************************************************************************2*4*w*
 * File:  ListDataDaoImpl.java Course Materials CST8277
 *
 * @author Teddy Yap
 * Implemented by Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;

@SuppressWarnings("unused")
/**
 * Description:  API for reading list data from the database
 */
@Named
@ApplicationScoped
public class ListDataDaoImpl implements ListDataDao, Serializable {
	/** Explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final String READ_ALL_SPECIALTIES = "SELECT * FROM specialty";;

	@PersistenceContext(name = "PU_DataBank")
	protected EntityManager em;

	@Inject
	protected ExternalContext externalContext;

	/**
	 * @param msg
	 */
	private void logMsg(String msg) {
		((ServletContext) externalContext.getContext()).log(msg);
	}

	/**
	 * @return specialties
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> readAllSpecialties() {
		logMsg("reading all specialties");
		List<String> specialties = new ArrayList<>();
		try {
			specialties = (List<String>) em.createNativeQuery(READ_ALL_SPECIALTIES).getResultList();
		}
		catch (Exception e) {
			logMsg("something went wrong:  " + e.getLocalizedMessage());
		}
		return specialties;
	}

}
