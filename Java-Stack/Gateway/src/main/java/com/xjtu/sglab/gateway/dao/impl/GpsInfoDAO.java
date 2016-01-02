package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IGpsInfoDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.GpsInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * GpsInfo entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.GpsInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class GpsInfoDAO implements IGpsInfoDAO {
	// property constants
	public static final String GPS_LONGITUDE = "gpsLongitude";
	public static final String GPS_LATITUDE = "gpsLatitude";
	public static final String DISTANCE_FROM_HOME = "distanceFromHome";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved GpsInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GpsInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GpsInfo entity) {
		EntityManagerHelper.log("saving GpsInfo instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent GpsInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * GpsInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GpsInfo entity) {
		EntityManagerHelper.log("deleting GpsInfo instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(GpsInfo.class,
					entity.getGpsId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved GpsInfo entity and return it or a copy of it
	 * to the sender. A copy of the GpsInfo entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = GpsInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfo entity to update
	 * @return GpsInfo the persisted GpsInfo entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public GpsInfo update(GpsInfo entity) {
		EntityManagerHelper.log("updating GpsInfo instance", Level.INFO, null);
		try {
			GpsInfo result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public GpsInfo findById(Integer id) {
		EntityManagerHelper.log("finding GpsInfo instance with id: " + id,
				Level.INFO, null);
		try {
			GpsInfo instance = getEntityManager().find(GpsInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all GpsInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the GpsInfo property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<GpsInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<GpsInfo> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding GpsInfo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from GpsInfo model where model."
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

	public List<GpsInfo> findByGpsLongitude(Object gpsLongitude,
			int... rowStartIdxAndCount) {
		return findByProperty(GPS_LONGITUDE, gpsLongitude, rowStartIdxAndCount);
	}

	public List<GpsInfo> findByGpsLatitude(Object gpsLatitude,
			int... rowStartIdxAndCount) {
		return findByProperty(GPS_LATITUDE, gpsLatitude, rowStartIdxAndCount);
	}

	public List<GpsInfo> findByDistanceFromHome(Object distanceFromHome,
			int... rowStartIdxAndCount) {
		return findByProperty(DISTANCE_FROM_HOME, distanceFromHome,
				rowStartIdxAndCount);
	}

	/**
	 * Find all GpsInfo entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<GpsInfo> all GpsInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<GpsInfo> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all GpsInfo instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from GpsInfo model";
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