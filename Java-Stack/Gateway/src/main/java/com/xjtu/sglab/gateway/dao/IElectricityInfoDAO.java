package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.ElectricityInfo;

/**
 * Interface for ElectricityInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IElectricityInfoDAO {
	/**
	 * Perform an initial save of a previously unsaved ElectricityInfo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElectricityInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ElectricityInfo entity);

	/**
	 * Delete a persistent ElectricityInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElectricityInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ElectricityInfo entity);

	/**
	 * Persist a previously saved ElectricityInfo entity and return it or a copy
	 * of it to the sender. A copy of the ElectricityInfo entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IElectricityInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityInfo entity to update
	 * @return ElectricityInfo the persisted ElectricityInfo entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ElectricityInfo update(ElectricityInfo entity);

	public ElectricityInfo findById(Integer id);

	/**
	 * Find all ElectricityInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElectricityInfo property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityInfo> found by query
	 */
	public List<ElectricityInfo> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<ElectricityInfo> findByActivePower(Object activePower,
			int... rowStartIdxAndCount);

	public List<ElectricityInfo> findByReactivePower(Object reactivePower,
			int... rowStartIdxAndCount);

	public List<ElectricityInfo> findByTotalConsumeEnergy(
			Object totalConsumeEnergy, int... rowStartIdxAndCount);

	/**
	 * Find all ElectricityInfo entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityInfo> all ElectricityInfo entities
	 */
	public List<ElectricityInfo> findAll(int... rowStartIdxAndCount);
}