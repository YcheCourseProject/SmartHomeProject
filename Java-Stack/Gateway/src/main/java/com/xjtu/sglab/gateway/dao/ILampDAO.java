package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.Lamp;

/**
 * Interface for LampDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILampDAO {
	/**
	 * Perform an initial save of a previously unsaved Lamp entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILampDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lamp entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Lamp entity);

	/**
	 * Delete a persistent Lamp entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILampDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Lamp entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Lamp entity);

	/**
	 * Persist a previously saved Lamp entity and return it or a copy of it to
	 * the sender. A copy of the Lamp entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILampDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lamp entity to update
	 * @return Lamp the persisted Lamp entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Lamp update(Lamp entity);

	public Lamp findById(Integer id);

	/**
	 * Find all Lamp entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Lamp property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Lamp> found by query
	 */
	public List<Lamp> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Lamp> findByLampType(Object lampType,
			int... rowStartIdxAndCount);

	public List<Lamp> findByLampRatedPower(Object lampRatedPower,
			int... rowStartIdxAndCount);

	public List<Lamp> findByLampLocation(Object lampLocation,
			int... rowStartIdxAndCount);

	/**
	 * Find all Lamp entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Lamp> all Lamp entities
	 */
	public List<Lamp> findAll(int... rowStartIdxAndCount);
}