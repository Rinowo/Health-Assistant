package com.example.healthassistant.repository;

import com.example.healthassistant.model.IssuePersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssuePersonalRepository extends JpaRepository<IssuePersonal,Long> {

    @Query("SELECT ip FROM IssuePersonal ip WHERE ip.name LIKE %?1%"
//            + " OR p.brand LIKE %?1%"
//            + " OR p.madein LIKE %?1%"
//            + " OR CONCAT(p.price, '') LIKE %?1%"
    )
    List<IssuePersonal> search(String keyword);

    List<IssuePersonal> findAllByCategoryId(Long id);
}
