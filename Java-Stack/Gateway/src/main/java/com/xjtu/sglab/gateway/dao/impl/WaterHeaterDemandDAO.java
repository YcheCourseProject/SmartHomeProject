package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IWaterHeaterDemandDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.WaterHeaterDemand;

import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * WaterHeaterDemand entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.WaterHeaterDemand
 * @author MyEclipse Persistence Tools
 */
@Repository
public class WaterHeaterDemandDAO implements IWaterHeaterDemandDAO {
	// property constants
	public static final String WATER_HEATER_TEMPERATURE = "waterHeaterTemperature";
	public static final String WATER_HEATER_TEMPERATURE_DELTA = "waterHeaterTemperatureDelta";
	public static final String WATER_HEATER_DEMAND_INTERNAL = "waterHeaterDemandInternal";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved WaterHeaterDemand entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WaterHeaterDemandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterDemand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeaterDemand entity) {
		EntityManagerHelper.log("saving WaterHeaterDemand instance",
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
	 * Delete a persistent WaterHeaterDemand entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WaterHeaterDemandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterDemand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeaterDemand entity) {
		EntityManagerHelper.log("deleting WaterHeaterDemand instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(WaterHeaterDemand.class,
					entity.getWaterHeaterDemandId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved WaterHeaterDemand entity and return it or a
	 * copy of it to the sender. A copy of the WaterHeaterDemand entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WaterHeaterDemandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterDemand entity to update
	 * @return WaterHeaterDemand the persisted WaterHeaterDemand entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeaterDemand update(WaterHeaterDemand entity) {
		EntityManagerHelper.log("updating WaterHeaterDemand instance",
				Level.INFO, null);
		try {
			WaterHeaterDemand result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public WaterHeaterDemand findById(Integer id) {
		EntityManagerHelper.log("finding WaterHeaterDemand instance with id: "
				+ id, Level.INFO, null);
		try {
			WaterHeaterDemand instance = getEntityManager().find(
					WaterHeaterDemand.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all WaterHeaterDemand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeaterDemand property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<WaterHeaterDemand> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WaterHeaterDemand> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding WaterHeaterDemand instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from WaterHeaterDemand model where model."
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

	public List<WaterHeaterDemand> findByWaterHeaterTemperature(
			Object waterHeaterTemperature, int... rowStartIdxAndCount) {
		return findByProperty(WATER_HEATER_TEMPERATURE, waterHeaterTemperature,
				rowStartIdxAndCount);
	}

	public List<WaterHeaterDemand> findByWaterHeaterTemperatureDelta(
			Object waterHeaterTemperatureDelta, int... rowStartIdxAndCount) {
		return findByProperty(WATER_HEATER_TEMPERATURE_DELTA,
				waterHeaterTemperatureDelta, rowStartIdxAndCount);
	}

	public List<WaterHeaterDemand> findByWaterHeaterDemandInternal(
			Object waterHeaterDemandInternal, int... rowStartIdxAndCount) {
		return findByProperty(WATER_HEATER_DEMAND_INTERNAL,
				waterHeaterDemandInternal, rowStartIdxAndCount);
	}

	/**
	 * Find all WaterHeaterDemand entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterDemand> all WaterHeaterDemand entities
	 */
	@SuppressWarnings("unchecked")
	public List<WaterHeaterDemand> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all WaterHeaterDemand instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from WaterHeaterDemand model";
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