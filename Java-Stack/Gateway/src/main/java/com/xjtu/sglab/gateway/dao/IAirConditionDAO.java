package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.AirCondition;

/**
 * Interface for AirConditionDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAirConditionDAO {
	/**
	 * Perform an initial save of a previously unsaved AirCondition entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirCondition entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirCondition entity);

	/**
	 * Delete a persistent AirCondition entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirCondition entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirCondition entity);

	/**
	 * Persist a previously saved AirCondition entity and return it or a copy of
	 * it to the sender. A copy of the AirCondition entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAirConditionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirCondition entity to update
	 * @return AirCondition the persisted AirCondition entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirCondition update(AirCondition entity);

	public AirCondition findById(Integer id);

	/**
	 * Find all AirCondition entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AirCondition property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirCondition> found by query
	 */
	public List<AirCondition> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<AirCondition> findByAirConditionIp(Object airConditionIp,
			int... rowStartIdxAndCount);

	public List<AirCondition> findByAirConditionRatedPower(
			Object airConditionRatedPower, int... rowStartIdxAndCount);

	/**
	 * Find all AirCondition entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirCondition> all AirCondition entities
	 */
	public List<AirCondition> findAll(int... rowStartIdxAndCount);
}