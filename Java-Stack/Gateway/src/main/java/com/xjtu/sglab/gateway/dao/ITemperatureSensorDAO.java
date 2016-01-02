package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.TemperatureSensor;

/**
 * Interface for TemperatureSensorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITemperatureSensorDAO {
	/**
	 * Perform an initial save of a previously unsaved TemperatureSensor entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITemperatureSensorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TemperatureSensor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TemperatureSensor entity);

	/**
	 * Delete a persistent TemperatureSensor entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITemperatureSensorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TemperatureSensor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TemperatureSensor entity);

	/**
	 * Persist a previously saved TemperatureSensor entity and return it or a
	 * copy of it to the sender. A copy of the TemperatureSensor entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITemperatureSensorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TemperatureSensor entity to update
	 * @return TemperatureSensor the persisted TemperatureSensor entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TemperatureSensor update(TemperatureSensor entity);

	public TemperatureSensor findById(Integer id);

	/**
	 * Find all TemperatureSensor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TemperatureSensor property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TemperatureSensor> found by query
	 */
	public List<TemperatureSensor> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all TemperatureSensor entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TemperatureSensor> all TemperatureSensor entities
	 */
	public List<TemperatureSensor> findAll(int... rowStartIdxAndCount);
}