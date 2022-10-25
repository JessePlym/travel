package hh.palvelinohjelmointi.travel.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {

	Booking findByUser(User user);
}
