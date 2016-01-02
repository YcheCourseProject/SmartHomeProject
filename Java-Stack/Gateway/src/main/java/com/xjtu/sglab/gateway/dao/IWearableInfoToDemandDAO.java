package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.WearableInfoToDemand;

/**
 * Interface for WearableInfoToDemandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWearableInfoToDemandDAO {
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
	 * IWearableInfoToDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WearableInfoToDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WearableInfoToDemand entity);

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
	 * IWearableInfoToDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WearableInfoToDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WearableInfoToDemand entity);

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
	 * entity = IWearableInfoToDemandDAO.update(entity);
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
	public WearableInfoToDemand update(WearableInfoToDemand entity);

	public WearableInfoToDemand findById(Integer id);

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
	 *            count of results to return.
	 * @return List<WearableInfoToDemand> found by query
	 */
	public List<WearableInfoToDemand> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<WearableInfoToDemand> findByHeartRate(Object heartRate,
			int... rowStartIdxAndCount);

	public List<WearableInfoToDemand> findByBodyTemperature(
			Object bodyTemperature, int... rowStartIdxAndCount);

	public List<WearableInfoToDemand> findByWearableInfoBeginTime(
			Object wearableInfoBeginTime, int... rowStartIdxAndCount);

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
	public List<WearableInfoToDemand> findAll(int... rowStartIdxAndCount);
}