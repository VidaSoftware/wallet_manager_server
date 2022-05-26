package optional.web3.vida.repository;

import optional.web3.vida.entity.LicenseCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseCodeRepository extends JpaRepository<LicenseCodeEntity, Long> {
    LicenseCodeEntity findLicenseCodeEntityByTelegramUsername(String telegramUsername);
    LicenseCodeEntity findLicenseCodeEntityByMachineId(String machineId);
}
