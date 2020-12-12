package ua.kiev.sinenko.otpservice.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.sinenko.otpservice.model.entity.AccessUserEntity;
import ua.kiev.sinenko.otpservice.model.repository.AccessUserRepository;

import java.util.List;
import java.util.UUID;


@Service("accessUserService")
@Transactional
public class AccessUserService {
    private Logger logger = LoggerFactory.getLogger(AccessUserService.class);

    AccessUserRepository accessUserRepository;

    public AccessUserService(AccessUserRepository accessUserRepository) {
        this.accessUserRepository = accessUserRepository;
    }

    @Transactional(readOnly = true)
    public List<AccessUserEntity> findAll() {
        return accessUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AccessUserEntity findById(UUID id)  throws Throwable {
        return accessUserRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public AccessUserEntity findByLogin(String login)  throws Throwable {
        return accessUserRepository.findByLogin(login).orElse(null);
    }

    @Transactional
    public AccessUserEntity save(AccessUserEntity accessUser) {
        return accessUserRepository.save(accessUser);
    }
}
