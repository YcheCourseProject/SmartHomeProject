package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.ILampDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.Lamp;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for Lamp
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.Lamp
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LampDAO implements ILampDAO {
	// property constants
	public static final String LAMP_TYPE = "lampType";
	public static final String LAMP_RATED_POWER = "lampRatedPower";
	public static final String LAMP_LOCATION = "lampLocation";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Lamp entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LampDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lamp entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Lamp entity) {
		EntityManagerHelper.log("saving Lamp instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Lamp entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LampDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Lamp entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Lamp entity) {
		EntityManagerHelper.log("deleting Lamp instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Lamp.class,
					entity.getLampId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Lamp entity and return it or a copy of it to
	 * the sender. A copy of the Lamp entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = LampDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lamp entity to update
	 * @return Lamp the persisted Lamp entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Lamp update(Lamp entity) {
		EntityManagerHelper.log("updating Lamp instance", Level.INFO, null);
		try {
			Lamp result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Lamp findById(Integer id) {
		EntityManagerHelper.log("finding Lamp instance with id: " + id,
				Level.INFO, null);
		try {
			Lamp instance = getEntityManager().find(Lamp.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Lamp entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Lamp property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Lamp> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Lamp> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Lamp instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Lamp model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Lamp> findByLampType(Object lampType,
			int... rowStartIdxAndCount) {
		return findByProperty(LAMP_TYPE, lampType, rowStartIdxAndCount);
	}

	public List<Lamp> findByLampRatedPower(Object lampRatedPower,
			int... rowStartIdxAndCount) {
		return findByProperty(LAMP_RATED_POWER, lampRatedPower,
				rowStartIdxAndCount);
	}

	public List<Lamp> findByLampLocation(Object lampLocation,
			int... rowStartIdxAndCount) {
		return findByProperty(LAMP_LOCATION, lampLocation, rowStartIdxAndCount);
	}

	/**
	 * Find all Lamp entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Lamp> all Lamp entities
	 */
	@SuppressWarnings("unchecked")
	public List<Lamp> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Lamp instances", Level.INFO, null);
		try {
			final String queryString = "select model from Lamp model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}