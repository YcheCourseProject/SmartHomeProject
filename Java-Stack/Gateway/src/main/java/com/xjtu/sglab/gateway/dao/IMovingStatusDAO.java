package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.MovingStatus;

/**
 * Interface for MovingStatusDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMovingStatusDAO {
	/**
	 * Perform an initial save of a previously unsaved MovingStatus entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMovingStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MovingStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MovingStatus entity);

	/**
	 * Delete a persistent MovingStatus entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMovingStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MovingStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MovingStatus entity);

	/**
	 * Persist a previously saved MovingStatus entity and return it or a copy of
	 * it to the sender. A copy of the MovingStatus entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMovingStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MovingStatus entity to update
	 * @return MovingStatus the persisted MovingStatus entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MovingStatus update(MovingStatus entity);

	public MovingStatus findById(Integer id);

	/**
	 * Find all MovingStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MovingStatus property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<MovingStatus> found by query
	 */
	public List<MovingStatus> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<MovingStatus> findByMovingType(Object movingType,
			int... rowStartIdxAndCount);

	/**
	 * Find all MovingStatus entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<MovingStatus> all MovingStatus entities
	 */
	public List<MovingStatus> findAll(int... rowStartIdxAndCount);
}