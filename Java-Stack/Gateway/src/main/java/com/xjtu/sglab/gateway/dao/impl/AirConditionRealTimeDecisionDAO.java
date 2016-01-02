package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IAirConditionRealTimeDecisionDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.AirConditionRealTimeDecision;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * AirConditionRealTimeDecision entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.AirConditionRealTimeDecision
 * @author MyEclipse Persistence Tools
 */
@Repository
public class AirConditionRealTimeDecisionDAO implements
		IAirConditionRealTimeDecisionDAO {
	// property constants
	public static final String AIR_CONDITION_DECIDE_AVERAGE_ENERGY = "airConditionDecideAverageEnergy";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved
	 * AirConditionRealTimeDecision entity. All subsequent persist actions of
	 * this entity should use the #update() method. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AirConditionRealTimeDecisionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionRealTimeDecision entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirConditionRealTimeDecision entity) {
		EntityManagerHelper.log("saving AirConditionRealTimeDecision instance",
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
	 * Delete a persistent AirConditionRealTimeDecision entity. This operation
	 * must be performed within the a database transaction context for the
	 * entity's data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AirConditionRealTimeDecisionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionRealTimeDecision entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirConditionRealTimeDecision entity) {
		EntityManagerHelper.log(
				"deleting AirConditionRealTimeDecision instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(
					AirConditionRealTimeDecision.class,
					entity.getAirConditionRealTimeDecisionId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AirConditionRealTimeDecision entity and return
	 * it or a copy of it to the sender. A copy of the
	 * AirConditionRealTimeDecision entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AirConditionRealTimeDecisionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionRealTimeDecision entity to update
	 * @return AirConditionRealTimeDecision the persisted
	 *         AirConditionRealTimeDecision entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirConditionRealTimeDecision update(
			AirConditionRealTimeDecision entity) {
		EntityManagerHelper.log(
				"updating AirConditionRealTimeDecision instance", Level.INFO,
				null);
		try {
			AirConditionRealTimeDecision result = getEntityManager().merge(
					entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AirConditionRealTimeDecision findById(Integer id) {
		EntityManagerHelper.log(
				"finding AirConditionRealTimeDecision instance with id: " + id,
				Level.INFO, null);
		try {
			AirConditionRealTimeDecision instance = getEntityManager().find(
					AirConditionRealTimeDecision.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AirConditionRealTimeDecision entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the AirConditionRealTimeDecision property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<AirConditionRealTimeDecision> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AirConditionRealTimeDecision> findByProperty(
			String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding AirConditionRealTimeDecision instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AirConditionRealTimeDecision model where model."
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

	public List<AirConditionRealTimeDecision> findByAirConditionDecideAverageEnergy(
			Object airConditionDecideAverageEnergy, int... rowStartIdxAndCount) {
		return findByProperty(AIR_CONDITION_DECIDE_AVERAGE_ENERGY,
				airConditionDecideAverageEnergy, rowStartIdxAndCount);
	}

	/**
	 * Find all AirConditionRealTimeDecision entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionRealTimeDecision> all
	 *         AirConditionRealTimeDecision entities
	 */
	@SuppressWarnings("unchecked")
	public List<AirConditionRealTimeDecision> findAll(
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding all AirConditionRealTimeDecision instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from AirConditionRealTimeDecision model";
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