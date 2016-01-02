package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.ICurtainStatusDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.CurtainStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * CurtainStatus entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.CurtainStatus
 * @author MyEclipse Persistence Tools
 */
@Repository
public class CurtainStatusDAO implements ICurtainStatusDAO {
	// property constants
	public static final String CURTAIN_STATUS = "curtainStatus";
	public static final String IS_CONTROLLED_BY_USER = "isControlledByUser";
	public static final String IS_ALREADY_CONTROLLED = "isAlreadyControlled";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CurtainStatus entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CurtainStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CurtainStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CurtainStatus entity) {
		EntityManagerHelper.log("saving CurtainStatus instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CurtainStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CurtainStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CurtainStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CurtainStatus entity) {
		EntityManagerHelper.log("deleting CurtainStatus instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(CurtainStatus.class,
					entity.getCurtainStatusId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CurtainStatus entity and return it or a copy
	 * of it to the sender. A copy of the CurtainStatus entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CurtainStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CurtainStatus entity to update
	 * @return CurtainStatus the persisted CurtainStatus entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CurtainStatus update(CurtainStatus entity) {
		EntityManagerHelper.log("updating CurtainStatus instance", Level.INFO,
				null);
		try {
			CurtainStatus result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CurtainStatus findById(Integer id) {
		EntityManagerHelper.log(
				"finding CurtainStatus instance with id: " + id, Level.INFO,
				null);
		try {
			CurtainStatus instance = getEntityManager().find(
					CurtainStatus.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CurtainStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CurtainStatus property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<CurtainStatus> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CurtainStatus> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding CurtainStatus instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CurtainStatus model where model."
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

	public List<CurtainStatus> findByCurtainStatus(Object curtainStatus,
			int... rowStartIdxAndCount) {
		return findByProperty(CURTAIN_STATUS, curtainStatus,
				rowStartIdxAndCount);
	}

	public List<CurtainStatus> findByIsControlledByUser(
			Object isControlledByUser, int... rowStartIdxAndCount) {
		return findByProperty(IS_CONTROLLED_BY_USER, isControlledByUser,
				rowStartIdxAndCount);
	}

	public List<CurtainStatus> findByIsAlreadyControlled(
			Object isAlreadyControlled, int... rowStartIdxAndCount) {
		return findByProperty(IS_ALREADY_CONTROLLED, isAlreadyControlled,
				rowStartIdxAndCount);
	}

	/**
	 * Find all CurtainStatus entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CurtainStatus> all CurtainStatus entities
	 */
	@SuppressWarnings("unchecked")
	public List<CurtainStatus> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all CurtainStatus instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from CurtainStatus model";
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