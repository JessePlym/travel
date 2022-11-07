package hh.palvelinohjelmointi.travel;

import java.util.Arrays;
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
			userRepo.saveAll(Arrays.asList(user1, user2, admin));

			// logging users to console
//			for (User user : userRepo.findAll()) {
//				log.info(user.toString());
//			}

			// creating few test traintypes
			TrainType type1 = new TrainType("ExpressTrain");
			TrainType type2 = new TrainType("BulletTrain");
			typeRepo.saveAll(Arrays.asList(type1, type2));

			// creating test trips with traintypes and adding to repos
			Trip trip1 = new Trip("Helsinki", "Tampere", "12:00", "13:30", type1);
			Trip trip2 = new Trip("Helsinki", "Turku", "12:05", "13:40", type2);
			Trip trip3 = new Trip("Helsinki", "Tampere", "13:00", "14:30", type1);
			Trip trip4 = new Trip("Helsinki", "Kuopio", "13:00", "17:30", type1);
			Trip trip5 = new Trip("Helsinki", "Pori", "13:05", "16:00", type2);
			Trip trip6 = new Trip("Helsinki", "Turku", "17:05", "18:40", type2);
			Trip trip7 = new Trip("Helsinki", "Tampere", "13:05", "14:35", type2);
			Trip trip8 = new Trip("Helsinki", "Tampere", "17:00", "18:30", type1);
			tripRepo.saveAll(Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip7, trip8, trip6));

			// logging trips to console
//			for (Trip trip : tripRepo.findAll()) {
//				log.info(trip.toString());
//			}

			// creating test bookings that use previously made trips
			Booking booking1 = new Booking(trip1, user1, new Date());
			Booking booking2 = new Booking(trip2, user2, new Date());
			bookingRepo.saveAll(Arrays.asList(booking1, booking2));

//			for (Booking booking : bookingRepo.findAll()) {
//				log.info(booking.toString());
//			}
		};
	}

}
