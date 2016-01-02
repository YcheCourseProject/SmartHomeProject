package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IUserAddressDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.UserAddress;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserAddress entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.UserAddress
 * @author MyEclipse Persistence Tools
 */
@Repository
public class UserAddressDAO implements IUserAddressDAO {
	// property constants
	public static final String USER_ADDRESS_LATITUDE = "userAddressLatitude";
	public static final String USER_ADDRESS_LONGITUDE = "userAddressLongitude";
	public static final String USER_ADDRESS_DETAIL = "userAddressDetail";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * UserAddressDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserAddress entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserAddress entity) {
		EntityManagerHelper
				.log("saving UserAddress instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent UserAddress entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserAddressDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserAddress entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserAddress entity) {
		EntityManagerHelper.log("deleting UserAddress instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(UserAddress.class,
					entity.getUserAddressId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = UserAddressDAO.update(entity);
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
	public UserAddress update(UserAddress entity) {
		EntityManagerHelper.log("updating UserAddress instance", Level.INFO,
				null);
		try {
			UserAddress result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UserAddress findById(Integer id) {
		EntityManagerHelper.log("finding UserAddress instance with id: " + id,
				Level.INFO, null);
		try {
			UserAddress instance = getEntityManager().find(UserAddress.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<UserAddress> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UserAddress> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding UserAddress instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UserAddress model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<UserAddress> findByUserAddressLatitude(
			Object userAddressLatitude, int... rowStartIdxAndCount) {
		return findByProperty(USER_ADDRESS_LATITUDE, userAddressLatitude,
				rowStartIdxAndCount);
	}

	public List<UserAddress> findByUserAddressLongitude(
			Object userAddressLongitude, int... rowStartIdxAndCount) {
		return findByProperty(USER_ADDRESS_LONGITUDE, userAddressLongitude,
				rowStartIdxAndCount);
	}

	public List<UserAddress> findByUserAddressDetail(Object userAddressDetail,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_ADDRESS_DETAIL, userAddressDetail,
				rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<UserAddress> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all UserAddress instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from UserAddress model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}