package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.GpsInfoToDemand;

/**
 * Interface for GpsInfoToDemandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IGpsInfoToDemandDAO {
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
	 * IGpsInfoToDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfoToDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GpsInfoToDemand entity);

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
	 * IGpsInfoToDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GpsInfoToDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GpsInfoToDemand entity);

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
	 * entity = IGpsInfoToDemandDAO.update(entity);
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
	public GpsInfoToDemand update(GpsInfoToDemand entity);

	public GpsInfoToDemand findById(Integer id);

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
	 *            count of results to return.
	 * @return List<GpsInfoToDemand> found by query
	 */
	public List<GpsInfoToDemand> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<GpsInfoToDemand> findByDistanceFromHome(
			Object distanceFromHome, int... rowStartIdxAndCount);

	public List<GpsInfoToDemand> findByGpsInfoBeginTime(
			Object gpsInfoBeginTime, int... rowStartIdxAndCount);

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
	public List<GpsInfoToDemand> findAll(int... rowStartIdxAndCount);
}