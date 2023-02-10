/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.samples.petclinic.vet.SpecialityRepository;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.bill.Bill;
import org.springframework.samples.petclinic.bill.BillService;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.samples.petclinic.owner.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import org.springframework.samples.petclinic.vet.VetService;
import org.springframework.data.domain.Pageable;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */

@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication {
private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);


	
	@Autowired
	private VisitRepository visitRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private BillService billService;

	

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoVetRepository( VetService vetService, SpecialityRepository specialityRepository){
		return (args) -> {
			log.info("*****************************************************");
			log.info("Tarea 1");
			log.info("*****************************************************");

			/*
			 * - Crear un objeto vet sin Speciality
			 * - Persistir el objeto vet en BBDD
			 * - Consultar por ID y comprobar que se ha creado correctamente
			 * - Editar el elemento recién creado para añadir una Speciality concreta
			 * - Listar todos los veterinarios exixtentes
			 */

			log.info("Creamos un objeto Vet");
			Vet vet = new Vet();
			vet.setFirstName("Luis");
			vet.setLastName("Lobato Jimenez");

			log.info("Persistimos en BBDD");
			vetService.save(vet);

			log.info("Comprobamos que se ha creado correctamente");
			Vet vetAux = vetService.findById(vet.getId());
			log.info(vetAux.toString());

			log.info("Editamos el objeto y añadimos una Speciality");
			Specialty s = specialityRepository.findById(1);
			vet.addSpecialty(s);
			vetService.save(vet);
			log.info(vet.toString());

			log.info("Listamos todos los veterinarios");
			for (Vet v : vetService.findAll()) {
				log.info("Vet: " + v.getFirstName());
			}

			log.info("*****************************************************");
			log.info("Tarea 2");
			log.info("*****************************************************");

			/*
			 * A vetsRepository crear estos métodos y usarlos desde cualquier parte del código
			 * 
			 * - Obtener una lista de Vets filtrando por lastName
			 * - Obtener una lista de Vets filtrando por firstName y lastName
			 * - Obtener una lista de Vets filtrando por firstName y lastName
			 * 
			 * B Organizar el código para que este en base a servicios y no sea llamadas de controller a dao
			 * 
			 * C Crear estos métodos y usuarlos desde cualquier parte del código
			 * 
			 * - Obtener las mascotas nacidas en 2010 ordenadas por fecha de nacimiento ascendente
			 * - Crear 3 vistas nuevas para diferentes mascotass.
			 * - Obtener las 4 visitas más reccientes de todo el sistema
			 */

			System.out.println("Listado de todos los veterinarios por lastName, cuyo lastName es Carter");
			List<Vet> listVetFilterLastName =vetService.findByLastName("Carter");
			System.out.println(listVetFilterLastName);

			System.out.println("Listado de todos los veterinarios por firstName y lastName, cuyo firstName es James y lastName es Carter");
			List<Vet> listVetFilterFirstNameAndLastName =vetService.findByFirstNameAndLastName("James", "Carter");
			System.out.println(listVetFilterFirstNameAndLastName);

			System.out.println("Listado de todos los veterinarios por firstName o lastName, cuyo firstName es Helen o lastName es Carter");
			List<Vet> listVetFilterFirstNameOrLastName =vetService.findByFirstNameOrLastName("Helen","Carter");
			System.out.println(listVetFilterFirstNameOrLastName);

			System.out.println("Listado de mascotas nacidas en determinada fecha");
			Sort sort = Sort.by(Sort.Direction.ASC, "birthDate");
			LocalDate dmenor = LocalDate.of(1995, 1, 1);
			LocalDate dmayor = LocalDate.of(1995, 12, 31);
			List<Pet> listPet = petRepository.findByBirthDateBetween(dmenor, dmayor, sort);
			System.out.println(listPet);
			
			// Creamos 3 visitas nuevas para diferentes mascotas

			Pet petLeo = petRepository.findByName("Leo");
			Visit v1 = new Visit();
			v1.setDate(LocalDate.of(2023, 2, 5));
			v1.setDescription("visita1");

			Visit v2 = new Visit();
			v2.setDate(LocalDate.of(2023, 3, 5));
			v2.setDescription("visita2");

			Visit v3 = new Visit();
			v3.setDate(LocalDate.of(2023, 4, 5));
			v3.setDescription("visita3");

			petLeo.addVisit(v1);
			petLeo.addVisit(v2);
			petLeo.addVisit(v3);
			

			petRepository.save(petLeo);
			
			// Mostramos las 4 visitas más recientes

			Pageable sortedByDate = PageRequest.of(0, 4, Sort.by("date").descending());
			Page<Pet> page = visitRepository.findAll(sortedByDate);
			System.out.println(page.toList());


			log.info("*****************************************************");
			log.info("Tarea 3");
			log.info("*****************************************************");

			/*
			 * Facturas
			 * 
			 * - Listar las facturas ordenadas por fecha de pago
			 * - Creación de una factura nueva,
			 * - Listamos las facturas
			 * - Eliminamos la factura creada
			 */
			
			System.out.println("Listado de facturas ordenadas por fecha de pago");
			List<Bill> listBill = billService.findAllOrderByPaymentDate();
			System.out.println(listBill);
			
			System.out.println("Creamos una nueva factura");
			Visit visit = visitRepository.findById(4l).get();
			
			Bill bill = new Bill();
			bill.setMoney(200);
			bill.setVisit(visit);
			bill.setPaymentDate(LocalDate.of(2023, 1, 6));
			billService.saveBill(bill);
			
			System.out.println("Volvemos a listar las facturas");
			listBill = billService.findAllOrderByPaymentDate();
			System.out.println(listBill);
			
			System.out.println("Eliminamos la factura creada");
			billService.delete(bill.getId());

			System.out.println("Volvemos a listar las facturas");
			listBill = billService.findAllOrderByPaymentDate();
			System.out.println(listBill);
		};
	}
}
