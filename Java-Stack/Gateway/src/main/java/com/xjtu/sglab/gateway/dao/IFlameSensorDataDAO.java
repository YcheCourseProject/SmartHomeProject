package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.FlameSensorData;

/**
 * Interface for FlameSensorDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFlameSensorDataDAO {
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
	 * IFlameSensorDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensorData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FlameSensorData entity);

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
	 * IFlameSensorDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FlameSensorData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FlameSensorData entity);

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
	 * entity = IFlameSensorDataDAO.update(entity);
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
	public FlameSensorData update(FlameSensorData entity);

	public FlameSensorData findById(Integer id);

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
	 *            count of results to return.
	 * @return List<FlameSensorData> found by query
	 */
	public List<FlameSensorData> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<FlameSensorData> findByFlameData(Object flameData,
			int... rowStartIdxAndCount);

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
	public List<FlameSensorData> findAll(int... rowStartIdxAndCount);
}