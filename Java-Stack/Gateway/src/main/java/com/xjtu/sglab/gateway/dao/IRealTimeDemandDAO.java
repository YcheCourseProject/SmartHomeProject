package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.RealTimeDemand;

/**
 * Interface for RealTimeDemandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRealTimeDemandDAO {
	/**
	 * Perform an initial save of a previously unsaved RealTimeDemand entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRealTimeDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RealTimeDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RealTimeDemand entity);

	/**
	 * Delete a persistent RealTimeDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRealTimeDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RealTimeDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RealTimeDemand entity);

	/**
	 * Persist a previously saved RealTimeDemand entity and return it or a copy
	 * of it to the sender. A copy of the RealTimeDemand entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRealTimeDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RealTimeDemand entity to update
	 * @return RealTimeDemand the persisted RealTimeDemand entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RealTimeDemand update(RealTimeDemand entity);

	public RealTimeDemand findById(Integer id);

	/**
	 * Find all RealTimeDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RealTimeDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<RealTimeDemand> found by query
	 */
	public List<RealTimeDemand> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all RealTimeDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<RealTimeDemand> all RealTimeDemand entities
	 */
	public List<RealTimeDemand> findAll(int... rowStartIdxAndCount);
}