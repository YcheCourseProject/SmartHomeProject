package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.SheSwitch;

/**
 * Interface for SheSwitchDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISheSwitchDAO {
	/**
	 * Perform an initial save of a previously unsaved SheSwitch entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISheSwitchDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SheSwitch entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SheSwitch entity);

	/**
	 * Delete a persistent SheSwitch entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISheSwitchDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SheSwitch entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SheSwitch entity);

	/**
	 * Persist a previously saved SheSwitch entity and return it or a copy of it
	 * to the sender. A copy of the SheSwitch entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISheSwitchDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SheSwitch entity to update
	 * @return SheSwitch the persisted SheSwitch entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SheSwitch update(SheSwitch entity);

	public SheSwitch findById(Integer id);

	/**
	 * Find all SheSwitch entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SheSwitch property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SheSwitch> found by query
	 */
	public List<SheSwitch> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<SheSwitch> findByIsAlreadyControlled(
			Object isAlreadyControlled, int... rowStartIdxAndCount);

	/**
	 * Find all SheSwitch entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SheSwitch> all SheSwitch entities
	 */
	public List<SheSwitch> findAll(int... rowStartIdxAndCount);
}