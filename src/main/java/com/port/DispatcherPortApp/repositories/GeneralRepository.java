package com.port.DispatcherPortApp.repositories;

import com.port.DispatcherPortApp.models.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralRepository extends JpaRepository<General, Long> {
}
