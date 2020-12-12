package ua.kiev.sinenko.otpservice.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.sinenko.otpservice.model.entity.AccessUserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessUserRepository extends JpaRepository<AccessUserEntity, UUID> {
    public Optional<AccessUserEntity> findByLogin(String login);
}
