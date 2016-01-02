package com.xjtu.sglab.gateway.dao;

import java.util.List;
import java.util.Set;

import com.xjtu.sglab.gateway.entity.Curtain;

/**
 * Interface for CurtainDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICurtainDAO {
	/**
	 * Perform an initial save of a previously unsaved Curtain entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICurtainDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Curtain entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Curtain entity);

	/**
	 * Delete a persistent Curtain entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICurtainDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Curtain entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Curtain entity);

	/**
	 * Persist a previously saved Curtain entity and return it or a copy of it
	 * to the sender. A copy of the Curtain entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICurtainDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Curtain entity to update
	 * @return Curtain the persisted Curtain entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Curtain update(Curtain entity);

	public Curtain findById(Integer id);

	/**
	 * Find all Curtain entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Curtain property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Curtain> found by query
	 */
	public List<Curtain> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Curtain> findByCurtainIp(Object curtainIp,
			int... rowStartIdxAndCount);

	public List<Curtain> findByCurtainSize(Object curtainSize,
			int... rowStartIdxAndCount);

	public List<Curtain> findByCurtainRatedPower(Object curtainRatedPower,
			int... rowStartIdxAndCount);

	public List<Curtain> findByIsAlreadyControlled(Object isAlreadyControlled,
			int... rowStartIdxAndCount);

	/**
	 * Find all Curtain entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Curtain> all Curtain entities
	 */
	public List<Curtain> findAll(int... rowStartIdxAndCount);
}