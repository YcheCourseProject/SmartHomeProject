package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.PlrSensor;

/**
 * Interface for PlrSensorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPlrSensorDAO {
	/**
	 * Perform an initial save of a previously unsaved PlrSensor entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPlrSensorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PlrSensor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PlrSensor entity);

	/**
	 * Delete a persistent PlrSensor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPlrSensorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PlrSensor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PlrSensor entity);

	/**
	 * Persist a previously saved PlrSensor entity and return it or a copy of it
	 * to the sender. A copy of the PlrSensor entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPlrSensorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PlrSensor entity to update
	 * @return PlrSensor the persisted PlrSensor entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PlrSensor update(PlrSensor entity);

	public PlrSensor findById(Integer id);

	/**
	 * Find all PlrSensor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PlrSensor property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PlrSensor> found by query
	 */
	public List<PlrSensor> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * Find all PlrSensor entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PlrSensor> all PlrSensor entities
	 */
	public List<PlrSensor> findAll(int... rowStartIdxAndCount);
}