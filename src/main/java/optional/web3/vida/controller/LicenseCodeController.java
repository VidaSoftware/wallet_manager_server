package optional.web3.vida.controller;

import optional.web3.vida.service.LicenseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/api")
public class LicenseCodeController {

    private final LicenseCodeService licenseCodeService;

    @Autowired
    public LicenseCodeController(LicenseCodeService licenseCodeService) {
        this.licenseCodeService = licenseCodeService;
    }

    @GetMapping("/license")
    public ResponseEntity checkLicense(@RequestParam String telegramUsername, @RequestParam String machineId) {
        if (licenseCodeService.checkLicense(telegramUsername, machineId)) {
            return ResponseEntity.ok(200);
        }
        return ResponseEntity.badRequest().body(400);
    }

    @GetMapping("/createLicenseMonth")
    public ResponseEntity createLicenseMonth(@RequestParam String telegramUsername, @RequestParam String machineId) {
        if (licenseCodeService.createLicense(telegramUsername, machineId, LocalDate.now().plusMonths(1))){
            return ResponseEntity.ok(200);
        }
        return ResponseEntity.badRequest().body(400);
    }

    @GetMapping("/createLicenseForever")
    public ResponseEntity createLicenseForever(@RequestParam String telegramUsername, @RequestParam String machineId) {
        if (licenseCodeService.createLicense(telegramUsername, machineId, LocalDate.now().plusYears(69))){
            return ResponseEntity.ok(200);
        }
        return ResponseEntity.badRequest().body(400);
    }

    @GetMapping("/changeMachineId")
    public ResponseEntity changeMachineId(@RequestParam String telegramUsername, @RequestParam String machineId) {
        if (licenseCodeService.changeMachineId(telegramUsername, machineId)) {
            return ResponseEntity.ok(200);
        }
        return ResponseEntity.badRequest().body(400);
    }
}
