package app.distr.hospital.cholecystitis.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private String message;

    private Long timestamp;
}

