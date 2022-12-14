package hh.palvelinohjelmointi.travel.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TrainType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;

	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trainType")
	private List<Trip> trips;

	public TrainType() {
		super();
		this.name = null;
	}

	public TrainType(String name) {
		super();
		this.name = name;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@Override
	public String toString() {
		return "TrainType [typeId=" + typeId + ", name=" + name + "]";
	}

}
