package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.ElectricityType;

/**
 * Interface for ElectricityTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IElectricityTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved ElectricityType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElectricityTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ElectricityType entity);

	/**
	 * Delete a persistent ElectricityType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElectricityTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ElectricityType entity);

	/**
	 * Persist a previously saved ElectricityType entity and return it or a copy
	 * of it to the sender. A copy of the ElectricityType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IElectricityTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElectricityType entity to update
	 * @return ElectricityType the persisted ElectricityType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ElectricityType update(ElectricityType entity);

	public ElectricityType findById(Integer id);

	/**
	 * Find all ElectricityType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElectricityType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityType> found by query
	 */
	public List<ElectricityType> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<ElectricityType> findByElectricityType(Object electricityType,
			int... rowStartIdxAndCount);

	/**
	 * Find all ElectricityType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ElectricityType> all ElectricityType entities
	 */
	public List<ElectricityType> findAll(int... rowStartIdxAndCount);
}