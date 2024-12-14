package app.distr.hospital.cholecystitis.controller;

import app.distr.hospital.cholecystitis.model.Cholecystitis;
import app.distr.hospital.cholecystitis.service.CholecystitisService;
import app.distr.hospital.cholecystitis.util.ErrorResponse;
import app.distr.hospital.cholecystitis.util.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hospitals/{hospitalName}/cholecystitis")
public class CholecystitisController {

    private final CholecystitisService cholecystitisService;

    @Autowired
    public CholecystitisController(CholecystitisService cholecystitisService) {
        this.cholecystitisService = cholecystitisService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cholecystitisService.getAll());
    }

    @GetMapping("/{patientId}/{complication}")
    public ResponseEntity<?> getCholecystitis(@PathVariable("hospitalName") String hospitalName,
                                              @PathVariable("patientId") Integer patientId,
                                              @PathVariable("complication") String complications) {
        return ResponseEntity.ok(cholecystitisService.getByHospitalNameAndComplicationsAndPatientId(
                hospitalName, complications, patientId
        ));
    }

    @PostMapping
    public ResponseEntity<?> createCholecystitis(@PathVariable("hospitalName") String hospitalName,
                                                 @RequestBody Cholecystitis request) {
        return ResponseEntity.ok(cholecystitisService.createCholecystitis(request, hospitalName));
    }

    @PutMapping
    public ResponseEntity<?> updateCholecystitis(@RequestBody Cholecystitis request) {
        return ResponseEntity.ok(cholecystitisService.updateCholecystitis(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(cholecystitisService.delete(id));
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException e) {
        ErrorResponse response = new ErrorResponse.Builder()
                .setMessage(e.getMessage())
                .setTimestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
