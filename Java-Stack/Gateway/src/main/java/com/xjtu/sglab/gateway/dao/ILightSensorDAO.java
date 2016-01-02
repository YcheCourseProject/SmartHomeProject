package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.LightSensor;

/**
 * Interface for LightSensorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILightSensorDAO {
	/**
	 * Perform an initial save of a previously unsaved LightSensor entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILightSensorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LightSensor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LightSensor entity);

	/**
	 * Delete a persistent LightSensor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILightSensorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LightSensor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LightSensor entity);

	/**
	 * Persist a previously saved LightSensor entity and return it or a copy of
	 * it to the sender. A copy of the LightSensor entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILightSensorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LightSensor entity to update
	 * @return LightSensor the persisted LightSensor entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LightSensor update(LightSensor entity);

	public LightSensor findById(Integer id);

	/**
	 * Find all LightSensor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LightSensor property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LightSensor> found by query
	 */
	public List<LightSensor> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * Find all LightSensor entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LightSensor> all LightSensor entities
	 */
	public List<LightSensor> findAll(int... rowStartIdxAndCount);
}