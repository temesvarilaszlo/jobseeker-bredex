package com.example.jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jobseeker.model.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query(value = "SELECT * FROM Position p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) AND " +
            "LOWER(p.location) LIKE LOWER(CONCAT('%', :location, '%'))", nativeQuery = true)
    List<Position> searchPositions(@Param("title") String title,
                                   @Param("location") String location);
}
