package org.springframework.samples.petclinic.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;
	
	public List<Bill> findAll(){
		return billRepository.findAll();
	}
	
	public List<Bill> findAllOrderByPaymentDate(){
		return billRepository.findAll(Sort.by(Sort.Direction.ASC, "paymentDate"));
	}
	
	public void saveBill(Bill bill) {
		billRepository.save(bill);
	}
	
	public void delete(long id) {
		billRepository.deleteById(id);
	}
}
