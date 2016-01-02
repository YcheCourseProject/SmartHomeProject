package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IWearableInfoToDemandDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.WearableInfoToDemand;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * WearableInfoToDemand entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.WearableInfoToDemand
 * @author MyEclipse Persistence Tools
 */
@Repository
public class WearableInfoToDemandDAO implements IWearableInfoToDemandDAO {
	// property constants
	public static final String HEART_RATE = "heartRate";
	public static final String BODY_TEMPERATURE = "bodyTemperature";
	public static final String WEARABLE_INFO_BEGIN_TIME = "wearableInfoBeginTime";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved WearableInfoToDemand
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WearableInfoToDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WearableInfoToDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WearableInfoToDemand entity) {
		EntityManagerHelper.log("saving WearableInfoToDemand instance",
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
	 * Delete a persistent WearableInfoToDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WearableInfoToDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WearableInfoToDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WearableInfoToDemand entity) {
		EntityManagerHelper.log("deleting WearableInfoToDemand instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					WearableInfoToDemand.class,
					entity.getWearableInfoToDemandId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved WearableInfoToDemand entity and return it or a
	 * copy of it to the sender. A copy of the WearableInfoToDemand entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WearableInfoToDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WearableInfoToDemand entity to update
	 * @return WearableInfoToDemand the persisted WearableInfoToDemand entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WearableInfoToDemand update(WearableInfoToDemand entity) {
		EntityManagerHelper.log("updating WearableInfoToDemand instance",
				Level.INFO, null);
		try {
			WearableInfoToDemand result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public WearableInfoToDemand findById(Integer id) {
		EntityManagerHelper.log(
				"finding WearableInfoToDemand instance with id: " + id,
				Level.INFO, null);
		try {
			WearableInfoToDemand instance = getEntityManager().find(
					WearableInfoToDemand.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all WearableInfoToDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WearableInfoToDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<WearableInfoToDemand> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WearableInfoToDemand> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding WearableInfoToDemand instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from WearableInfoToDemand model where model."
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

	public List<WearableInfoToDemand> findByHeartRate(Object heartRate,
			int... rowStartIdxAndCount) {
		return findByProperty(HEART_RATE, heartRate, rowStartIdxAndCount);
	}

	public List<WearableInfoToDemand> findByBodyTemperature(
			Object bodyTemperature, int... rowStartIdxAndCount) {
		return findByProperty(BODY_TEMPERATURE, bodyTemperature,
				rowStartIdxAndCount);
	}

	public List<WearableInfoToDemand> findByWearableInfoBeginTime(
			Object wearableInfoBeginTime, int... rowStartIdxAndCount) {
		return findByProperty(WEARABLE_INFO_BEGIN_TIME, wearableInfoBeginTime,
				rowStartIdxAndCount);
	}

	/**
	 * Find all WearableInfoToDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WearableInfoToDemand> all WearableInfoToDemand entities
	 */
	@SuppressWarnings("unchecked")
	public List<WearableInfoToDemand> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all WearableInfoToDemand instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from WearableInfoToDemand model";
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