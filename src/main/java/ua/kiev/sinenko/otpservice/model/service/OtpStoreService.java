package ua.kiev.sinenko.otpservice.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.sinenko.otpservice.error.NoChellangeException;
import ua.kiev.sinenko.otpservice.model.entity.OtpEntity;
import ua.kiev.sinenko.otpservice.model.repository.OtpRepository;

import java.util.UUID;

@Service
public class OtpStoreService {
    private Logger logger = LoggerFactory.getLogger(OtpStoreService.class);

    private OtpRepository otpRepository;

    public OtpStoreService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Transactional(readOnly = true)
    public OtpEntity findById(UUID uuid) throws NoChellangeException {
        return otpRepository.findById(uuid).orElseThrow(() -> new NoChellangeException("No record with chellangeid " + uuid));
    }

    @Transactional
    public OtpEntity save(OtpEntity otpEntity) {
        return otpRepository.saveAndFlush(otpEntity);
    }
}
