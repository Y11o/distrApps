package app.distr.hospital.cholecystitis.service;

import app.distr.hospital.cholecystitis.model.Cholecystitis;
import app.distr.hospital.cholecystitis.repository.CholecystitisRepository;
import app.distr.hospital.cholecystitis.util.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class CholecystitisService {

    private final CholecystitisRepository cholecystitisRepository;

    private final MessageSource messages;

    @Autowired
    public CholecystitisService(CholecystitisRepository cholecystitisRepository, MessageSource messages) {
        this.cholecystitisRepository = cholecystitisRepository;
        this.messages = messages;
    }

    public String createCholecystitis(Cholecystitis request, String hospitalName, Locale locale) {
        request.setHospitalName(hospitalName);

        return String.format(messages.getMessage("cholecystitis.create.message", null, locale),
                this.save(request).toString());
    }

    public String updateCholecystitis(Cholecystitis request, Locale locale) {
        Cholecystitis cholecystitisToUpdate = cholecystitisRepository.findById(request.getId()).orElseThrow(
                () -> new ItemNotFoundException(String.format(
                        messages.getMessage("cholecystitis.not_found.message", null, locale), request.getId()))
        );
        return String.format(messages.getMessage("cholecystitis.update.message", null, locale),
                this.save(request).toString());
    }

    public List<Cholecystitis> getAll() {
        return cholecystitisRepository.findAll();
    }

    public List<Cholecystitis> getByHospitalNameAndComplicationsAndPatientId(String hospitalName,
                                                                            String complications, Integer patientId) {
        return cholecystitisRepository.findByHospitalNameAndComplicationsContainingAndPatientId(
                hospitalName, complications, patientId
        );
    }

    public String delete(Integer id, Locale locale) {
        Cholecystitis cholecystitis = cholecystitisRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(String.format(
                        messages.getMessage("cholecystitis.not_found.message", null, locale), id)));

        String responseMsg = String.format(messages.getMessage("cholecystitis.delete.message", null, locale),
                cholecystitis.getId(), cholecystitis.getHospitalName());

        cholecystitisRepository.deleteById(id);
        return responseMsg;

    }

    private Cholecystitis save(Cholecystitis cholecystitis) {
        return cholecystitisRepository.save(cholecystitis);
    }
}
