package com.example;


import java.util.Arrays;
import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ReservationServiceApplication {
	
	/*<bean class=../CommandLineRunner id=demo>
	</bean>*/
	@Bean
	CommandLineRunner demo(ReservationRepository  rr){
		
		return args ->{
			Arrays.asList("sarah, Serene, sneha, manoj".split(",")).forEach(x -> rr.save(new Reservation(x)));
			
			rr.findByReservationName("sarah").forEach(System.out::println);;
			rr.findAll().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}

@RestController
class ReservationRestController{
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping(value= "/reservations", method = RequestMethod.GET)
	Collection<Reservation> reservations(){
		
		return this.reservationRepository.findAll();
	}
}

interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	// select * from reservation where reservation_Name = :rn
	Collection<Reservation> findByReservationName(String rn);
}



@Entity
class Reservation{
	
	@Id
	@GeneratedValue
	private Long id;

	private String reservationName;
	
	
Reservation(){			
		
	}	
	public Reservation(String reservationName){		
		this.reservationName = reservationName;			
	}	

	public Long getId() {
		return id;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}
	
	
	
}
