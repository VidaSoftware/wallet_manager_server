package optional.web3.vida.service;

import optional.web3.vida.entity.LicenseCodeEntity;
import optional.web3.vida.repository.LicenseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LicenseCodeService {

    private final LicenseCodeRepository licenseCodeRepository;

    @Autowired
    public LicenseCodeService(LicenseCodeRepository licenseCodeRepository) {
        this.licenseCodeRepository = licenseCodeRepository;
    }

    public boolean checkLicense(String telegramUsername, String machineId) {
        LicenseCodeEntity licenseCodeEntity = licenseCodeRepository.findLicenseCodeEntityByTelegramUsername(telegramUsername);
        if (licenseCodeEntity != null
                && licenseCodeEntity.getMachineId().equals(machineId)
                && licenseCodeEntity.getExpireDate().isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }

    public boolean createLicense(String telegramUsername, String machineId, LocalDate expireDate) {
        if (licenseCodeRepository.findLicenseCodeEntityByTelegramUsername(telegramUsername) == null
                && licenseCodeRepository.findLicenseCodeEntityByMachineId(machineId) == null) {
            LicenseCodeEntity licenseCodeEntity = new LicenseCodeEntity(telegramUsername, machineId, expireDate);
            licenseCodeRepository.save(licenseCodeEntity);
        } else {
            LicenseCodeEntity licenseCodeEntity = licenseCodeRepository.findLicenseCodeEntityByTelegramUsername(telegramUsername);
            if (!licenseCodeEntity.getMachineId().equals(machineId)) {
                return false;
            }
            licenseCodeEntity.setExpireDate(expireDate);
            licenseCodeRepository.save(licenseCodeEntity);
        }
        return true;
    }

    public boolean changeMachineId(String telegramUsername, String machineId) {
        LicenseCodeEntity licenseCodeEntity = licenseCodeRepository.findLicenseCodeEntityByTelegramUsername(telegramUsername);
        if (licenseCodeEntity == null) {
            return false;
        }

        licenseCodeEntity.setMachineId(machineId);
        licenseCodeRepository.save(licenseCodeEntity);
        return true;
    }
}
