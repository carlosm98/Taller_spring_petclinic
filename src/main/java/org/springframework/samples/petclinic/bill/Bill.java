package org.springframework.samples.petclinic.bill;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Visit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill extends BaseEntity{

	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;

	@Column(name = "money")
	private double money;
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "visit_id", referencedColumnName = "id")
    private Visit visit;

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	@Override
	public String toString() {
		return "Bill [date=" + paymentDate + ", money=" + money + ", visit=" + visit + "]";
	}
	
}
