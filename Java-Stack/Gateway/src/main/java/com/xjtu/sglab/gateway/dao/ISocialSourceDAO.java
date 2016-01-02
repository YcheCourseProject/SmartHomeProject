package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.SocialSource;

/**
 * Interface for SocialSourceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISocialSourceDAO {
	/**
	 * Perform an initial save of a previously unsaved SocialSource entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISocialSourceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialSource entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SocialSource entity);

	/**
	 * Delete a persistent SocialSource entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISocialSourceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SocialSource entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SocialSource entity);

	/**
	 * Persist a previously saved SocialSource entity and return it or a copy of
	 * it to the sender. A copy of the SocialSource entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISocialSourceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialSource entity to update
	 * @return SocialSource the persisted SocialSource entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SocialSource update(SocialSource entity);

	public SocialSource findById(Integer id);

	/**
	 * Find all SocialSource entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SocialSource property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SocialSource> found by query
	 */
	public List<SocialSource> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<SocialSource> findBySourceType(Object sourceType,
			int... rowStartIdxAndCount);

	/**
	 * Find all SocialSource entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<SocialSource> all SocialSource entities
	 */
	public List<SocialSource> findAll(int... rowStartIdxAndCount);
}