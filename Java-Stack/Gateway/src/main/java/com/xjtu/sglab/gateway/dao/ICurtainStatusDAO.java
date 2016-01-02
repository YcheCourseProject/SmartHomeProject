package com.xjtu.sglab.gateway.dao;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.sglab.gateway.entity.CurtainStatus;

/**
 * Interface for CurtainStatusDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICurtainStatusDAO {
	/**
	 * Perform an initial save of a previously unsaved CurtainStatus entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICurtainStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CurtainStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CurtainStatus entity);

	/**
	 * Delete a persistent CurtainStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICurtainStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CurtainStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CurtainStatus entity);

	/**
	 * Persist a previously saved CurtainStatus entity and return it or a copy
	 * of it to the sender. A copy of the CurtainStatus entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICurtainStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CurtainStatus entity to update
	 * @return CurtainStatus the persisted CurtainStatus entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CurtainStatus update(CurtainStatus entity);

	public CurtainStatus findById(Integer id);

	/**
	 * Find all CurtainStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CurtainStatus property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CurtainStatus> found by query
	 */
	public List<CurtainStatus> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<CurtainStatus> findByCurtainStatus(Object curtainStatus,
			int... rowStartIdxAndCount);

	public List<CurtainStatus> findByIsControlledByUser(
			Object isControlledByUser, int... rowStartIdxAndCount);

	public List<CurtainStatus> findByIsAlreadyControlled(
			Object isAlreadyControlled, int... rowStartIdxAndCount);

	/**
	 * Find all CurtainStatus entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CurtainStatus> all CurtainStatus entities
	 */
	public List<CurtainStatus> findAll(int... rowStartIdxAndCount);
}