package app.in.rest.service;

import app.in.rest.entity.Doctor;
import app.in.rest.entity.Patient;
import app.in.rest.repository.DoctorRepository;
import app.in.rest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName DoctorPatientServiceImplementation.java | app.in.rest.service | DoctorPatientServiceImplementation
 * @since Jul 22, 2017 16:14:14 PM
 */

@Service
public class DoctorPatientServiceImplementation implements DoctorPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public Doctor getDoctor(Long doctorId) {
        return this.buildDoctorFromDbDoctor(doctorRepository.findOne(doctorId));
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public boolean isDoctorExist(Long doctorId) {
        return doctorRepository.exists(doctorId);
    }

    @Override
    public boolean isPatientExist(Long doctorId) {
        return patientRepository.exists(doctorId);
    }

    @Override
    @Transactional
    public void savePatientWithDoctor(Long doctorId, Patient patientIn) {
        patientRepository.save(patientIn);
        Patient patient = this.buildPatientFromDbPatient(patientIn);
        Doctor doctor   = this.getDoctor(doctorId);
        doctor.getPatients().add(patient);
        this.saveDoctor(doctor);
    }


    // Utility methods
    @Override
    public Patient buildPatientFromDbPatient(Patient patientIn) {
        Patient patient = new Patient();

        patient.setId(patientIn.getId());
        patient.setName(patientIn.getName());
        patient.setCity(patientIn.getCity());
        patient.setPhone(patientIn.getPhone());

        return patient;
    }

    @Override
    public Patient buildPatient(String name, String city, String phone) {
        Patient patient = new Patient();

        patient.setName(name);
        patient.setCity(city);
        patient.setPhone(phone);

        return patient;
    }

    @Override
    public Doctor buildDoctorFromDbDoctor(Doctor doctorIn) {
        Doctor doctor = new Doctor();

        doctor.setId(doctorIn.getId());
        doctor.setName(doctorIn.getName());
        doctor.setHospitalName(doctorIn.getHospitalName());
        doctor.setVisitFee(doctorIn.getVisitFee());

        /*Set<Patient> patients = new HashSet<Patient>(0);
        Iterator<Patient>  iterator = doctorIn.getPatients().iterator();
        while(iterator.hasNext()){
            Patient patient = (Patient)iterator.next();
            System.out.println(patient.toString());
            patients.add(this.buildPatientFromDbPatient(patient));
        }

        doctor.setPatients(patients);*/

        return doctor;
    }

    @Override
    public Doctor buildDoctor(String name, String hospitalName, Double visitFee) {
        Doctor doctor = new Doctor();

        doctor.setName(name);
        doctor.setHospitalName(hospitalName);
        doctor.setVisitFee(visitFee);

        return doctor;
    }

}
