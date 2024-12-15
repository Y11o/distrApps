package app.distr.hospital.cholecystitis.util;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String message;

    private Long timestamp;
}

