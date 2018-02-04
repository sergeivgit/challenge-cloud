package com.demo.citiesmanagement.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.citiesmanagement.domain.CityRoute;

/**
 * The Interface CityRoutesRepository.
 */
@Repository
public interface CityRoutesRepository extends CrudRepository<CityRoute, Long> {

	/**
	 * Find routes by city.
	 *
	 * @param city
	 *            the city
	 * @return the collection
	 */
	@Query("SELECT t FROM CityRoute t where t.city = ?1")
	public Collection<CityRoute> findRoutesByCity(String city);

	/**
	 * Del routes for city.
	 *
	 * @param city
	 *            the city
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM CityRoute t where t.city = ?1")
	// public Collection<CityRoute> delRoutesForCity(String city);
	public void delRoutesForCity(String city);

}
