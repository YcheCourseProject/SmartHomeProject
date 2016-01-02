package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.TemperatureSensorData;

/**
 * Interface for TemperatureSensorDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITemperatureSensorDataDAO {
	/**
	 * Perform an initial save of a previously unsaved TemperatureSensorData
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITemperatureSensorDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TemperatureSensorData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TemperatureSensorData entity);

	/**
	 * Delete a persistent TemperatureSensorData entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITemperatureSensorDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TemperatureSensorData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TemperatureSensorData entity);

	/**
	 * Persist a previously saved TemperatureSensorData entity and return it or
	 * a copy of it to the sender. A copy of the TemperatureSensorData entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITemperatureSensorDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TemperatureSensorData entity to update
	 * @return TemperatureSensorData the persisted TemperatureSensorData entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TemperatureSensorData update(TemperatureSensorData entity);

	public TemperatureSensorData findById(Integer id);

	/**
	 * Find all TemperatureSensorData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TemperatureSensorData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TemperatureSensorData> found by query
	 */
	public List<TemperatureSensorData> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<TemperatureSensorData> findByTemperatureData(
			Object temperatureData, int... rowStartIdxAndCount);

	/**
	 * Find all TemperatureSensorData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TemperatureSensorData> all TemperatureSensorData entities
	 */
	public List<TemperatureSensorData> findAll(int... rowStartIdxAndCount);
}