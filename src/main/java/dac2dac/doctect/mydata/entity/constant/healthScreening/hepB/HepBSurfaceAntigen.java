package dac2dac.doctect.mydata.entity.constant.healthScreening.hepB;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HepBSurfaceAntigen {
    STANDARD("일반"),
    PRECISION("정밀");

    private final String DiagTypeName;
}
