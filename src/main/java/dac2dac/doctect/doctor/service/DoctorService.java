package dac2dac.doctect.doctor.service;

import dac2dac.doctect.common.constant.ErrorCode;
import dac2dac.doctect.common.error.exception.NotFoundException;
import dac2dac.doctect.doctor.dto.response.AcceptedReservationItemList;
import dac2dac.doctect.doctor.dto.response.RequestReservationFormDto;
import dac2dac.doctect.doctor.dto.response.RequestReservationItemList;
import dac2dac.doctect.doctor.dto.response.ReservationItem;
import dac2dac.doctect.doctor.dto.response.ReservationListDto;
import dac2dac.doctect.doctor.entity.Doctor;
import dac2dac.doctect.doctor.repository.DoctorRepository;
import dac2dac.doctect.noncontact_diag.dto.response.NoncontactDiagFormInfo;
import dac2dac.doctect.noncontact_diag.entity.NoncontactDiagReservation;
import dac2dac.doctect.noncontact_diag.entity.Symptom;
import dac2dac.doctect.noncontact_diag.entity.constant.ReservationStatus;
import dac2dac.doctect.noncontact_diag.repository.NoncontactDiagReservationRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {

    private final NoncontactDiagReservationRepository noncontactDiagReservationRepository;
    private final DoctorRepository doctorRepository;

    public ReservationListDto getReservations(Long doctorId, String reservationDate) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.DOCTOR_NOT_FOUND));

        // 날짜에 해당하면서 doctor id에 해당하는 예약 조회
        LocalDate localReservationDate = LocalDate.parse(reservationDate);
        List<NoncontactDiagReservation> noncontactDiagReservationList = noncontactDiagReservationRepository.findByDoctorIdAndReservationDate(doctorId, localReservationDate)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NONCONTACT_DIAGNOSIS_RESERVATION_NOT_FOUND));

        // 요청된 예약: SIGN_UP 상태인 예약 필터링
        List<NoncontactDiagReservation> requestReservations = noncontactDiagReservationList.stream()
            .filter(reservation -> ReservationStatus.SIGN_UP.equals(reservation.getStatus()))
            .collect(Collectors.toList());

        List<ReservationItem> requestReservationItems = requestReservations.stream()
            .map(r -> ReservationItem.builder()
                .reservationId(r.getId())
                .signupDate(r.getCreateDate())
                .patientName(r.getUser().getUsername())
                .reservationDate(LocalDateTime.of(r.getReservationDate(), r.getReservationTime()))
                .build())
            .sorted(Comparator.comparing(ReservationItem::getSignupDate))
            .collect(Collectors.toList());

        RequestReservationItemList requestReservationItemList = RequestReservationItemList.builder()
            .totalCnt(requestReservationItems.size())
            .requestReservationItemList(requestReservationItems)
            .build();

        // 수락된 예약: COMPLETE 상태인 예약 필터링
        List<NoncontactDiagReservation> acceptedReservations = noncontactDiagReservationList.stream()
            .filter(reservation -> ReservationStatus.COMPLETE.equals(reservation.getStatus()))
            .collect(Collectors.toList());

        List<ReservationItem> acceptedReservationItems = acceptedReservations.stream()
            .map(r -> ReservationItem.builder()
                .reservationId(r.getId())
                .signupDate(r.getCreateDate())
                .patientName(r.getUser().getUsername())
                .reservationDate(LocalDateTime.of(r.getReservationDate(), r.getReservationTime()))
                .build())
            .sorted(Comparator.comparing(ReservationItem::getReservationDate))
            .collect(Collectors.toList());

        AcceptedReservationItemList acceptedReservationItemList = AcceptedReservationItemList.builder()
            .totalCnt(acceptedReservationItems.size())
            .acceptedReservationItemList(acceptedReservationItems)
            .build();

        return ReservationListDto.builder()
            .requestReservationList(requestReservationItemList)
            .acceptedReservationList(acceptedReservationItemList)
            .build();
    }

    public RequestReservationFormDto getReservationForm(Long doctorId, Long reservationId) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.DOCTOR_NOT_FOUND));

        NoncontactDiagReservation findNoncontactDiagReservation = noncontactDiagReservationRepository.findById(reservationId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NONCONTACT_DIAGNOSIS_RESERVATION_NOT_FOUND));

        //* 예약 헤더 정보
        ReservationItem reservationItem = ReservationItem.builder()
            .reservationId(findNoncontactDiagReservation.getId())
            .signupDate(findNoncontactDiagReservation.getCreateDate())
            .patientName(findNoncontactDiagReservation.getUser().getUsername())
            .reservationDate(LocalDateTime.of(findNoncontactDiagReservation.getReservationDate(), findNoncontactDiagReservation.getReservationTime()))
            .build();

        Symptom findSymptom = findNoncontactDiagReservation.getSymptom();

        //* 예약 신청 세부 정보
        NoncontactDiagFormInfo noncontactDiagFormInfo = NoncontactDiagFormInfo.builder()
            .signupDate(findNoncontactDiagReservation.getCreateDate())
            .reservationDate(LocalDateTime.of(findNoncontactDiagReservation.getReservationDate(), findNoncontactDiagReservation.getReservationTime()))
            .department(doctor.getDepartment().getDepartmentName())
            .diagType(findNoncontactDiagReservation.getDiagType().getNoncontactDiagTypeName())
            .isPrescribedDrug(findSymptom.getIsPrescribedDrug())
            .prescribedDrug(findSymptom.getPrescribedDrug())
            .isAllergicSymptom(findSymptom.getIsAllergicSymptom())
            .allergicSymptom(findSymptom.getAllergicSymptom())
            .isInbornDisease(findSymptom.getIsInbornDisease())
            .inbornDisease(findSymptom.getInbornDisease())
            .additionalInformation(findSymptom.getAdditionalInformation())
            .build();

        return RequestReservationFormDto.builder()
            .reservationItem(reservationItem)
            .noncontactDiagFormInfo(noncontactDiagFormInfo)
            .build();
    }
}
