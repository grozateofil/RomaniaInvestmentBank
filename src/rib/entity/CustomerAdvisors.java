package rib.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Customer_Advisors")
@Getter
@Setter
@NoArgsConstructor
public class CustomerAdvisors {
	@Id
	@Column(name = "No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "CNP")
	private String cnp;

	@Column(name = "PositionHeld")
	private String positionHeld;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "customerAdvisors")
	private List<Client> client;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BankAgency bankAgency;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CustomerAdvisorsPassword_No")
	private CustomerAdvisorsPassword customerAdvisorsPassword;

	public CustomerAdvisors(String firstName, String lastName, String cnp, String positionHeld, Address address,
			String phoneNumber, BankAgency bankAgency, CustomerAdvisorsPassword customerAdvisorsPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cnp = cnp;
		this.positionHeld = positionHeld;
		this.phoneNumber = phoneNumber;
		this.bankAgency = bankAgency;
		this.address = address;
		this.customerAdvisorsPassword = customerAdvisorsPassword;
	}

	@Override
	public String toString() {
		String finalString;
		finalString = "\nBancher " + id + "\nFunctie: " + positionHeld + "\nNume: " + firstName + " " + lastName
				+ "\nCNP: " + cnp + "\nTelefon: " + phoneNumber;
		if (Hibernate.isInitialized(this.address) && this.address != null)
			finalString += "\nAdresa bancher: " + this.address;
		if (Hibernate.isInitialized(this.bankAgency) && this.bankAgency != null)
			finalString += "\nAdresa agentie bancara: " + this.bankAgency.getAddress();
		finalString += "\n";
		return finalString;
	}

}
