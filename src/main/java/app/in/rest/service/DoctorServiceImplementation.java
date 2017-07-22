package app.in.rest.service;

import app.in.rest.entity.Doctor;
import app.in.rest.entity.Patient;
import app.in.rest.repository.DoctorRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName DoctorServiceImplementation.java | app.in.rest.service | DoctorServiceImplementation
 * @since Jul 20, 2017 22:53:53 PM
 */

@Service
@Transactional
public class DoctorServiceImplementation implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findOne(id);
    }

    @Override
    public Doctor findByName(String name) {
        return doctorRepository.findByName(name);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public boolean isDoctorExist(Doctor doctor) {
        return findByName(doctor.getName()) != null;
    }

    @Override
    @Transactional
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void update(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        doctorRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        doctorRepository.deleteAll();
    }
}
