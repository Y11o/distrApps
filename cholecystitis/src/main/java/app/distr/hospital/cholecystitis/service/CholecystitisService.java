package app.distr.hospital.cholecystitis.service;

import app.distr.hospital.cholecystitis.model.Cholecystitis;
import app.distr.hospital.cholecystitis.repository.CholecystitisRepository;
import app.distr.hospital.cholecystitis.util.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CholecystitisService {

    private final CholecystitisRepository cholecystitisRepository;

    @Autowired
    public CholecystitisService(CholecystitisRepository cholecystitisRepository) {
        this.cholecystitisRepository = cholecystitisRepository;
    }

    public String createCholecystitis(Cholecystitis request, String hospitalName) {
        request.setHospitalName(hospitalName);

        return String.format("This is the post and the object is: %s",
                this.save(request).toString());
    }

    public String updateCholecystitis(Cholecystitis request) {
        Cholecystitis cholecystitisToUpdate = cholecystitisRepository.findById(request.getId()).orElseThrow(
                () -> new ItemNotFoundException("Сholecystitis with id: " + request.getId() + " was not found!")
        );
        return String.format("This is the put and the object is: %s",
                this.save(request).toString());
    }

    public List<Cholecystitis> getAll() {
        return cholecystitisRepository.findAll();
    }

    public List<Cholecystitis> getByHospitalNameAndComplicationsAndPatientId(String hospitalName,
                                                                            String complications, Integer patientId) {
        return cholecystitisRepository.findByHospitalNameAndComplicationsAndPatientId(
                hospitalName, complications, patientId
        );
    }

    public String delete(Integer id) {
        String responseMsg = String.format("This is the delete and the object is: %s",
                cholecystitisRepository.findById(id).orElseThrow(
                        () -> new ItemNotFoundException("Сholecystitis with id: " + id + " was not found!")
                ).toString());

        cholecystitisRepository.deleteById(id);
        return responseMsg;

    }

    private Cholecystitis save(Cholecystitis cholecystitis) {
        return cholecystitisRepository.save(cholecystitis);
    }
}
