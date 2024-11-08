package dac2dac.doctect.health_list.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrescriptionDrug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    private String drugName;
    private Integer prescriptionCnt;
    private Integer medicationDays;

    @Builder
    public PrescriptionDrug(Prescription prescription, String drugName, Integer prescriptionCnt, Integer medicationDays) {
        this.prescription = prescription;
        this.drugName = drugName;
        this.prescriptionCnt = prescriptionCnt;
        this.medicationDays = medicationDays;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
