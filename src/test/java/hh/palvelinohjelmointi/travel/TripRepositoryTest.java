package hh.palvelinohjelmointi.travel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.palvelinohjelmointi.travel.domain.Trip;
import hh.palvelinohjelmointi.travel.domain.TripRepository;

@DataJpaTest
public class TripRepositoryTest {

	@Autowired
	private TripRepository repository;

	@Test
	void saveTrip() {
		repository.save(new Trip("Helsinki", "Oulu", "12:00", "20:00", null));
		assertThat(repository.findByDestination("Oulu")).isNotNull();
	}

	@Test
	void deleteAllTrips() {
		repository.save(new Trip("Tampere", "Oulu", "12:00", "20:00", null));
		repository.deleteAll();
		assertThat(repository.count()).isEqualTo(0);
	}

	@Test
	void deleteTripById() {
		Trip trip = new Trip("Tampere", "Jyväskylä", "12:00", "20:00", null);
		repository.save(trip);
		Long id = trip.getTripId();
		repository.deleteById(trip.getTripId());
		assertThat(repository.existsById(id)).isFalse();
	}

	@Test
	void updateTrip() {
		Trip trip = new Trip("Tampere", "Jyväskylä", "12:00", "20:00", null);
		repository.save(trip);
		repository.findById(trip.getTripId()).get().setDestination("Kuopio");
		assertThat(repository.findByDestination("Kuopio")).isNotNull();
	}

}
