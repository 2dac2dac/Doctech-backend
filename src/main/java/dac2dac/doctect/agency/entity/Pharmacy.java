package dac2dac.doctect.agency.entity;

import dac2dac.doctect.agency.entity.constant.AgencyType;
import dac2dac.doctect.common.entity.DiagTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pharmacy extends Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fax;

    @Builder
    public Pharmacy(String name, String address, String tel, String fax, String thumnail, Double longitude, Double latitude, Integer diagTimeMonOpen, Integer diagTimeMonClose,
        Integer diagTimeTuesOpen,
        Integer diagTimeTuesClose, Integer diagTimeWedsOpen, Integer diagTimeWedsClose, Integer diagTimeThursOpen, Integer diagTimeThursClose, Integer diagTimeFriOpen, Integer diagTimeFriClose,
        Integer diagTimeSatOpen, Integer diagTimeSatClose, Integer diagTimeSunOpen, Integer diagTimeSunClose, Integer diagTimeHolidayOpen, Integer diagTimeHolidayClose) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.fax = fax;
        this.thumnail = thumnail;
        this.longitude = longitude;
        this.latitude = latitude;
        this.diagTime = new DiagTime(diagTimeMonOpen, diagTimeMonClose, diagTimeTuesOpen, diagTimeTuesClose, diagTimeWedsOpen, diagTimeWedsClose,
            diagTimeThursOpen, diagTimeThursClose, diagTimeFriOpen, diagTimeFriClose, diagTimeSatOpen, diagTimeSatClose,
            diagTimeSunOpen, diagTimeSunClose, diagTimeHolidayOpen, diagTimeHolidayClose);
    }

    @Override
    public AgencyType getAgencyType() {
        return AgencyType.PHARMACY;
    }

    @Override
    public Long getId() {
        return id;
    }
}
