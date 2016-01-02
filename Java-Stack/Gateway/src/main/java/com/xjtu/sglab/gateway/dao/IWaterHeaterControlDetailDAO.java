package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.WaterHeaterControlDetail;

/**
 * Interface for WaterHeaterControlDetailDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWaterHeaterControlDetailDAO {
	/**
	 * Perform an initial save of a previously unsaved WaterHeaterControlDetail
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterControlDetailDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterControlDetail entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WaterHeaterControlDetail entity);

	/**
	 * Delete a persistent WaterHeaterControlDetail entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWaterHeaterControlDetailDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterControlDetail entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WaterHeaterControlDetail entity);

	/**
	 * Persist a previously saved WaterHeaterControlDetail entity and return it
	 * or a copy of it to the sender. A copy of the WaterHeaterControlDetail
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWaterHeaterControlDetailDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WaterHeaterControlDetail entity to update
	 * @return WaterHeaterControlDetail the persisted WaterHeaterControlDetail
	 *         entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WaterHeaterControlDetail update(WaterHeaterControlDetail entity);

	public WaterHeaterControlDetail findById(Integer id);

	/**
	 * Find all WaterHeaterControlDetail entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the WaterHeaterControlDetail property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterControlDetail> found by query
	 */
	public List<WaterHeaterControlDetail> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<WaterHeaterControlDetail> findByWaterTemperature(
			Object waterTemperature, int... rowStartIdxAndCount);

	/**
	 * Find all WaterHeaterControlDetail entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WaterHeaterControlDetail> all WaterHeaterControlDetail
	 *         entities
	 */
	public List<WaterHeaterControlDetail> findAll(int... rowStartIdxAndCount);
}