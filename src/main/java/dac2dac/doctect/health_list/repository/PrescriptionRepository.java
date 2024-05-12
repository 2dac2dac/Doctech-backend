package dac2dac.doctect.health_list.repository;

import dac2dac.doctect.health_list.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    void deleteByUserId(Long userId);
}
