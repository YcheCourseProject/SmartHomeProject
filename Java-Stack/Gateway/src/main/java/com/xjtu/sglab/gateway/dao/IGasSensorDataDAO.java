package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.GasSensorData;

/**
 * Interface for GasSensorDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IGasSensorDataDAO {
	/**
	 * Perform an initial save of a previously unsaved GasSensorData entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGasSensorDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GasSensorData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(GasSensorData entity);

	/**
	 * Delete a persistent GasSensorData entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGasSensorDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            GasSensorData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(GasSensorData entity);

	/**
	 * Persist a previously saved GasSensorData entity and return it or a copy
	 * of it to the sender. A copy of the GasSensorData entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IGasSensorDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            GasSensorData entity to update
	 * @return GasSensorData the persisted GasSensorData entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public GasSensorData update(GasSensorData entity);

	public GasSensorData findById(Integer id);

	/**
	 * Find all GasSensorData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the GasSensorData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<GasSensorData> found by query
	 */
	public List<GasSensorData> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<GasSensorData> findByGasData(Object gasData,
			int... rowStartIdxAndCount);

	/**
	 * Find all GasSensorData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<GasSensorData> all GasSensorData entities
	 */
	public List<GasSensorData> findAll(int... rowStartIdxAndCount);
}