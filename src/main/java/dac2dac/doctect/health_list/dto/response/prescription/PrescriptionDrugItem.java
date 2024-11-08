package dac2dac.doctect.health_list.dto.response.prescription;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PrescriptionDrugItem {

    private String drugName;
    private String drugThumnail;

    private Integer prescriptionCnt;
    private Integer medicationDays;

    @Builder
    public PrescriptionDrugItem(String drugName, String drugThumnail, Integer prescriptionCnt, Integer medicationDays) {
        this.drugName = drugName;
        this.drugThumnail = drugThumnail;
        this.prescriptionCnt = prescriptionCnt;
        this.medicationDays = medicationDays;
    }
}
