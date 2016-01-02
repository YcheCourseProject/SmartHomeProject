package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.HumidityData;

/**
 * Interface for HumidityDataDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IHumidityDataDAO {
	/**
	 * Perform an initial save of a previously unsaved HumidityData entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHumidityDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            HumidityData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(HumidityData entity);

	/**
	 * Delete a persistent HumidityData entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHumidityDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            HumidityData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(HumidityData entity);

	/**
	 * Persist a previously saved HumidityData entity and return it or a copy of
	 * it to the sender. A copy of the HumidityData entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IHumidityDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            HumidityData entity to update
	 * @return HumidityData the persisted HumidityData entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public HumidityData update(HumidityData entity);

	public HumidityData findById(Integer id);

	/**
	 * Find all HumidityData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the HumidityData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<HumidityData> found by query
	 */
	public List<HumidityData> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<HumidityData> findByHumidityData(Object humidityData,
			int... rowStartIdxAndCount);

	/**
	 * Find all HumidityData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<HumidityData> all HumidityData entities
	 */
	public List<HumidityData> findAll(int... rowStartIdxAndCount);
}