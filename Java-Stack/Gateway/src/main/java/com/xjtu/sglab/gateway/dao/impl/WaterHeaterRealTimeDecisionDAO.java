package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IWaterHeaterRealTimeDecisionDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.WaterHeaterRealTimeDecision;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * WaterHeaterRealTimeDecision entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.WaterHeaterRealTimeDecision
 * @author MyEclipse Persistence Tools
 */
@Repository
public class WaterHeaterRealTimeDecisionDAO implements
		IWaterHeaterRealTimeDecisionDAO {
	// property constants
	public static final String WATER_HEATER_CONSUME_AVERAGE_ENERGY = "waterHeaterConsumeAverageEnergy";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved
	 * WaterHeaterRealTimeDecision entity. All subsequent persist actions of
	 * this entity should use the #update() method. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WaterHeaterRealTimeDecisionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterRealTimeDecision entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeaterRealTimeDecision entity) {
		EntityManagerHelper.log("saving WaterHeaterRealTimeDecision instance",
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
	 * Delete a persistent WaterHeaterRealTimeDecision entity. This operation
	 * must be performed within the a database transaction context for the
	 * entity's data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WaterHeaterRealTimeDecisionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterRealTimeDecision entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeaterRealTimeDecision entity) {
		EntityManagerHelper.log(
				"deleting WaterHeaterRealTimeDecision instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(
					WaterHeaterRealTimeDecision.class,
					entity.getWaterHeaterRealTimeDecisionId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved WaterHeaterRealTimeDecision entity and return
	 * it or a copy of it to the sender. A copy of the
	 * WaterHeaterRealTimeDecision entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WaterHeaterRealTimeDecisionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterRealTimeDecision entity to update
	 * @return WaterHeaterRealTimeDecision the persisted
	 *         WaterHeaterRealTimeDecision entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeaterRealTimeDecision update(WaterHeaterRealTimeDecision entity) {
		EntityManagerHelper.log(
				"updating WaterHeaterRealTimeDecision instance", Level.INFO,
				null);
		try {
			WaterHeaterRealTimeDecision result = getEntityManager().merge(
					entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public WaterHeaterRealTimeDecision findById(Integer id) {
		EntityManagerHelper.log(
				"finding WaterHeaterRealTimeDecision instance with id: " + id,
				Level.INFO, null);
		try {
			WaterHeaterRealTimeDecision instance = getEntityManager().find(
					WaterHeaterRealTimeDecision.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all WaterHeaterRealTimeDecision entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeaterRealTimeDecision property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<WaterHeaterRealTimeDecision> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WaterHeaterRealTimeDecision> findByProperty(
			String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding WaterHeaterRealTimeDecision instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from WaterHeaterRealTimeDecision model where model."
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

	public List<WaterHeaterRealTimeDecision> findByWaterHeaterConsumeAverageEnergy(
			Object waterHeaterConsumeAverageEnergy, int... rowStartIdxAndCount) {
		return findByProperty(WATER_HEATER_CONSUME_AVERAGE_ENERGY,
				waterHeaterConsumeAverageEnergy, rowStartIdxAndCount);
	}

	/**
	 * Find all WaterHeaterRealTimeDecision entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterRealTimeDecision> all WaterHeaterRealTimeDecision
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<WaterHeaterRealTimeDecision> findAll(
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding all WaterHeaterRealTimeDecision instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from WaterHeaterRealTimeDecision model";
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