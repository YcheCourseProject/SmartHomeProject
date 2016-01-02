package com.xjtu.sglab.gateway.dao;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.AirConditionDemand;

/**
 * Interface for AirConditionDemandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAirConditionDemandDAO {
	/**
	 * Perform an initial save of a previously unsaved AirConditionDemand
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirConditionDemand entity);

	/**
	 * Delete a persistent AirConditionDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirConditionDemand entity);

	/**
	 * Persist a previously saved AirConditionDemand entity and return it or a
	 * copy of it to the sender. A copy of the AirConditionDemand entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAirConditionDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionDemand entity to update
	 * @return AirConditionDemand the persisted AirConditionDemand entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirConditionDemand update(AirConditionDemand entity);

	public AirConditionDemand findById(Integer id);

	/**
	 * Find all AirConditionDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AirConditionDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionDemand> found by query
	 */
	public List<AirConditionDemand> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<AirConditionDemand> findByWearableInfoToDemandId(
			Object wearableInfoToDemandId, int... rowStartIdxAndCount);

	public List<AirConditionDemand> findByAirConditionTemperature(
			Object airConditionTemperature, int... rowStartIdxAndCount);

	public List<AirConditionDemand> findByAirConditionTemperatureDelta(
			Object airConditionTemperatureDelta, int... rowStartIdxAndCount);

	public List<AirConditionDemand> findByAirConditionDemandInternal(
			Object airConditionDemandInternal, int... rowStartIdxAndCount);

	/**
	 * Find all AirConditionDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionDemand> all AirConditionDemand entities
	 */
	public List<AirConditionDemand> findAll(int... rowStartIdxAndCount);
}