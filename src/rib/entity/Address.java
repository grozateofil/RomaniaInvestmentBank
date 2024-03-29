package rib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

	@Id
	@Column(name = "No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;

	@Column(name = "City")
	private String city;

	@Column(name = "County")
	private String county;

	@Column(name = "HouseNumber")
	private String houseNumber;

	@Column(name = "BlockOfFlatsNumber")
	private String blockOfFlatsNumber;

	@Column(name = "Apartment")
	private String apartment;

	@Column(name = "Street")
	private String street;

	@OneToOne(mappedBy = "address")
	private Client client;

	@OneToOne(mappedBy = "address")
	private CustomerAdvisors customerAdvisors;

	@OneToOne(mappedBy = "address")
	private BankAgency bankAgency;

	public Address(int no, String county, String city, String street, String houseNumber, String blockOfFlatsNumber,
			String apartment) {
		super();
		this.no=no;
		this.county = county;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.apartment = apartment;
		this.blockOfFlatsNumber = blockOfFlatsNumber;
	}

	@Override
	public String toString() {
		String finalString = "\nAdresa "+this.no+": ";
		if (Hibernate.isInitialized(this.county) && this.county != null)
			finalString += "Judet " + this.county+", ";
		if (Hibernate.isInitialized(this.city) && this.city != null)
			finalString += "Oras " + this.city+", ";
		if (Hibernate.isInitialized(this.street) && this.street != null)
			finalString += "Str. " + this.street+", ";
		if (Hibernate.isInitialized(this.houseNumber) && this.houseNumber != null)
			finalString += "Nr. " + this.houseNumber;
		if (Hibernate.isInitialized(this.blockOfFlatsNumber) && this.blockOfFlatsNumber != null)
			finalString += "Nr. bloc. " + this.blockOfFlatsNumber+", ";
		if (Hibernate.isInitialized(this.apartment) && this.apartment != null)
			finalString += "Ap. " + this.apartment;
		// else
		//finalString += "\n";
		return finalString;
	}
}
