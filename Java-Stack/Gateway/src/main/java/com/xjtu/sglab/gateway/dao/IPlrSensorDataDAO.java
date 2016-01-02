package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.PlrSensorData;

/**
 * Interface for PlrSensorDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPlrSensorDataDAO {
	/**
	 * Perform an initial save of a previously unsaved PlrSensorData entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPlrSensorDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PlrSensorData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PlrSensorData entity);

	/**
	 * Delete a persistent PlrSensorData entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPlrSensorDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PlrSensorData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PlrSensorData entity);

	/**
	 * Persist a previously saved PlrSensorData entity and return it or a copy
	 * of it to the sender. A copy of the PlrSensorData entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPlrSensorDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PlrSensorData entity to update
	 * @return PlrSensorData the persisted PlrSensorData entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PlrSensorData update(PlrSensorData entity);

	public PlrSensorData findById(Integer id);

	/**
	 * Find all PlrSensorData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PlrSensorData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PlrSensorData> found by query
	 */
	public List<PlrSensorData> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<PlrSensorData> findByPlrData(Object plrData,
			int... rowStartIdxAndCount);

	/**
	 * Find all PlrSensorData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PlrSensorData> all PlrSensorData entities
	 */
	public List<PlrSensorData> findAll(int... rowStartIdxAndCount);
}