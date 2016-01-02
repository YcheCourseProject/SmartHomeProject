package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.WearableDeviceInfo;

/**
 * Interface for WearableDeviceInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWearableDeviceInfoDAO {
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
	 * IWearableDeviceInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WearableDeviceInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WearableDeviceInfo entity);

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
	 * IWearableDeviceInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WearableDeviceInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WearableDeviceInfo entity);

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
	 * entity = IWearableDeviceInfoDAO.update(entity);
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
	public WearableDeviceInfo update(WearableDeviceInfo entity);

	public WearableDeviceInfo findById(Integer id);

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
	 *            count of results to return.
	 * @return List<WearableDeviceInfo> found by query
	 */
	public List<WearableDeviceInfo> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByAcceleratedSpeedX(
			Object acceleratedSpeedX, int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByAcceleratedSpeedY(
			Object acceleratedSpeedY, int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByAcceleratedSpeedZ(
			Object acceleratedSpeedZ, int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByGyroscopeX(Object gyroscopeX,
			int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByGyroscopeY(Object gyroscopeY,
			int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByGyroscopeZ(Object gyroscopeZ,
			int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByBodyTemperature(
			Object bodyTemperature, int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findByHeartRate(Object heartRate,
			int... rowStartIdxAndCount);

	public List<WearableDeviceInfo> findBySpeed(Object speed,
			int... rowStartIdxAndCount);

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
	public List<WearableDeviceInfo> findAll(int... rowStartIdxAndCount);
}