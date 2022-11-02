package hh.palvelinohjelmointi.travel.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookingRepository extends CrudRepository<Booking, Long> {

	// List<Booking> findByUser(User user);

	List<Booking> findByUser(@Param("user") User user);

	List<Booking> findByTrip(@Param("trip") Trip trip);
}
