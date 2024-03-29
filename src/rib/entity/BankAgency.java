package rib.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BankAgency")
@Getter
@Setter
@NoArgsConstructor
public class BankAgency {
	@Id
	@Column(name = "No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;

	@Column(name = "ID")
	private String id;

	@Column(name = "PhoneBankNumber")
	private String phoneBankNumber;

	@Column(name = "OperatingMorningHours")
	private String operatingMorningHours;

	@Column(name = "OperatingAfternoonHours")
	private String operatingAfternoonHours;

	@Column(name = "LunchBreak")
	private String lunchBreak;

	@OneToMany(cascade = { CascadeType.ALL, CascadeType.MERGE }, mappedBy = "bankAgency")
	private List<CustomerAdvisors> customerAdvisors;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;

	public BankAgency(String id, String phoneBankNumber, String operatingMorningHours, String operatingAfternoonHours,
			String lunchBreak, Address address) {
		super();
		this.id = id;
		this.phoneBankNumber = phoneBankNumber;
		this.operatingMorningHours = operatingMorningHours;
		this.operatingAfternoonHours = operatingAfternoonHours;
		this.lunchBreak = lunchBreak;
		this.address = address;
	}

	@Override
	public String toString() {
		String finalString;
		finalString = "\nAgentie bancara " + "\nId: " + id + "\nTelefon: " + phoneBankNumber
				+ "\nProgram\nLuni-Vineri: " + operatingMorningHours + " si " + operatingAfternoonHours
				+ "\nSambata si Duminica: inchis" + "\nPauza de masa: " + lunchBreak + "\n";

		if (Hibernate.isInitialized(this.address) && this.address != null)
			finalString += "Adresa agentie bancara: " + this.address + "\n";
		return finalString;
	}

}
