package app.distr.hospital.cholecystitis.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "cholecystitis")
public class Cholecystitis extends RepresentationModel<Cholecystitis> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    /** Тип лечения: медикаментозное, хирургическое */
    @Column(name = "treatment_type", nullable = false)
    private String treatmentType;

    /** Наличие камней в желчном пузыре */
    @Column(name = "has_gallstones", nullable = false)
    private boolean hasGallstones;

    /** Тип формы заболевания: острое или хроническое */
    @Column(name = "severity", nullable = false)
    private String severity;

    /** Наличие инфекции */
    @Column(name = "is_infected", nullable = false)
    private boolean isInfected;

    /** Осложнения холецистита: эмпиема, гангрена, прободение стенки желчного пузыря, перитонит, свищи,
     *  воспаление поджелудочной железы, желчных протоков, желтуха */
    @Column(name = "complications", nullable = false)
    private String complications;

    private String comment;

    public Cholecystitis withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
