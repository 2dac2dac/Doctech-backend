package dac2dac.doctect.doctor.dto;

import dac2dac.doctect.doctor.entity.Doctor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomDoctorDetails implements UserDetails {

    private Doctor doctor;

    public CustomDoctorDetails(Doctor doctor) {
        this.doctor = doctor;
    }

    //    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 권한 로직 필요
    }

    @Override
    public String getPassword() {
        return doctor.getPassword();
    }

    @Override
    public String getUsername() {
        return doctor.getName();
    }

    public String getId() {
        return doctor.getId().toString();
    }

    public String getEmail() {
        return doctor.getEmail();
    }

    public String getOneLiner() {
        return doctor.getOneLiner();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 비즈니스 로직에 맞게 수정 필요
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 비즈니스 로직에 맞게 수정 필요
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비즈니스 로직에 맞게 수정 필요
    }

    @Override
    public boolean isEnabled() {
        return true; // 비즈니스 로직에 맞게 수정 필요
    }
}