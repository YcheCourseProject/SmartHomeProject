package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IGpsInfoToDemandDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.GpsInfoToDemand;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * GpsInfoToDemand entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.GpsInfoToDemand
 * @author MyEclipse Persistence Tools
 */
@Repository
public class GpsInfoToDemandDAO implements IGpsInfoToDemandDAO {
	// property constants
	public static final String DISTANCE_FROM_HOME = "distanceFromHome";
	public static final String GPS_INFO_BEGIN_TIME = "gpsInfoBeginTime";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved GpsInfoToDemand entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GpsInfoToDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfoToDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GpsInfoToDemand entity) {
		EntityManagerHelper.log("saving GpsInfoToDemand instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent GpsInfoToDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GpsInfoToDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfoToDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GpsInfoToDemand entity) {
		EntityManagerHelper.log("deleting GpsInfoToDemand instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(GpsInfoToDemand.class,
					entity.getGpsInfoToDemandId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved GpsInfoToDemand entity and return it or a copy
	 * of it to the sender. A copy of the GpsInfoToDemand entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = GpsInfoToDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfoToDemand entity to update
	 * @return GpsInfoToDemand the persisted GpsInfoToDemand entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public GpsInfoToDemand update(GpsInfoToDemand entity) {
		EntityManagerHelper.log("updating GpsInfoToDemand instance",
				Level.INFO, null);
		try {
			GpsInfoToDemand result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public GpsInfoToDemand findById(Integer id) {
		EntityManagerHelper.log("finding GpsInfoToDemand instance with id: "
				+ id, Level.INFO, null);
		try {
			GpsInfoToDemand instance = getEntityManager().find(
					GpsInfoToDemand.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all GpsInfoToDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the GpsInfoToDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<GpsInfoToDemand> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<GpsInfoToDemand> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding GpsInfoToDemand instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from GpsInfoToDemand model where model."
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

	public List<GpsInfoToDemand> findByDistanceFromHome(
			Object distanceFromHome, int... rowStartIdxAndCount) {
		return findByProperty(DISTANCE_FROM_HOME, distanceFromHome,
				rowStartIdxAndCount);
	}

	public List<GpsInfoToDemand> findByGpsInfoBeginTime(
			Object gpsInfoBeginTime, int... rowStartIdxAndCount) {
		return findByProperty(GPS_INFO_BEGIN_TIME, gpsInfoBeginTime,
				rowStartIdxAndCount);
	}

	/**
	 * Find all GpsInfoToDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<GpsInfoToDemand> all GpsInfoToDemand entities
	 */
	@SuppressWarnings("unchecked")
	public List<GpsInfoToDemand> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all GpsInfoToDemand instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from GpsInfoToDemand model";
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