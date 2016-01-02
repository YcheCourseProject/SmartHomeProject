package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.WaterHeaterRealTimeDecision;

/**
 * Interface for WaterHeaterRealTimeDecisionDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWaterHeaterRealTimeDecisionDAO {
	/**
	 * Perform an initial save of a previously unsaved
	 * WaterHeaterRealTimeDecision entity. All subsequent persist actions of
	 * this entity should use the #update() method. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterRealTimeDecisionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterRealTimeDecision entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeaterRealTimeDecision entity);

	/**
	 * Delete a persistent WaterHeaterRealTimeDecision entity. This operation
	 * must be performed within the a database transaction context for the
	 * entity's data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterRealTimeDecisionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterRealTimeDecision entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeaterRealTimeDecision entity);

	/**
	 * Persist a previously saved WaterHeaterRealTimeDecision entity and return
	 * it or a copy of it to the sender. A copy of the
	 * WaterHeaterRealTimeDecision entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWaterHeaterRealTimeDecisionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterRealTimeDecision entity to update
	 * @return WaterHeaterRealTimeDecision the persisted
	 *         WaterHeaterRealTimeDecision entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeaterRealTimeDecision update(WaterHeaterRealTimeDecision entity);

	public WaterHeaterRealTimeDecision findById(Integer id);

	/**
	 * Find all WaterHeaterRealTimeDecision entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeaterRealTimeDecision property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterRealTimeDecision> found by query
	 */
	public List<WaterHeaterRealTimeDecision> findByProperty(
			String propertyName, Object value, int... rowStartIdxAndCount);

	public List<WaterHeaterRealTimeDecision> findByWaterHeaterConsumeAverageEnergy(
			Object waterHeaterConsumeAverageEnergy, int... rowStartIdxAndCount);

	/**
	 * Find all WaterHeaterRealTimeDecision entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterRealTimeDecision> all WaterHeaterRealTimeDecision
	 *         entities
	 */
	public List<WaterHeaterRealTimeDecision> findAll(int... rowStartIdxAndCount);
}