package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IWearableDeviceInfoDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.WearableDeviceInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * WearableDeviceInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.WearableDeviceInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class WearableDeviceInfoDAO implements IWearableDeviceInfoDAO {
	// property constants
	public static final String ACCELERATED_SPEED_X = "acceleratedSpeedX";
	public static final String ACCELERATED_SPEED_Y = "acceleratedSpeedY";
	public static final String ACCELERATED_SPEED_Z = "acceleratedSpeedZ";
	public static final String GYROSCOPE_X = "gyroscopeX";
	public static final String GYROSCOPE_Y = "gyroscopeY";
	public static final String GYROSCOPE_Z = "gyroscopeZ";
	public static final String BODY_TEMPERATURE = "bodyTemperature";
	public static final String HEART_RATE = "heartRate";
	public static final String SPEED = "speed";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved WearableDeviceInfo
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WearableDeviceInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WearableDeviceInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WearableDeviceInfo entity) {
		EntityManagerHelper.log("saving WearableDeviceInfo instance",
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
	 * Delete a persistent WearableDeviceInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WearableDeviceInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WearableDeviceInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WearableDeviceInfo entity) {
		EntityManagerHelper.log("deleting WearableDeviceInfo instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(WearableDeviceInfo.class,
					entity.getWearableInfoId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved WearableDeviceInfo entity and return it or a
	 * copy of it to the sender. A copy of the WearableDeviceInfo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WearableDeviceInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WearableDeviceInfo entity to update
	 * @return WearableDeviceInfo the persisted WearableDeviceInfo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WearableDeviceInfo update(WearableDeviceInfo entity) {
		EntityManagerHelper.log("updating WearableDeviceInfo instance",
				Level.INFO, null);
		try {
			WearableDeviceInfo result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public WearableDeviceInfo findById(Integer id) {
		EntityManagerHelper.log("finding WearableDeviceInfo instance with id: "
				+ id, Level.INFO, null);
		try {
			WearableDeviceInfo instance = getEntityManager().find(
					WearableDeviceInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all WearableDeviceInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WearableDeviceInfo property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<WearableDeviceInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WearableDeviceInfo> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding WearableDeviceInfo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from WearableDeviceInfo model where model."
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

	public List<WearableDeviceInfo> findByAcceleratedSpeedX(
			Object acceleratedSpeedX, int... rowStartIdxAndCount) {
		return findByProperty(ACCELERATED_SPEED_X, acceleratedSpeedX,
				rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByAcceleratedSpeedY(
			Object acceleratedSpeedY, int... rowStartIdxAndCount) {
		return findByProperty(ACCELERATED_SPEED_Y, acceleratedSpeedY,
				rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByAcceleratedSpeedZ(
			Object acceleratedSpeedZ, int... rowStartIdxAndCount) {
		return findByProperty(ACCELERATED_SPEED_Z, acceleratedSpeedZ,
				rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByGyroscopeX(Object gyroscopeX,
			int... rowStartIdxAndCount) {
		return findByProperty(GYROSCOPE_X, gyroscopeX, rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByGyroscopeY(Object gyroscopeY,
			int... rowStartIdxAndCount) {
		return findByProperty(GYROSCOPE_Y, gyroscopeY, rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByGyroscopeZ(Object gyroscopeZ,
			int... rowStartIdxAndCount) {
		return findByProperty(GYROSCOPE_Z, gyroscopeZ, rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByBodyTemperature(
			Object bodyTemperature, int... rowStartIdxAndCount) {
		return findByProperty(BODY_TEMPERATURE, bodyTemperature,
				rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findByHeartRate(Object heartRate,
			int... rowStartIdxAndCount) {
		return findByProperty(HEART_RATE, heartRate, rowStartIdxAndCount);
	}

	public List<WearableDeviceInfo> findBySpeed(Object speed,
			int... rowStartIdxAndCount) {
		return findByProperty(SPEED, speed, rowStartIdxAndCount);
	}

	/**
	 * Find all WearableDeviceInfo entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WearableDeviceInfo> all WearableDeviceInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<WearableDeviceInfo> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all WearableDeviceInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from WearableDeviceInfo model";
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