package app.distr.hospital.cholecystitis.util;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String message;

    private Long timestamp;

    private ErrorResponse(Builder builder) {
        this.message = builder.message;
        this.timestamp = builder.timestamp;
    }

    public static class Builder {
        private String message;
        private Long timestamp;

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}

