package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IElectricityInfoDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.ElectricityInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElectricityInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.ElectricityInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ElectricityInfoDAO implements IElectricityInfoDAO {
	// property constants
	public static final String ACTIVE_POWER = "activePower";
	public static final String REACTIVE_POWER = "reactivePower";
	public static final String TOTAL_CONSUME_ENERGY = "totalConsumeEnergy";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ElectricityInfo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ElectricityInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ElectricityInfo entity) {
		EntityManagerHelper.log("saving ElectricityInfo instance", Level.INFO,
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
	 * Delete a persistent ElectricityInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ElectricityInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ElectricityInfo entity) {
		EntityManagerHelper.log("deleting ElectricityInfo instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ElectricityInfo.class,
					entity.getElectricityInfoId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ElectricityInfo entity and return it or a copy
	 * of it to the sender. A copy of the ElectricityInfo entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ElectricityInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityInfo entity to update
	 * @return ElectricityInfo the persisted ElectricityInfo entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ElectricityInfo update(ElectricityInfo entity) {
		EntityManagerHelper.log("updating ElectricityInfo instance",
				Level.INFO, null);
		try {
			ElectricityInfo result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ElectricityInfo findById(Integer id) {
		EntityManagerHelper.log("finding ElectricityInfo instance with id: "
				+ id, Level.INFO, null);
		try {
			ElectricityInfo instance = getEntityManager().find(
					ElectricityInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ElectricityInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElectricityInfo property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<ElectricityInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ElectricityInfo> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding ElectricityInfo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ElectricityInfo model where model."
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

	public List<ElectricityInfo> findByActivePower(Object activePower,
			int... rowStartIdxAndCount) {
		return findByProperty(ACTIVE_POWER, activePower, rowStartIdxAndCount);
	}

	public List<ElectricityInfo> findByReactivePower(Object reactivePower,
			int... rowStartIdxAndCount) {
		return findByProperty(REACTIVE_POWER, reactivePower,
				rowStartIdxAndCount);
	}

	public List<ElectricityInfo> findByTotalConsumeEnergy(
			Object totalConsumeEnergy, int... rowStartIdxAndCount) {
		return findByProperty(TOTAL_CONSUME_ENERGY, totalConsumeEnergy,
				rowStartIdxAndCount);
	}

	/**
	 * Find all ElectricityInfo entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityInfo> all ElectricityInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<ElectricityInfo> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all ElectricityInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from ElectricityInfo model";
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