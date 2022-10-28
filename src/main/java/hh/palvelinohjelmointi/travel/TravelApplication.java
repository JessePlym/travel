package hh.palvelinohjelmointi.travel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.travel.domain.Booking;
import hh.palvelinohjelmointi.travel.domain.BookingRepository;
import hh.palvelinohjelmointi.travel.domain.TrainType;
import hh.palvelinohjelmointi.travel.domain.TrainTypeRepository;
import hh.palvelinohjelmointi.travel.domain.Trip;
import hh.palvelinohjelmointi.travel.domain.TripRepository;
import hh.palvelinohjelmointi.travel.domain.User;
import hh.palvelinohjelmointi.travel.domain.UserRepository;

@SpringBootApplication
public class TravelApplication {

	private static final Logger log = LoggerFactory.getLogger(TravelApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	@Bean
	public CommandLineRunner travelDemo(BookingRepository bookingRepo, TripRepository tripRepo,
			TrainTypeRepository typeRepo, UserRepository userRepo) {
		return (args) -> {
			// creating test users
			User user1 = new User("user1@mail.com", "USER",
					"$2a$10$VRmMn/mqKQE7SZhmk./o5O7LLjtVdHhzNS9y9LrGOGVKF4HOLAcJ6");
			User user2 = new User("user2@mail.com", "USER",
					"$2a$10$VRmMn/mqKQE7SZhmk./o5O7LLjtVdHhzNS9y9LrGOGVKF4HOLAcJ6");
			User admin = new User("admin@mail.com", "ADMIN",
					"$2a$10$f/eyQ2c01Ke.H2N38KtLV.vyuu1UWeiGmB3SByfmfa8CTS8v4RSG.");
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(admin);

			// logging users to console
			for (User user : userRepo.findAll()) {
				log.info(user.toString());
			}

			// creating few test traintypes
			TrainType type1 = new TrainType("ExpressTrain");
			TrainType type2 = new TrainType("BulletTrain");
			typeRepo.save(type1);
			typeRepo.save(type2);

			// creating test trips with traintypes and adding to repos
			Trip trip1 = new Trip("Helsinki", "Tampere", new Date(), "12:00", "13:30", type1);
			Trip trip2 = new Trip("Helsinki", "Turku", new Date(), "12:05", "13:40", type2);

			tripRepo.save(trip1);
			tripRepo.save(trip2);

			// logging trips to console
			for (Trip trip : tripRepo.findAll()) {
				log.info(trip.toString());
			}

			// creating test bookings that use previously made trips
			Booking booking1 = new Booking(trip1, user1);
			Booking booking2 = new Booking(trip2, user2);
			bookingRepo.save(booking1);
			bookingRepo.save(booking2);

			for (Booking booking : bookingRepo.findAll()) {
				log.info(booking.toString());
			}
		};
	}

}
