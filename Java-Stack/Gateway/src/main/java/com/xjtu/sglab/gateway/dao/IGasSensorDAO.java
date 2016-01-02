package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.GasSensor;

/**
 * Interface for GasSensorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IGasSensorDAO {
	/**
	 * Perform an initial save of a previously unsaved GasSensor entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGasSensorDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GasSensor entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GasSensor entity);

	/**
	 * Delete a persistent GasSensor entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGasSensorDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GasSensor entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GasSensor entity);

	/**
	 * Persist a previously saved GasSensor entity and return it or a copy of it
	 * to the sender. A copy of the GasSensor entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IGasSensorDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GasSensor entity to update
	 * @return GasSensor the persisted GasSensor entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public GasSensor update(GasSensor entity);

	public GasSensor findById(Integer id);

	/**
	 * Find all GasSensor entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the GasSensor property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<GasSensor> found by query
	 */
	public List<GasSensor> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * Find all GasSensor entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<GasSensor> all GasSensor entities
	 */
	public List<GasSensor> findAll(int... rowStartIdxAndCount);
}