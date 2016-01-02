package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IFlameSensorDataDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.FlameSensorData;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * FlameSensorData entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.FlameSensorData
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FlameSensorDataDAO implements IFlameSensorDataDAO {
	// property constants
	public static final String FLAME_DATA = "flameData";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved FlameSensorData entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FlameSensorDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensorData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FlameSensorData entity) {
		EntityManagerHelper.log("saving FlameSensorData instance", Level.INFO,
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
	 * Delete a persistent FlameSensorData entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FlameSensorDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensorData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FlameSensorData entity) {
		EntityManagerHelper.log("deleting FlameSensorData instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(FlameSensorData.class,
					entity.getFlameDataId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved FlameSensorData entity and return it or a copy
	 * of it to the sender. A copy of the FlameSensorData entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FlameSensorDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensorData entity to update
	 * @return FlameSensorData the persisted FlameSensorData entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FlameSensorData update(FlameSensorData entity) {
		EntityManagerHelper.log("updating FlameSensorData instance",
				Level.INFO, null);
		try {
			FlameSensorData result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public FlameSensorData findById(Integer id) {
		EntityManagerHelper.log("finding FlameSensorData instance with id: "
				+ id, Level.INFO, null);
		try {
			FlameSensorData instance = getEntityManager().find(
					FlameSensorData.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all FlameSensorData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FlameSensorData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<FlameSensorData> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FlameSensorData> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding FlameSensorData instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from FlameSensorData model where model."
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

	public List<FlameSensorData> findByFlameData(Object flameData,
			int... rowStartIdxAndCount) {
		return findByProperty(FLAME_DATA, flameData, rowStartIdxAndCount);
	}

	/**
	 * Find all FlameSensorData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FlameSensorData> all FlameSensorData entities
	 */
	@SuppressWarnings("unchecked")
	public List<FlameSensorData> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all FlameSensorData instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from FlameSensorData model";
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