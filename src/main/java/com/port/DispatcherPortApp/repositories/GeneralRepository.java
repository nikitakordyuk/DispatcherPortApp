package com.port.DispatcherPortApp.repositories;

import com.port.DispatcherPortApp.models.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface GeneralRepository extends JpaRepository<General, Long> {
    List<General> findGeneralsByNomenclatureIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByCarNumberIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByTrailerNumberIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByDriverLicenseNumberIgnoreCaseStartingWith(String query);

    List<General> findGeneralsBySenderIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByVehicleTypeIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByPhoneNumberIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByFullNameIgnoreCaseStartingWith(String query);

    List<General> findGeneralsByDateOfCreationStartingWith(Timestamp query);

    List<General> findGeneralsByIsCome(boolean query);

}
