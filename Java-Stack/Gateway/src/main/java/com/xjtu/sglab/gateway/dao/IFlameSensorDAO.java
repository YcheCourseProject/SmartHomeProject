package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.FlameSensor;

/**
 * Interface for FlameSensorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFlameSensorDAO {
	/**
	 * Perform an initial save of a previously unsaved FlameSensor entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFlameSensorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FlameSensor entity);

	/**
	 * Delete a persistent FlameSensor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFlameSensorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FlameSensor entity);

	/**
	 * Persist a previously saved FlameSensor entity and return it or a copy of
	 * it to the sender. A copy of the FlameSensor entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFlameSensorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensor entity to update
	 * @return FlameSensor the persisted FlameSensor entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FlameSensor update(FlameSensor entity);

	public FlameSensor findById(Integer id);

	/**
	 * Find all FlameSensor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FlameSensor property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FlameSensor> found by query
	 */
	public List<FlameSensor> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * Find all FlameSensor entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FlameSensor> all FlameSensor entities
	 */
	public List<FlameSensor> findAll(int... rowStartIdxAndCount);
}