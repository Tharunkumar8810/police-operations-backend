package com.example.Police.repository;

import com.example.Police.model.Location;
import com.example.Police.model.Patrol;
import com.example.Police.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByOfficerAndPatrolOrderByTimestampDesc(User officer, Patrol patrol);
    List<Location> findByPatrolOrderByTimestampDesc(Patrol patrol);
    Optional<Location> findTopByOfficerAndPatrolOrderByTimestampDesc(User officer, Patrol patrol);
}