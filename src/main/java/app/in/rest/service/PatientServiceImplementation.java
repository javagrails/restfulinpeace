package app.in.rest.service;

import app.in.rest.entity.Doctor;
import app.in.rest.entity.Patient;
import app.in.rest.repository.DoctorRepository;
import app.in.rest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName PatientServiceImplementation.java | app.in.rest.service | PatientServiceImplementation
 * @since Jul 20, 2017 22:54:54 PM
 */

@Service
@Transactional
public class PatientServiceImplementation implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorPatientService doctorPatientService;

    @Override
    public Patient findById(Long id) {
        return patientRepository.findOne(id);

    }

    @Override
    public Patient findByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public Patient findByCity(String city) {
        return patientRepository.findByCity(city);
    }

    @Override
    public Patient findByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public boolean isPatientExist(Patient patient) {
        return findByName(patient.getName()) != null;
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        patientRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        patientRepository.deleteAll();
    }
}
