package hh.palvelinohjelmointi.travel.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {

	List<Booking> findByUser(User user);
}
