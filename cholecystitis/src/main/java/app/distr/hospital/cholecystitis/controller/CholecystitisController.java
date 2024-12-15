package app.distr.hospital.cholecystitis.controller;

import app.distr.hospital.cholecystitis.model.Cholecystitis; import app.distr.hospital.cholecystitis.service.CholecystitisService; import app.distr.hospital.cholecystitis.util.ErrorResponse; import app.distr.hospital.cholecystitis.util.ItemNotFoundException; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController @RequestMapping(value = "/hospitals/{hospitalName}/cholecystitis") public class CholecystitisController {

    private final CholecystitisService cholecystitisService;

    @Autowired
    public CholecystitisController(CholecystitisService cholecystitisService) {
        this.cholecystitisService = cholecystitisService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestHeader(value = "Accept-Language",required = false,
                    defaultValue = "en") String locale) {
        return ResponseEntity.ok(cholecystitisService.getAll(parseLocale(locale)));
    }

    @GetMapping("/{patientId}/{complication}")
    public ResponseEntity<?> getCholecystitis(@PathVariable("hospitalName") String hospitalName,
                                              @PathVariable("patientId") Integer patientId,
                                              @PathVariable("complication") String complications,
                                              @RequestHeader(value = "Accept-Language",required = false,
                                                      defaultValue = "en") String locale) {
        return ResponseEntity.ok(cholecystitisService.getByHospitalNameAndComplicationsAndPatientId(
                hospitalName, complications, patientId, parseLocale(locale)
        ));
    }

    @PostMapping
    public ResponseEntity<?> createCholecystitis(@PathVariable("hospitalName") String hospitalName,
                                                 @RequestBody Cholecystitis request,
                                                 @RequestHeader(value = "Accept-Language",required = false,
                                                         defaultValue = "en") String locale) {
        return ResponseEntity.ok(cholecystitisService.createCholecystitis(request, hospitalName, parseLocale(locale)));
    }

    @PutMapping
    public ResponseEntity<?> updateCholecystitis(@RequestBody Cholecystitis request,
                                                 @PathVariable("hospitalName") String hospitalName,
                                                 @RequestHeader(value = "Accept-Language",required = false,
                                                         defaultValue = "en") String locale) {
        return ResponseEntity.ok(cholecystitisService.updateCholecystitis(request, parseLocale(locale)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        @PathVariable("hospitalName") String hospitalName,
                                        @RequestHeader(value = "Accept-Language",required = false,
                                                defaultValue = "en") String locale) {
        return ResponseEntity.ok(cholecystitisService.delete(id, parseLocale(locale)));
    }

    private Locale parseLocale(String sLocale) {
        if(sLocale.substring(0, 2).toLowerCase().contains("ru")) return new Locale("rus");
        return new Locale(sLocale.substring(0, 2));
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}