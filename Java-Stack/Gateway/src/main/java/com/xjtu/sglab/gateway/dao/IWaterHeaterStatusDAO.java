package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.WaterHeaterStatus;

/**
 * Interface for WaterHeaterStatusDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWaterHeaterStatusDAO {
	/**
	 * Perform an initial save of a previously unsaved WaterHeaterStatus entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeaterStatus entity);

	/**
	 * Delete a persistent WaterHeaterStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeaterStatus entity);

	/**
	 * Persist a previously saved WaterHeaterStatus entity and return it or a
	 * copy of it to the sender. A copy of the WaterHeaterStatus entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWaterHeaterStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterStatus entity to update
	 * @return WaterHeaterStatus the persisted WaterHeaterStatus entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeaterStatus update(WaterHeaterStatus entity);

	public WaterHeaterStatus findById(Integer id);

	/**
	 * Find all WaterHeaterStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeaterStatus property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterStatus> found by query
	 */
	public List<WaterHeaterStatus> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<WaterHeaterStatus> findByWaterHeaterTemperature(
			Object waterHeaterTemperature, int... rowStartIdxAndCount);

	public List<WaterHeaterStatus> findByIsControlledByUser(
			Object isControlledByUser, int... rowStartIdxAndCount);

	public List<WaterHeaterStatus> findByIsAlreadyControlled(
			Object isAlreadyControlled, int... rowStartIdxAndCount);

	/**
	 * Find all WaterHeaterStatus entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterStatus> all WaterHeaterStatus entities
	 */
	public List<WaterHeaterStatus> findAll(int... rowStartIdxAndCount);
}