package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.SocialInfo;

/**
 * Interface for SocialInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISocialInfoDAO {
	/**
	 * Perform an initial save of a previously unsaved SocialInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISocialInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SocialInfo entity);

	/**
	 * Delete a persistent SocialInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISocialInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SocialInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SocialInfo entity);

	/**
	 * Persist a previously saved SocialInfo entity and return it or a copy of
	 * it to the sender. A copy of the SocialInfo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISocialInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialInfo entity to update
	 * @return SocialInfo the persisted SocialInfo entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SocialInfo update(SocialInfo entity);

	public SocialInfo findById(Integer id);

	/**
	 * Find all SocialInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SocialInfo property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SocialInfo> found by query
	 */
	public List<SocialInfo> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<SocialInfo> findByStartTime(Object startTime,
			int... rowStartIdxAndCount);

	public List<SocialInfo> findByEndTime(Object endTime,
			int... rowStartIdxAndCount);

	public List<SocialInfo> findByLocation(Object location,
			int... rowStartIdxAndCount);

	/**
	 * Find all SocialInfo entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SocialInfo> all SocialInfo entities
	 */
	public List<SocialInfo> findAll(int... rowStartIdxAndCount);
}