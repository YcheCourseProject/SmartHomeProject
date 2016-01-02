package com.xjtu.sglab.gateway.dao.impl;

import com.xjtu.sglab.gateway.dao.IDailyElectricityPriceDAO;
import com.xjtu.sglab.gateway.dao.impl.EntityManagerHelper;
import com.xjtu.sglab.gateway.entity.DailyElectricityPrice;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * DailyElectricityPrice entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see com.xjtu.sglab.shems.dao.impl.test.DailyElectricityPrice
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DailyElectricityPriceDAO implements IDailyElectricityPriceDAO {
	// property constants
	public static final String PRICE_PERIOD0 = "pricePeriod0";
	public static final String PRICE_PERIOD1 = "pricePeriod1";
	public static final String PRICE_PERIOD2 = "pricePeriod2";
	public static final String PRICE_PERIOD3 = "pricePeriod3";
	public static final String PRICE_PERIOD4 = "pricePeriod4";
	public static final String PRICE_PERIOD5 = "pricePeriod5";
	public static final String PRICE_PERIOD6 = "pricePeriod6";
	public static final String PRICE_PERIOD7 = "pricePeriod7";
	public static final String PRICE_PERIOD8 = "pricePeriod8";
	public static final String PRICE_PERIOD9 = "pricePeriod9";
	public static final String PRICE_PERIOD10 = "pricePeriod10";
	public static final String PRICE_PERIOD11 = "pricePeriod11";
	public static final String PRICE_PERIOD12 = "pricePeriod12";
	public static final String PRICE_PERIOD13 = "pricePeriod13";
	public static final String PIRCE_PERIOD14 = "pircePeriod14";
	public static final String PRICE_PERIOD15 = "pricePeriod15";
	public static final String PRICE_PERIOD16 = "pricePeriod16";
	public static final String PRICE_PERIOD17 = "pricePeriod17";
	public static final String PRICE_PERIOD18 = "pricePeriod18";
	public static final String PRICE_PERIOD19 = "pricePeriod19";
	public static final String PRICE_PERIOD20 = "pricePeriod20";
	public static final String PRICE_PERIOD21 = "pricePeriod21";
	public static final String PRICE_PERIOD22 = "pricePeriod22";
	public static final String PRICE_PERIOD23 = "pricePeriod23";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * DailyElectricityPriceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DailyElectricityPrice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DailyElectricityPrice entity) {
		EntityManagerHelper.log("saving DailyElectricityPrice instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * DailyElectricityPriceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DailyElectricityPrice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DailyElectricityPrice entity) {
		EntityManagerHelper.log("deleting DailyElectricityPrice instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					DailyElectricityPrice.class,
					entity.getDailyElectricityPriceId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = DailyElectricityPriceDAO.update(entity);
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
	public DailyElectricityPrice update(DailyElectricityPrice entity) {
		EntityManagerHelper.log("updating DailyElectricityPrice instance",
				Level.INFO, null);
		try {
			DailyElectricityPrice result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DailyElectricityPrice findById(Integer id) {
		EntityManagerHelper.log(
				"finding DailyElectricityPrice instance with id: " + id,
				Level.INFO, null);
		try {
			DailyElectricityPrice instance = getEntityManager().find(
					DailyElectricityPrice.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<DailyElectricityPrice> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DailyElectricityPrice> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding DailyElectricityPrice instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DailyElectricityPrice model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<DailyElectricityPrice> findByPricePeriod0(Object pricePeriod0,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD0, pricePeriod0, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod1(Object pricePeriod1,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD1, pricePeriod1, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod2(Object pricePeriod2,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD2, pricePeriod2, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod3(Object pricePeriod3,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD3, pricePeriod3, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod4(Object pricePeriod4,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD4, pricePeriod4, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod5(Object pricePeriod5,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD5, pricePeriod5, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod6(Object pricePeriod6,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD6, pricePeriod6, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod7(Object pricePeriod7,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD7, pricePeriod7, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod8(Object pricePeriod8,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD8, pricePeriod8, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod9(Object pricePeriod9,
			int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD9, pricePeriod9, rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod10(
			Object pricePeriod10, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD10, pricePeriod10,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod11(
			Object pricePeriod11, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD11, pricePeriod11,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod12(
			Object pricePeriod12, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD12, pricePeriod12,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod13(
			Object pricePeriod13, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD13, pricePeriod13,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPircePeriod14(
			Object pircePeriod14, int... rowStartIdxAndCount) {
		return findByProperty(PIRCE_PERIOD14, pircePeriod14,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod15(
			Object pricePeriod15, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD15, pricePeriod15,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod16(
			Object pricePeriod16, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD16, pricePeriod16,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod17(
			Object pricePeriod17, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD17, pricePeriod17,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod18(
			Object pricePeriod18, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD18, pricePeriod18,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod19(
			Object pricePeriod19, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD19, pricePeriod19,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod20(
			Object pricePeriod20, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD20, pricePeriod20,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod21(
			Object pricePeriod21, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD21, pricePeriod21,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod22(
			Object pricePeriod22, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD22, pricePeriod22,
				rowStartIdxAndCount);
	}

	public List<DailyElectricityPrice> findByPricePeriod23(
			Object pricePeriod23, int... rowStartIdxAndCount) {
		return findByProperty(PRICE_PERIOD23, pricePeriod23,
				rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<DailyElectricityPrice> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all DailyElectricityPrice instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DailyElectricityPrice model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}