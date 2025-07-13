package com.linktree.demo.repositories; // Adjust package as needed

import com.linktree.demo.models.LinksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LinksRepository extends JpaRepository<LinksModel, UUID> {
    List<LinksModel> findByUserId(String userId); // <--- This is the problematic method
}