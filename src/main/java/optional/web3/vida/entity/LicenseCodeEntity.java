package optional.web3.vida.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LicenseCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String telegramUsername;

    @Column
    private String machineId;

    @Column
    private LocalDate expireDate;


    public LicenseCodeEntity() {
    }

    public LicenseCodeEntity(String telegramUsername, String machineId, LocalDate expireDate) {
        this.telegramUsername = telegramUsername;
        this.machineId = machineId;
        this.expireDate = expireDate;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }
}
