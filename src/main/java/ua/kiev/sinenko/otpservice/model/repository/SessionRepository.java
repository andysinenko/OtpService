package ua.kiev.sinenko.otpservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.sinenko.otpservice.model.entity.OtpEntity;
import ua.kiev.sinenko.otpservice.model.entity.SessionEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {
    Optional<SessionEntity> findById(UUID uuid);
}
