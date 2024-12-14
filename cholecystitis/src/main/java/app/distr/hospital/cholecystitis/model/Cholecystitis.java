package app.distr.hospital.cholecystitis.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "cholecystitis")
public class Cholecystitis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "patient_id")
    private Integer patientId;

    /** Тип лечения: медикаментозное, хирургическое */
    @Column(name = "treatment_type")
    private String treatmentType;

    /** Наличие камней в желчном пузыре */
    @Column(name = "has_gallstones")
    private boolean hasGallstones;

    /** Тип формы заболевания: острое или хроническое */
    @Column(name = "severity")
    private String severity;

    /** Наличие инфекции */
    @Column(name = "is_infected")
    private boolean isInfected;

    /** Осложнения холецистита: эмпиема, гангрена, прободение стенки желчного пузыря, перитонит, свищи,
     *  воспаление поджелудочной железы, желчных протоков, желтуха */
    @Column(name = "complications")
    private String complications;

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getId() {
        return this.id;
    }
}
