package com.xjtu.sglab.gateway.dao;

import java.util.Date;
import java.util.List;

import com.xjtu.sglab.gateway.entity.DailyElectricityPrice;

/**
 * Interface for DailyElectricityPriceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDailyElectricityPriceDAO {
	/**
	 * Perform an initial save of a previously unsaved DailyElectricityPrice
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDailyElectricityPriceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DailyElectricityPrice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DailyElectricityPrice entity);

	/**
	 * Delete a persistent DailyElectricityPrice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDailyElectricityPriceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DailyElectricityPrice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DailyElectricityPrice entity);

	/**
	 * Persist a previously saved DailyElectricityPrice entity and return it or
	 * a copy of it to the sender. A copy of the DailyElectricityPrice entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDailyElectricityPriceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DailyElectricityPrice entity to update
	 * @return DailyElectricityPrice the persisted DailyElectricityPrice entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DailyElectricityPrice update(DailyElectricityPrice entity);

	public DailyElectricityPrice findById(Integer id);

	/**
	 * Find all DailyElectricityPrice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DailyElectricityPrice property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<DailyElectricityPrice> found by query
	 */
	public List<DailyElectricityPrice> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod0(Object pricePeriod0,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod1(Object pricePeriod1,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod2(Object pricePeriod2,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod3(Object pricePeriod3,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod4(Object pricePeriod4,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod5(Object pricePeriod5,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod6(Object pricePeriod6,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod7(Object pricePeriod7,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod8(Object pricePeriod8,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod9(Object pricePeriod9,
			int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod10(
			Object pricePeriod10, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod11(
			Object pricePeriod11, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod12(
			Object pricePeriod12, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod13(
			Object pricePeriod13, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPircePeriod14(
			Object pircePeriod14, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod15(
			Object pricePeriod15, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod16(
			Object pricePeriod16, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod17(
			Object pricePeriod17, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod18(
			Object pricePeriod18, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod19(
			Object pricePeriod19, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod20(
			Object pricePeriod20, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod21(
			Object pricePeriod21, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod22(
			Object pricePeriod22, int... rowStartIdxAndCount);

	public List<DailyElectricityPrice> findByPricePeriod23(
			Object pricePeriod23, int... rowStartIdxAndCount);

	/**
	 * Find all DailyElectricityPrice entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<DailyElectricityPrice> all DailyElectricityPrice entities
	 */
	public List<DailyElectricityPrice> findAll(int... rowStartIdxAndCount);
}