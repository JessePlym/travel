package hh.palvelinohjelmointi.travel.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
	Optional<Trip> findById(Long id);
}
