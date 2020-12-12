package ua.kiev.sinenko.otpservice.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.sinenko.otpservice.model.entity.AccessUserEntity;
import ua.kiev.sinenko.otpservice.model.entity.SessionEntity;
import ua.kiev.sinenko.otpservice.model.repository.AccessUserRepository;
import ua.kiev.sinenko.otpservice.model.repository.SessionRepository;

import javax.mail.Session;
import java.util.List;
import java.util.UUID;


@Transactional
@Service
public class SessionService {
    private Logger logger = LoggerFactory.getLogger(SessionService.class);

    SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional(readOnly = true)
    public SessionEntity findById(UUID id)  throws Throwable {
        return sessionRepository.findById(id).orElse(null);
    }

    @Transactional
    public SessionEntity save(SessionEntity sessionEntity) {
        return sessionRepository.save(sessionEntity);
    }
}
