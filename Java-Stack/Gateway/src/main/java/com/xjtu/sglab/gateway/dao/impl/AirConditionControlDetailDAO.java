package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IAirConditionControlDetailDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.AirConditionControlDetail;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * AirConditionControlDetail entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.AirConditionControlDetail
 * @author MyEclipse Persistence Tools
 */
@Repository
public class AirConditionControlDetailDAO implements
		IAirConditionControlDetailDAO {
	// property constants
	public static final String AIR_CONDITION_TEMPERATURE = "airConditionTemperature";
	public static final String AIR_CONDITION_MODE = "airConditionMode";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AirConditionControlDetail
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AirConditionControlDetailDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionControlDetail entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirConditionControlDetail entity) {
		EntityManagerHelper.log("saving AirConditionControlDetail instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AirConditionControlDetail entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AirConditionControlDetailDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionControlDetail entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirConditionControlDetail entity) {
		EntityManagerHelper.log("deleting AirConditionControlDetail instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					AirConditionControlDetail.class,
					entity.getAriConditionControlId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AirConditionControlDetail entity and return it
	 * or a copy of it to the sender. A copy of the AirConditionControlDetail
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AirConditionControlDetailDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionControlDetail entity to update
	 * @return AirConditionControlDetail the persisted AirConditionControlDetail
	 *         entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirConditionControlDetail update(AirConditionControlDetail entity) {
		EntityManagerHelper.log("updating AirConditionControlDetail instance",
				Level.INFO, null);
		try {
			AirConditionControlDetail result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AirConditionControlDetail findById(Integer id) {
		EntityManagerHelper.log(
				"finding AirConditionControlDetail instance with id: " + id,
				Level.INFO, null);
		try {
			AirConditionControlDetail instance = getEntityManager().find(
					AirConditionControlDetail.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AirConditionControlDetail entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the AirConditionControlDetail property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<AirConditionControlDetail> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AirConditionControlDetail> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding AirConditionControlDetail instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AirConditionControlDetail model where model."
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

	public List<AirConditionControlDetail> findByAirConditionTemperature(
			Object airConditionTemperature, int... rowStartIdxAndCount) {
		return findByProperty(AIR_CONDITION_TEMPERATURE,
				airConditionTemperature, rowStartIdxAndCount);
	}

	public List<AirConditionControlDetail> findByAirConditionMode(
			Object airConditionMode, int... rowStartIdxAndCount) {
		return findByProperty(AIR_CONDITION_MODE, airConditionMode,
				rowStartIdxAndCount);
	}

	/**
	 * Find all AirConditionControlDetail entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionControlDetail> all AirConditionControlDetail
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<AirConditionControlDetail> findAll(
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding all AirConditionControlDetail instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from AirConditionControlDetail model";
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