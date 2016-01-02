package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.SheSwitchStatus;

/**
 * Interface for SheSwitchStatusDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISheSwitchStatusDAO {
	/**
	 * Perform an initial save of a previously unsaved SheSwitchStatus entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISheSwitchStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SheSwitchStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SheSwitchStatus entity);

	/**
	 * Delete a persistent SheSwitchStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISheSwitchStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SheSwitchStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SheSwitchStatus entity);

	/**
	 * Persist a previously saved SheSwitchStatus entity and return it or a copy
	 * of it to the sender. A copy of the SheSwitchStatus entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISheSwitchStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SheSwitchStatus entity to update
	 * @return SheSwitchStatus the persisted SheSwitchStatus entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SheSwitchStatus update(SheSwitchStatus entity);

	public SheSwitchStatus findById(Integer id);

	/**
	 * Find all SheSwitchStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SheSwitchStatus property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SheSwitchStatus> found by query
	 */
	public List<SheSwitchStatus> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<SheSwitchStatus> findBySheSwitchStatus(Object sheSwitchStatus,
			int... rowStartIdxAndCount);

	public List<SheSwitchStatus> findByIsControlledByUser(
			Object isControlledByUser, int... rowStartIdxAndCount);

	public List<SheSwitchStatus> findByIsAlreadyControlled(
			Object isAlreadyControlled, int... rowStartIdxAndCount);

	/**
	 * Find all SheSwitchStatus entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SheSwitchStatus> all SheSwitchStatus entities
	 */
	public List<SheSwitchStatus> findAll(int... rowStartIdxAndCount);
}