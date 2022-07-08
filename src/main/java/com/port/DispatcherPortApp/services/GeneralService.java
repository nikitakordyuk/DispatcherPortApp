package com.port.DispatcherPortApp.services;

import com.port.DispatcherPortApp.models.General;
import com.port.DispatcherPortApp.repositories.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GeneralService {
    private final GeneralRepository generalRepository;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public GeneralService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public List<General> generalList() {
        return generalRepository.findAll();
    }

    public General findGeneralById(long id) {
        Optional<General> general = generalRepository.findById(id);

        return general.orElse(null);
    }

    public General findByCarNumber(String carNumber) {
        Optional<General> general = Optional.ofNullable(generalRepository.findGeneralByCarNumber(carNumber));

        return general.orElse(null);
    }

    public void saveGeneral(General general) {
        general.setDateOfCreation(Timestamp.valueOf(DATE_FORMAT.format(Timestamp.from(Instant.now()))));
        generalRepository.save(general);
    }

    public void updateById(long id, General toUpdate) {
        toUpdate.setId(id);
//        toUpdate.setDateOfCreation(Timestamp.valueOf(LocalDateTime.now()));
        generalRepository.save(toUpdate);
    }

    public List<General> search(String option, String query) {
        Optional<List<General>> generals = Optional.empty();
        switch (option) {
            case "nomenclature":
                generals =
                    Optional.ofNullable(generalRepository.findGeneralsByNomenclatureIgnoreCaseStartingWith(query));
                break;
            case "trailerNumber":
                generals =
                        Optional.ofNullable(generalRepository.findGeneralsByTrailerNumberIgnoreCaseStartingWith(query));
                break;
            case "carNumber":
                generals = Optional.ofNullable(generalRepository.findGeneralsByCarNumberIgnoreCaseStartingWith(query));
                break;
            case "driverLicenseNumber":
                generals = Optional.ofNullable(generalRepository.findGeneralsByDriverLicenseNumberIgnoreCaseStartingWith(query));
                break;
            case "sender":
                generals = Optional.ofNullable(generalRepository.findGeneralsBySenderIgnoreCaseStartingWith(query));
                break;
            case "vehicleType":
                generals = Optional.ofNullable(generalRepository.findGeneralsByVehicleTypeIgnoreCaseStartingWith(query));
                break;
            case "phoneNumber":
                generals = Optional.ofNullable(generalRepository.findGeneralsByPhoneNumberIgnoreCaseStartingWith(query));
                break;
            case "fullName":
                generals = Optional.ofNullable(generalRepository.findGeneralsByFullNameIgnoreCaseStartingWith(query));
                break;
            case "dateOfCreation":
                generals = Optional.ofNullable(
                        generalRepository.findGeneralsByDateOfCreationStartingWith(Timestamp.valueOf(query)));
                break;
            case "isCome":
                generals = Optional.ofNullable(
                        generalRepository.findGeneralsByIsCome(Boolean.parseBoolean(query)));
        }

        return generals.orElse(Collections.emptyList());
    }

    public List<General> findMoreThanOneByCarNumber(String carNumber) {
        return generalRepository.findGeneralsByCarNumber(carNumber);
    }

    public void deleteById(long id) {
        generalRepository.deleteById(id);
    }


}
