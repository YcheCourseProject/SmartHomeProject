package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.AirConditionControlDetail;

/**
 * Interface for AirConditionControlDetailDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAirConditionControlDetailDAO {
	/**
	 * Perform an initial save of a previously unsaved AirConditionControlDetail
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionControlDetailDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionControlDetail entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AirConditionControlDetail entity);

	/**
	 * Delete a persistent AirConditionControlDetail entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAirConditionControlDetailDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionControlDetail entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AirConditionControlDetail entity);

	/**
	 * Persist a previously saved AirConditionControlDetail entity and return it
	 * or a copy of it to the sender. A copy of the AirConditionControlDetail
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAirConditionControlDetailDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AirConditionControlDetail entity to update
	 * @return AirConditionControlDetail the persisted AirConditionControlDetail
	 *         entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AirConditionControlDetail update(AirConditionControlDetail entity);

	public AirConditionControlDetail findById(Integer id);

	/**
	 * Find all AirConditionControlDetail entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the AirConditionControlDetail property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionControlDetail> found by query
	 */
	public List<AirConditionControlDetail> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<AirConditionControlDetail> findByAirConditionTemperature(
			Object airConditionTemperature, int... rowStartIdxAndCount);

	public List<AirConditionControlDetail> findByAirConditionMode(
			Object airConditionMode, int... rowStartIdxAndCount);

	/**
	 * Find all AirConditionControlDetail entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AirConditionControlDetail> all AirConditionControlDetail
	 *         entities
	 */
	public List<AirConditionControlDetail> findAll(int... rowStartIdxAndCount);
}