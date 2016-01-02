package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.AirConditionStatus;

/**
 * Interface for AirConditionStatusDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAirConditionStatusDAO {
	/**
	 * Perform an initial save of a previously unsaved AirConditionStatus
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirConditionStatus entity);

	/**
	 * Delete a persistent AirConditionStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirConditionStatus entity);

	/**
	 * Persist a previously saved AirConditionStatus entity and return it or a
	 * copy of it to the sender. A copy of the AirConditionStatus entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAirConditionStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionStatus entity to update
	 * @return AirConditionStatus the persisted AirConditionStatus entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirConditionStatus update(AirConditionStatus entity);

	public AirConditionStatus findById(Integer id);

	/**
	 * Find all AirConditionStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AirConditionStatus property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionStatus> found by query
	 */
	public List<AirConditionStatus> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<AirConditionStatus> findByAirConditionTemperature(
			Object airConditionTemperature, int... rowStartIdxAndCount);

	public List<AirConditionStatus> findByAirConditionMode(
			Object airConditionMode, int... rowStartIdxAndCount);

	public List<AirConditionStatus> findByIsControlledByUser(
			Object isControlledByUser, int... rowStartIdxAndCount);

	public List<AirConditionStatus> findByIsAlreadyControlled(
			Object isAlreadyControlled, int... rowStartIdxAndCount);

	/**
	 * Find all AirConditionStatus entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionStatus> all AirConditionStatus entities
	 */
	public List<AirConditionStatus> findAll(int... rowStartIdxAndCount);
}