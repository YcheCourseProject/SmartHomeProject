package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.LightSensorData;

/**
 * Interface for LightSensorDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILightSensorDataDAO {
	/**
	 * Perform an initial save of a previously unsaved LightSensorData entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILightSensorDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LightSensorData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LightSensorData entity);

	/**
	 * Delete a persistent LightSensorData entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILightSensorDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LightSensorData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LightSensorData entity);

	/**
	 * Persist a previously saved LightSensorData entity and return it or a copy
	 * of it to the sender. A copy of the LightSensorData entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILightSensorDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LightSensorData entity to update
	 * @return LightSensorData the persisted LightSensorData entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LightSensorData update(LightSensorData entity);

	public LightSensorData findById(Integer id);

	/**
	 * Find all LightSensorData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LightSensorData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LightSensorData> found by query
	 */
	public List<LightSensorData> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<LightSensorData> findByLightData(Object lightData,
			int... rowStartIdxAndCount);

	/**
	 * Find all LightSensorData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LightSensorData> all LightSensorData entities
	 */
	public List<LightSensorData> findAll(int... rowStartIdxAndCount);
}