package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.CircuitLine;

/**
 * Interface for CircuitLineDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICircuitLineDAO {
	/**
	 * Perform an initial save of a previously unsaved CircuitLine entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICircuitLineDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CircuitLine entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CircuitLine entity);

	/**
	 * Delete a persistent CircuitLine entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICircuitLineDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CircuitLine entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CircuitLine entity);

	/**
	 * Persist a previously saved CircuitLine entity and return it or a copy of
	 * it to the sender. A copy of the CircuitLine entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICircuitLineDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CircuitLine entity to update
	 * @return CircuitLine the persisted CircuitLine entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CircuitLine update(CircuitLine entity);

	public CircuitLine findById(Integer id);

	/**
	 * Find all CircuitLine entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CircuitLine property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CircuitLine> found by query
	 */
	public List<CircuitLine> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<CircuitLine> findByCircuitLineDescription(
			Object circuitLineDescription, int... rowStartIdxAndCount);

	/**
	 * Find all CircuitLine entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CircuitLine> all CircuitLine entities
	 */
	public List<CircuitLine> findAll(int... rowStartIdxAndCount);
}