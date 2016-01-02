package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.ElectricityMeter;

/**
 * Interface for ElectricityMeterDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IElectricityMeterDAO {
	/**
	 * Perform an initial save of a previously unsaved ElectricityMeter entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElectricityMeterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityMeter entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ElectricityMeter entity);

	/**
	 * Delete a persistent ElectricityMeter entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElectricityMeterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityMeter entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ElectricityMeter entity);

	/**
	 * Persist a previously saved ElectricityMeter entity and return it or a
	 * copy of it to the sender. A copy of the ElectricityMeter entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IElectricityMeterDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityMeter entity to update
	 * @return ElectricityMeter the persisted ElectricityMeter entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ElectricityMeter update(ElectricityMeter entity);

	public ElectricityMeter findById(Integer id);

	/**
	 * Find all ElectricityMeter entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElectricityMeter property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityMeter> found by query
	 */
	public List<ElectricityMeter> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<ElectricityMeter> findByElectricityMeterIp(
			Object electricityMeterIp, int... rowStartIdxAndCount);

	/**
	 * Find all ElectricityMeter entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityMeter> all ElectricityMeter entities
	 */
	public List<ElectricityMeter> findAll(int... rowStartIdxAndCount);
}