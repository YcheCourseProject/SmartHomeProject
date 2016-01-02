package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.Box;

/**
 * Interface for BoxDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBoxDAO {
	/**
	 * Perform an initial save of a previously unsaved Box entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBoxDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Box entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Box entity);

	/**
	 * Delete a persistent Box entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBoxDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Box entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Box entity);

	/**
	 * Persist a previously saved Box entity and return it or a copy of it to
	 * the sender. A copy of the Box entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IBoxDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Box entity to update
	 * @return Box the persisted Box entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Box update(Box entity);

	public Box findById(Integer id);

	/**
	 * Find all Box entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Box property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Box> found by query
	 */
	public List<Box> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Box> findByDevelopmentBoardIp(Object developmentBoardIp,
			int... rowStartIdxAndCount);

	public List<Box> findByControlModelIp(Object controlModelIp,
			int... rowStartIdxAndCount);

	/**
	 * Find all Box entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Box> all Box entities
	 */
	public List<Box> findAll(int... rowStartIdxAndCount);
}