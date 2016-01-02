package com.xjtu.sglab.gateway.dao;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.WaterHeaterDemand;

/**
 * Interface for WaterHeaterDemandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWaterHeaterDemandDAO {
	/**
	 * Perform an initial save of a previously unsaved WaterHeaterDemand entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeaterDemand entity);

	/**
	 * Delete a persistent WaterHeaterDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeaterDemand entity);

	/**
	 * Persist a previously saved WaterHeaterDemand entity and return it or a
	 * copy of it to the sender. A copy of the WaterHeaterDemand entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWaterHeaterDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterDemand entity to update
	 * @return WaterHeaterDemand the persisted WaterHeaterDemand entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeaterDemand update(WaterHeaterDemand entity);

	public WaterHeaterDemand findById(Integer id);

	/**
	 * Find all WaterHeaterDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeaterDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterDemand> found by query
	 */
	public List<WaterHeaterDemand> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<WaterHeaterDemand> findByWaterHeaterTemperature(
			Object waterHeaterTemperature, int... rowStartIdxAndCount);

	public List<WaterHeaterDemand> findByWaterHeaterTemperatureDelta(
			Object waterHeaterTemperatureDelta, int... rowStartIdxAndCount);

	public List<WaterHeaterDemand> findByWaterHeaterDemandInternal(
			Object waterHeaterDemandInternal, int... rowStartIdxAndCount);

	/**
	 * Find all WaterHeaterDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterDemand> all WaterHeaterDemand entities
	 */
	public List<WaterHeaterDemand> findAll(int... rowStartIdxAndCount);
}