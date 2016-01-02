package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.ActivityType;

/**
 * Interface for ActivityTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IActivityTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved ActivityType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActivityTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActivityType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ActivityType entity);

	/**
	 * Delete a persistent ActivityType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActivityTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ActivityType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ActivityType entity);

	/**
	 * Persist a previously saved ActivityType entity and return it or a copy of
	 * it to the sender. A copy of the ActivityType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IActivityTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActivityType entity to update
	 * @return ActivityType the persisted ActivityType entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ActivityType update(ActivityType entity);

	public ActivityType findById(Integer id);

	/**
	 * Find all ActivityType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ActivityType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ActivityType> found by query
	 */
	public List<ActivityType> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<ActivityType> findByActivityType(Object activityType,
			int... rowStartIdxAndCount);

	/**
	 * Find all ActivityType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ActivityType> all ActivityType entities
	 */
	public List<ActivityType> findAll(int... rowStartIdxAndCount);
}