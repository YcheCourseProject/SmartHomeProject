package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.GpsInfo;

/**
 * Interface for GpsInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IGpsInfoDAO {
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
	 * IGpsInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GpsInfo entity);

	/**
	 * Delete a persistent GpsInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGpsInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GpsInfo entity);

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
	 * entity = IGpsInfoDAO.update(entity);
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
	public GpsInfo update(GpsInfo entity);

	public GpsInfo findById(Integer id);

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
	 *            count of results to return.
	 * @return List<GpsInfo> found by query
	 */
	public List<GpsInfo> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<GpsInfo> findByGpsLongitude(Object gpsLongitude,
			int... rowStartIdxAndCount);

	public List<GpsInfo> findByGpsLatitude(Object gpsLatitude,
			int... rowStartIdxAndCount);

	public List<GpsInfo> findByDistanceFromHome(Object distanceFromHome,
			int... rowStartIdxAndCount);

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
	public List<GpsInfo> findAll(int... rowStartIdxAndCount);
}