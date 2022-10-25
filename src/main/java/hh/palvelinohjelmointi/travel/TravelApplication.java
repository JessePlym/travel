package hh.palvelinohjelmointi.travel;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.travel.domain.Booking;
import hh.palvelinohjelmointi.travel.domain.BookingRepository;
import hh.palvelinohjelmointi.travel.domain.Trip;
import hh.palvelinohjelmointi.travel.domain.TripRepository;

@SpringBootApplication
public class TravelApplication {

	private static final Logger log = LoggerFactory.getLogger(TravelApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	@Bean
	public CommandLineRunner travelDemo(BookingRepository bookingRepo, TripRepository tripRepo) {
		return (args) -> {
			// creating test trips and adding to repos
			Trip trip1 = new Trip("Helsinki", "Tampere", LocalDate.of(2022, 10, 25), "12:00", "13:30", "ExpressTrain");
			Trip trip2 = new Trip("Helsinki", "Turku", LocalDate.of(2022, 10, 25), "12:05", "13:40", "InterCity");

			tripRepo.save(trip1);
			tripRepo.save(trip2);

			for (Trip trip : tripRepo.findAll()) {
				log.info(trip.toString());
			}

			// creating test bookings that use previously made trips
			Booking booking1 = new Booking(trip1);
			Booking booking2 = new Booking(trip2);
			bookingRepo.save(booking1);
			bookingRepo.save(booking2);

			for (Booking booking : bookingRepo.findAll()) {
				log.info(booking.toString());
			}
		};
	}

}
