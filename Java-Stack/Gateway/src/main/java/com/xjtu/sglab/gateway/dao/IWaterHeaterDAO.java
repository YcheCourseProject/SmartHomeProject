package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.WaterHeater;

/**
 * Interface for WaterHeaterDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWaterHeaterDAO {
	/**
	 * Perform an initial save of a previously unsaved WaterHeater entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeater entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeater entity);

	/**
	 * Delete a persistent WaterHeater entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeater entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeater entity);

	/**
	 * Persist a previously saved WaterHeater entity and return it or a copy of
	 * it to the sender. A copy of the WaterHeater entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWaterHeaterDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeater entity to update
	 * @return WaterHeater the persisted WaterHeater entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeater update(WaterHeater entity);

	public WaterHeater findById(Integer id);

	/**
	 * Find all WaterHeater entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeater property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeater> found by query
	 */
	public List<WaterHeater> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<WaterHeater> findByWaterHeaterRatedPower(
			Object waterHeaterRatedPower, int... rowStartIdxAndCount);

	/**
	 * Find all WaterHeater entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeater> all WaterHeater entities
	 */
	public List<WaterHeater> findAll(int... rowStartIdxAndCount);
}