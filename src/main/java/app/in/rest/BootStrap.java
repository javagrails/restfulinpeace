package app.in.rest;

import app.in.rest.entity.Doctor;
import app.in.rest.entity.Patient;
import app.in.rest.repository.DoctorRepository;
import app.in.rest.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName BootStrap.java | app.in.rest | BootStrap
 * @since Jul 19, 2017 15:52:52 PM
 */


@Service
public class BootStrap implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(BootStrap.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        log.info("\t\t\t\t\t\t\t\t\t\t====================== BootStrap Starts =========================");
        initDataForDoctorAndPatient();
        log.info("\t\t\t\t\t\t\t\t\t\t====================== BootStrap Ends =========================");
    }

    private void initDataForDoctorAndPatient() {
        log.info("\t\t\t\t\t\t\t\t\t\t====================== initDataForDoctorAndPatient Starts =========================");
        long countDoctor  = doctorRepository.count();
        long countPatient = patientRepository.count();
        if(countDoctor > 0 || countPatient > 0){
            log.info("System already have some Doctor("+countDoctor+") and Patient("+countPatient+")");
            return;
        }

        Set<Patient> patientsOfDoctor1 = new HashSet<Patient>();
        patientsOfDoctor1.add(this.buildPatient("Salman1","Dhaka", "130"));
        patientsOfDoctor1.add(this.buildPatient("Saleh1","Tangail", "768"));
        Doctor doctor1 = this.buildDoctor("Ali","DMC", 60.6);
        doctor1.setPatients(patientsOfDoctor1);


        Set<Patient> patientsOfDoctor2 = new HashSet<Patient>();
        patientsOfDoctor2.add(this.buildPatient("Salman2","Dhaka", "553"));
        patientsOfDoctor2.add(this.buildPatient("Saleh2","Tangail", "135"));
        Doctor doctor2 = this.buildDoctor("Raz","MMC", 490.5);
        doctor2.setPatients(patientsOfDoctor2);


        doctorRepository.saveAndFlush(doctor1);
       /*Hibernate: insert into `doctor` (`hospital_name`, `name`, `visit_fee`) values (?, ?, ?)
       Hibernate: insert into `patient` (`city`, `name`, `phone`) values (?, ?, ?)
       Hibernate: insert into `patient` (`city`, `name`, `phone`) values (?, ?, ?)
       Hibernate: insert into `doctor_patient` (`doctor_id`, `patient_id`) values (?, ?)
       Hibernate: insert into `doctor_patient` (`doctor_id`, `patient_id`) values (?, ?)*/
        doctorRepository.saveAndFlush(doctor2);
        log.info("\t\t\t\t\t\t\t\t\t\t====================== initDataForDoctorAndPatient Ends =========================");

    }


    /************ Utility methods for BootStrap ***************/

    private Patient buildPatient(String name,String city,String phone) {
        Patient patient = new Patient();

        patient.setName(name);
        patient.setCity(city);
        patient.setPhone(phone);

        return patient;
    }

    private Doctor buildDoctor(String name,String hospitalName,Double visitFee) {
        Doctor doctor = new Doctor();

        doctor.setName(name);
        doctor.setHospitalName(hospitalName);
        doctor.setVisitFee(visitFee);

        return doctor;
    }


    public String randomString() {
        Random random = new Random();
        char random_3_Char = (char) (48 + random.nextInt(47));
        return String.valueOf(random_3_Char);
    }
}
