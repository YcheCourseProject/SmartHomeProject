package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.ISocialActivityToDemandDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.SocialActivityToDemand;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * SocialActivityToDemand entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.SocialActivityToDemand
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SocialActivityToDemandDAO implements ISocialActivityToDemandDAO {
	// property constants
	public static final String SOCIAL_ACTIVITY_BEGIN_TIME = "socialActivityBeginTime";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * SocialActivityToDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SocialActivityToDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SocialActivityToDemand entity) {
		EntityManagerHelper.log("saving SocialActivityToDemand instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * SocialActivityToDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SocialActivityToDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SocialActivityToDemand entity) {
		EntityManagerHelper.log("deleting SocialActivityToDemand instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					SocialActivityToDemand.class,
					entity.getSocialActivityToDemandId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = SocialActivityToDemandDAO.update(entity);
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
	public SocialActivityToDemand update(SocialActivityToDemand entity) {
		EntityManagerHelper.log("updating SocialActivityToDemand instance",
				Level.INFO, null);
		try {
			SocialActivityToDemand result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SocialActivityToDemand findById(Integer id) {
		EntityManagerHelper.log(
				"finding SocialActivityToDemand instance with id: " + id,
				Level.INFO, null);
		try {
			SocialActivityToDemand instance = getEntityManager().find(
					SocialActivityToDemand.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<SocialActivityToDemand> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SocialActivityToDemand> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding SocialActivityToDemand instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from SocialActivityToDemand model where model."
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

	public List<SocialActivityToDemand> findBySocialActivityBeginTime(
			Object socialActivityBeginTime, int... rowStartIdxAndCount) {
		return findByProperty(SOCIAL_ACTIVITY_BEGIN_TIME,
				socialActivityBeginTime, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<SocialActivityToDemand> findAll(
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all SocialActivityToDemand instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from SocialActivityToDemand model";
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