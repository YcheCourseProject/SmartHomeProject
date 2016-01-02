package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.LocationType;

/**
 * Interface for LocationTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILocationTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved LocationType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationType entity);

	/**
	 * Delete a persistent LocationType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationType entity);

	/**
	 * Persist a previously saved LocationType entity and return it or a copy of
	 * it to the sender. A copy of the LocationType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILocationTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationType entity to update
	 * @return LocationType the persisted LocationType entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LocationType update(LocationType entity);

	public LocationType findById(Integer id);

	/**
	 * Find all LocationType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LocationType> found by query
	 */
	public List<LocationType> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<LocationType> findByLocType(Object locType,
			int... rowStartIdxAndCount);

	/**
	 * Find all LocationType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LocationType> all LocationType entities
	 */
	public List<LocationType> findAll(int... rowStartIdxAndCount);
}