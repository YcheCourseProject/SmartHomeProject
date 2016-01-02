package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IOutdoorWeatherDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.OutdoorWeather;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * OutdoorWeather entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.OutdoorWeather
 * @author MyEclipse Persistence Tools
 */
@Repository
public class OutdoorWeatherDAO implements IOutdoorWeatherDAO {
	// property constants
	public static final String OUTDOOR_WEATHER_TEM = "outdoorWeatherTem";
	public static final String OUTDOOR_WEATHER_HUM = "outdoorWeatherHum";
	public static final String OUTDOOR_WEATHER_PRES = "outdoorWeatherPres";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved OutdoorWeather entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OutdoorWeatherDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OutdoorWeather entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(OutdoorWeather entity) {
		EntityManagerHelper.log("saving OutdoorWeather instance", Level.INFO,
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
	 * Delete a persistent OutdoorWeather entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OutdoorWeatherDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            OutdoorWeather entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(OutdoorWeather entity) {
		EntityManagerHelper.log("deleting OutdoorWeather instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(OutdoorWeather.class,
					entity.getOutdoorWeatherId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved OutdoorWeather entity and return it or a copy
	 * of it to the sender. A copy of the OutdoorWeather entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = OutdoorWeatherDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OutdoorWeather entity to update
	 * @return OutdoorWeather the persisted OutdoorWeather entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public OutdoorWeather update(OutdoorWeather entity) {
		EntityManagerHelper.log("updating OutdoorWeather instance", Level.INFO,
				null);
		try {
			OutdoorWeather result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public OutdoorWeather findById(Integer id) {
		EntityManagerHelper.log("finding OutdoorWeather instance with id: "
				+ id, Level.INFO, null);
		try {
			OutdoorWeather instance = getEntityManager().find(
					OutdoorWeather.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all OutdoorWeather entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the OutdoorWeather property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<OutdoorWeather> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<OutdoorWeather> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding OutdoorWeather instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from OutdoorWeather model where model."
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

	public List<OutdoorWeather> findByOutdoorWeatherTem(
			Object outdoorWeatherTem, int... rowStartIdxAndCount) {
		return findByProperty(OUTDOOR_WEATHER_TEM, outdoorWeatherTem,
				rowStartIdxAndCount);
	}

	public List<OutdoorWeather> findByOutdoorWeatherHum(
			Object outdoorWeatherHum, int... rowStartIdxAndCount) {
		return findByProperty(OUTDOOR_WEATHER_HUM, outdoorWeatherHum,
				rowStartIdxAndCount);
	}

	public List<OutdoorWeather> findByOutdoorWeatherPres(
			Object outdoorWeatherPres, int... rowStartIdxAndCount) {
		return findByProperty(OUTDOOR_WEATHER_PRES, outdoorWeatherPres,
				rowStartIdxAndCount);
	}

	/**
	 * Find all OutdoorWeather entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<OutdoorWeather> all OutdoorWeather entities
	 */
	@SuppressWarnings("unchecked")
	public List<OutdoorWeather> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all OutdoorWeather instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from OutdoorWeather model";
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