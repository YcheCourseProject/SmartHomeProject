package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.SocialActivityToDemand;

/**
 * Interface for SocialActivityToDemandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISocialActivityToDemandDAO {
	/**
	 * Perform an initial save of a previously unsaved SocialActivityToDemand
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISocialActivityToDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialActivityToDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SocialActivityToDemand entity);

	/**
	 * Delete a persistent SocialActivityToDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISocialActivityToDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SocialActivityToDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SocialActivityToDemand entity);

	/**
	 * Persist a previously saved SocialActivityToDemand entity and return it or
	 * a copy of it to the sender. A copy of the SocialActivityToDemand entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISocialActivityToDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialActivityToDemand entity to update
	 * @return SocialActivityToDemand the persisted SocialActivityToDemand
	 *         entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SocialActivityToDemand update(SocialActivityToDemand entity);

	public SocialActivityToDemand findById(Integer id);

	/**
	 * Find all SocialActivityToDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SocialActivityToDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SocialActivityToDemand> found by query
	 */
	public List<SocialActivityToDemand> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<SocialActivityToDemand> findBySocialActivityBeginTime(
			Object socialActivityBeginTime, int... rowStartIdxAndCount);

	/**
	 * Find all SocialActivityToDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SocialActivityToDemand> all SocialActivityToDemand entities
	 */
	public List<SocialActivityToDemand> findAll(int... rowStartIdxAndCount);
}