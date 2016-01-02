package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.AirConditionRealTimeDecision;

/**
 * Interface for AirConditionRealTimeDecisionDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAirConditionRealTimeDecisionDAO {
	/**
	 * Perform an initial save of a previously unsaved
	 * AirConditionRealTimeDecision entity. All subsequent persist actions of
	 * this entity should use the #update() method. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionRealTimeDecisionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionRealTimeDecision entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirConditionRealTimeDecision entity);

	/**
	 * Delete a persistent AirConditionRealTimeDecision entity. This operation
	 * must be performed within the a database transaction context for the
	 * entity's data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionRealTimeDecisionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionRealTimeDecision entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirConditionRealTimeDecision entity);

	/**
	 * Persist a previously saved AirConditionRealTimeDecision entity and return
	 * it or a copy of it to the sender. A copy of the
	 * AirConditionRealTimeDecision entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAirConditionRealTimeDecisionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionRealTimeDecision entity to update
	 * @return AirConditionRealTimeDecision the persisted
	 *         AirConditionRealTimeDecision entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirConditionRealTimeDecision update(
			AirConditionRealTimeDecision entity);

	public AirConditionRealTimeDecision findById(Integer id);

	/**
	 * Find all AirConditionRealTimeDecision entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the AirConditionRealTimeDecision property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionRealTimeDecision> found by query
	 */
	public List<AirConditionRealTimeDecision> findByProperty(
			String propertyName, Object value, int... rowStartIdxAndCount);

	public List<AirConditionRealTimeDecision> findByAirConditionDecideAverageEnergy(
			Object airConditionDecideAverageEnergy, int... rowStartIdxAndCount);

	/**
	 * Find all AirConditionRealTimeDecision entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionRealTimeDecision> all
	 *         AirConditionRealTimeDecision entities
	 */
	public List<AirConditionRealTimeDecision> findAll(
			int... rowStartIdxAndCount);
}