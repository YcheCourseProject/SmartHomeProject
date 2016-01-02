package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.Room;

/**
 * Interface for RoomDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRoomDAO {
	/**
	 * Perform an initial save of a previously unsaved Room entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRoomDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Room entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Room entity);

	/**
	 * Delete a persistent Room entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRoomDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Room entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Room entity);

	/**
	 * Persist a previously saved Room entity and return it or a copy of it to
	 * the sender. A copy of the Room entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRoomDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Room entity to update
	 * @return Room the persisted Room entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Room update(Room entity);

	public Room findById(Integer id);

	/**
	 * Find all Room entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Room property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Room> found by query
	 */
	public List<Room> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Room> findByUserId(Object userId, int... rowStartIdxAndCount);

	public List<Room> findByRoomSize(Object roomSize,
			int... rowStartIdxAndCount);

	/**
	 * Find all Room entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Room> all Room entities
	 */
	public List<Room> findAll(int... rowStartIdxAndCount);
}