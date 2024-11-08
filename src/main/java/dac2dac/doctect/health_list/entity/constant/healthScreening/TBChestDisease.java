package dac2dac.doctect.health_list.entity.constant.healthScreening;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TBChestDisease {
    NORMAL("정상"),
    LATENT_TB("비활동성 폐결핵"),
    DISEASE_SUSPECTED("질환의심");

    private final String TBChestDiseaseType;

    public static TBChestDisease fromString(String text) {
        for (TBChestDisease b : TBChestDisease.values()) {
            if (b.TBChestDiseaseType.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
