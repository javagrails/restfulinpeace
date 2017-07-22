package app.in.rest.controller;

import app.in.rest.entity.Doctor;
import app.in.rest.exception.ResourceNotFoundException;
import app.in.rest.service.DoctorService;
import app.in.rest.utils.CustomResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName DoctorController.java | app.in.rest.controller | DoctorController
 * @since Jul 22, 2017 11:49:49 AM c
 */

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    DoctorService doctorService;

    // ------------------- Retrieve All Doctors -------------------------- DONE ----------
    @GetMapping("")
    public ResponseEntity<List<Doctor>> list() {
        List<Doctor> doctors = null;
        try {
            doctors = doctorService.findAll();
            if (doctors.isEmpty()) {
                // return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
                return new ResponseEntity(new CustomResponseEntity("Doctor list is empty."), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[list] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Exception to get Doctor list."), HttpStatus.OK);
        }

        return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
    }


    // ------------------- Retrieve Single Doctor ------------------------ DONE ----------
    @GetMapping("/{id}")
    public ResponseEntity<?> single(@PathVariable("id") Long id) {
        log.info("Fetching Doctor with id {}", id);

        Doctor doctor = doctorService.findById(id);
        if (doctor == null) {
            log.error("Doctor with id {} not found.", id);
            throw new ResourceNotFoundException(id, "Doctor not found");
        }

        return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
    }


    // ------------------- Create a Doctor ------------------------------- sdfsdf ----------
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody Doctor doctor, UriComponentsBuilder ucBuilder) {
        log.info("Creating Doctor : {}", doctor);
        if (doctorService.isDoctorExist(doctor)) {
            log.error("Unable to create. A Doctor with name {} already exist", doctor.getName());
            return new ResponseEntity(new CustomResponseEntity("Unable to create. A Doctor with name [ "+doctor.getName()+" ] already exist."), HttpStatus.OK);
        }
        try {
            doctorService.save(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[save] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Unable to create a Doctor."), HttpStatus.OK);
        }

        String path = ucBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri().toString();
        return new ResponseEntity(new CustomResponseEntity("Doctor created successfully :: "+path), HttpStatus.CREATED);

    }


    // ------------------- Update a Doctor ------------------------------- sdfsdf ----------
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Doctor doctorIn) {
        log.info("Updating Doctor with id {}", id);
        Doctor doctor = doctorService.findById(id);
        if (doctor == null) {
            log.error("Unable to update. Doctor with id {} not found.", id);
            return new ResponseEntity(new CustomResponseEntity("Unable to update. Doctor with id [ "+id+" ] not found."), HttpStatus.NOT_FOUND);
        }

        doctor.setName(doctorIn.getName());
        doctor.setHospitalName(doctorIn.getHospitalName());
        doctor.setVisitFee(doctorIn.getVisitFee());
        //doctor.setPatients(doctorIn.getPatients());

        try {
            doctorService.update(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[update] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Unable to update. Patient with id [ "+id+" ] not found."), HttpStatus.OK);
        }

        return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
    }


    // ------------------- Delete a Doctor ------------------------------- DONE ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("Fetching & Deleting Doctor with id {}", id);

        Doctor doctor = doctorService.findById(id);
        if (doctor == null) {
            log.error("Unable to delete. Doctor with id {} not found.", id);
            return new ResponseEntity(new CustomResponseEntity("No Doctor found with id ("+id+") to delete."), HttpStatus.OK);
        }

        try {
            doctorService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[delete] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Exception to delete the Doctor having id("+id+")"), HttpStatus.OK);
        }

        //return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
        return new ResponseEntity(new CustomResponseEntity("Doctor with id ("+id+") deleted successfully."), HttpStatus.OK);

    }



}
