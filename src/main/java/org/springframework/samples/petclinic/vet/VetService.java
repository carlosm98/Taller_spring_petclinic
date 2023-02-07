package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@Service
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    public List<Vet> findByLastName(String lastName){
        return vetRepository.findByLastName(lastName);
    }

    public List<Vet> findByFirstNameAndLastName(String firstName, String lastName) {
        return vetRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    
    public List<Vet> findByFirstNameOrLastName(String firstName, String lastName) {
        return vetRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public void save(Vet vet) {
        vetRepository.save(vet);
    }

    public Page<Vet> findAll(Pageable pageable) {
        return vetRepository.findAll(pageable);
    }

    public 	Collection<Vet> findAll(){
        return vetRepository.findAll();
    }

    public Vet findById(Integer id) {
        return vetRepository.findById(id);
    }  
    
}

