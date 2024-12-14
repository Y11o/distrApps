package app.distr.hospital.cholecystitis.repository;

import app.distr.hospital.cholecystitis.model.Cholecystitis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CholecystitisRepository extends CrudRepository<Cholecystitis, Integer> {
    @Override
    Optional<Cholecystitis> findById(Integer id);

    @Override
    List<Cholecystitis> findAll();

    List<Cholecystitis> findByHospitalNameAndComplicationsAndPatientId(String hospitalName,
                                                                      String complications,
                                                                      Integer patientId);
}
