package app.in.rest.controller;

import app.in.rest.entity.Patient;
import app.in.rest.exception.ResourceNotFoundException;
import app.in.rest.service.DoctorPatientService;
import app.in.rest.service.PatientService;
import app.in.rest.utils.CustomResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @fileName PatientController.java | app.in.rest.controller | PatientController
 * @since Jul 22, 2017 11:49:49 AM
 */

@RestController
@RequestMapping("/doctors/{doctorId}")
public class PatientController {
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorPatientService doctorPatientService;

    // ------------------- Retrieve All Patients -------------------------- DONE ----------
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> list() {
        List<Patient> patients = null;
        try {
            patients = patientService.findAll();
            if (patients.isEmpty()) {
                // return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
                return new ResponseEntity(new CustomResponseEntity("Patient list is empty."), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[list] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Exception to get Patient list."), HttpStatus.OK);
        }
        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
    }


    // ------------------- Retrieve Single Patient ------------------------ DONE ----------
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> single(@PathVariable("id") Long id) {
        log.info("Fetching Patient with id {}", id);

        Patient patient = patientService.findById(id);
        if (patient == null) {
            log.error("Patient with id {} not found.", id);
            throw new ResourceNotFoundException(id, "Patient not found");
        }
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }


    // ------------------- Create a Patient ------------------------------- sdfsdf ----------
    @PostMapping(value = "/patients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@PathVariable("doctorId") Long doctorId, @Valid @RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
        log.info("Creating Patient : {}", patient);

        if (!doctorPatientService.isDoctorExist(doctorId)) {
            log.error("Doctor having id {} not exist", doctorId);
            return new ResponseEntity(new CustomResponseEntity("Doctor having id "+doctorId+" not exist"), HttpStatus.OK);
        }

        if (patientService.isPatientExist(patient)) {
            log.error("Unable to create. A Patient with name {} already exist", patient.getName());
            return new ResponseEntity(new CustomResponseEntity("Unable to create. A Patient with name [ "+patient.getName()+" ] already exist."), HttpStatus.OK);
        }

        try {
            //patientService.save(patient);
            doctorPatientService.savePatientWithDoctor(doctorId,patient);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[save] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Unable to create a Patient."), HttpStatus.OK);
        }

        String path = ucBuilder.path("/doctors/{doctorId}/patients/{id}").buildAndExpand(doctorId, patient.getId()).toUri().toString();
        return new ResponseEntity(new CustomResponseEntity("Patient created successfully under a Doctor :: "+path), HttpStatus.CREATED);

    }


    // ------------------- Update a Patient ------------------------------- sdfsdf ----------
    @PutMapping("/patients/{id}")
    public ResponseEntity<?> update(@PathVariable("doctorId") Long doctorId, @PathVariable("id") Long id, @RequestBody Patient patientIn) {
        log.info("Updating Patient with id {}", id);

        if (!doctorPatientService.isDoctorExist(doctorId)) {
            log.error("Doctor having id {} not found", doctorId);
            return new ResponseEntity(new CustomResponseEntity("Doctor having id "+doctorId+" not found"), HttpStatus.OK);
        }

        Patient patient = patientService.findById(id);
        if (patient == null) {
            log.error("Unable to update. Patient with id {} not found.", id);
            return new ResponseEntity(new CustomResponseEntity("Unable to update. Patient with id [ "+id+" ] not found."), HttpStatus.NOT_FOUND);
        }

        patient.setName(patientIn.getName());
        patient.setPhone(patientIn.getPhone());
        patient.setCity(patientIn.getCity());
        //patient.setDoctors(patientIn.getDoctors());

        try {
            patientService.update(patient);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[update] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Unable to update. Patient with id [ "+id+" ] not found."), HttpStatus.OK);
        }

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }


    // ------------------- Delete a Patient ------------------------------- DONE ----------
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> delete(@PathVariable("doctorId") Long doctorId, @PathVariable("id") Long id) {
        log.info("Fetching & Deleting Patient with id {}", id);

        if (!doctorPatientService.isDoctorExist(doctorId)) {
            log.error("Doctor having id {} not found", doctorId);
            return new ResponseEntity(new CustomResponseEntity("Doctor having id "+doctorId+" not found"), HttpStatus.OK);
        }

        Patient patient = patientService.findById(id);
        if (patient == null) {
            log.error("Unable to delete. Patient with id {} not found.", id);
            return new ResponseEntity(new CustomResponseEntity("No Patient found with id ("+id+") to delete."), HttpStatus.OK);
        }

        try {
            patientService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception[delete] => "+e.getMessage());
            return new ResponseEntity(new CustomResponseEntity("Exception to delete the Patient having id("+id+")"), HttpStatus.OK);
        }

        //return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
        return new ResponseEntity(new CustomResponseEntity("Patient with id ("+id+") deleted successfully."), HttpStatus.OK);
    }




}
