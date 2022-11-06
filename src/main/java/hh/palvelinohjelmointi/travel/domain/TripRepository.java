package hh.palvelinohjelmointi.travel.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {

	List<Trip> findByDestination(String destination);

}
