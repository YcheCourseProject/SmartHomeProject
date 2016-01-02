package com.xjtu.sglab.gateway.dao;

import java.util.List;

import com.xjtu.sglab.gateway.entity.UserAddress;

/**
 * Interface for UserAddressDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserAddressDAO {
	/**
	 * Perform an initial save of a previously unsaved UserAddress entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserAddressDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserAddress entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserAddress entity);

	/**
	 * Delete a persistent UserAddress entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserAddressDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserAddress entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserAddress entity);

	/**
	 * Persist a previously saved UserAddress entity and return it or a copy of
	 * it to the sender. A copy of the UserAddress entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserAddressDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserAddress entity to update
	 * @return UserAddress the persisted UserAddress entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserAddress update(UserAddress entity);

	public UserAddress findById(Integer id);

	/**
	 * Find all UserAddress entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserAddress property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserAddress> found by query
	 */
	public List<UserAddress> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<UserAddress> findByUserAddressLatitude(
			Object userAddressLatitude, int... rowStartIdxAndCount);

	public List<UserAddress> findByUserAddressLongitude(
			Object userAddressLongitude, int... rowStartIdxAndCount);

	public List<UserAddress> findByUserAddressDetail(Object userAddressDetail,
			int... rowStartIdxAndCount);

	/**
	 * Find all UserAddress entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserAddress> all UserAddress entities
	 */
	public List<UserAddress> findAll(int... rowStartIdxAndCount);
}