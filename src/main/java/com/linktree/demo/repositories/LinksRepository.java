package com.linktree.demo.repositories;

import com.linktree.demo.models.LinksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinksRepository extends JpaRepository<LinksModel, UUID> {
}
