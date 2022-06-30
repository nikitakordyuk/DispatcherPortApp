package com.port.DispatcherPortApp.services;

import com.port.DispatcherPortApp.models.General;
import com.port.DispatcherPortApp.repositories.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GeneralService {
    private final GeneralRepository generalRepository;

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

    public void saveGeneral(General general) {
        generalRepository.save(general);
    }

    public void updateById(long id, General toUpdate) {
        toUpdate.setId(id);
//        toUpdate.setDateOfCreation(Timestamp.valueOf(LocalDateTime.now()));
        generalRepository.save(toUpdate);
    }

    public void deleteById(long id) {
        generalRepository.deleteById(id);
    }
}
