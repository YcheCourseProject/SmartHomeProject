package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.OutdoorWeather;

/**
 * Interface for OutdoorWeatherDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IOutdoorWeatherDAO {
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
	 * IOutdoorWeatherDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OutdoorWeather entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(OutdoorWeather entity);

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
	 * IOutdoorWeatherDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            OutdoorWeather entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(OutdoorWeather entity);

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
	 * entity = IOutdoorWeatherDAO.update(entity);
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
	public OutdoorWeather update(OutdoorWeather entity);

	public OutdoorWeather findById(Integer id);

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
	 *            count of results to return.
	 * @return List<OutdoorWeather> found by query
	 */
	public List<OutdoorWeather> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<OutdoorWeather> findByOutdoorWeatherTem(
			Object outdoorWeatherTem, int... rowStartIdxAndCount);

	public List<OutdoorWeather> findByOutdoorWeatherHum(
			Object outdoorWeatherHum, int... rowStartIdxAndCount);

	public List<OutdoorWeather> findByOutdoorWeatherPres(
			Object outdoorWeatherPres, int... rowStartIdxAndCount);

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
	public List<OutdoorWeather> findAll(int... rowStartIdxAndCount);
}