package hh.palvelinohjelmointi.travel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.palvelinohjelmointi.travel.web.BookingController;
import hh.palvelinohjelmointi.travel.web.BookingRestController;
import hh.palvelinohjelmointi.travel.web.TrainTypeController;
import hh.palvelinohjelmointi.travel.web.TripController;
import hh.palvelinohjelmointi.travel.web.UserController;
import hh.palvelinohjelmointi.travel.web.UserDetailServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TravelApplicationTests {

	@Autowired
	private TripController tripController;
	@Autowired
	private BookingController bookingController;
	@Autowired
	private BookingRestController bookingRestController;
	@Autowired
	private TrainTypeController trainTypeController;
	@Autowired
	private UserController userController;
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Test
	@DisplayName("Testing controllers")
	void contextLoads() {
		assertThat(tripController).isNotNull();
		assertThat(bookingController).isNotNull();
		assertThat(bookingRestController).isNotNull();
		assertThat(trainTypeController).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(userDetailServiceImpl).isNotNull();
	}

}
