package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.RealTimeDecision;

/**
 * Interface for RealTimeDecisionDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRealTimeDecisionDAO {
	/**
	 * Perform an initial save of a previously unsaved RealTimeDecision entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRealTimeDecisionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RealTimeDecision entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RealTimeDecision entity);

	/**
	 * Delete a persistent RealTimeDecision entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRealTimeDecisionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RealTimeDecision entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RealTimeDecision entity);

	/**
	 * Persist a previously saved RealTimeDecision entity and return it or a
	 * copy of it to the sender. A copy of the RealTimeDecision entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRealTimeDecisionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RealTimeDecision entity to update
	 * @return RealTimeDecision the persisted RealTimeDecision entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RealTimeDecision update(RealTimeDecision entity);

	public RealTimeDecision findById(Integer id);

	/**
	 * Find all RealTimeDecision entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RealTimeDecision property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<RealTimeDecision> found by query
	 */
	public List<RealTimeDecision> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all RealTimeDecision entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<RealTimeDecision> all RealTimeDecision entities
	 */
	public List<RealTimeDecision> findAll(int... rowStartIdxAndCount);
}