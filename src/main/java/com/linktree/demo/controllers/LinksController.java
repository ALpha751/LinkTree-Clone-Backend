package com.linktree.demo.controllers;

import com.linktree.demo.models.LinksModel;
import com.linktree.demo.services.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException; // Import this if you want to catch specific exceptions in controller

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/links")
public class LinksController {

    @Autowired
    private LinksService linksService;

    @GetMapping
    public ResponseEntity<List<LinksModel>> getAllLinks() {
        List<LinksModel> links = linksService.getAllLinks();
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinksModel> getLinkById(@PathVariable UUID id) {
        Optional<LinksModel> link = linksService.getLinkById(id);
        return link.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<LinksModel> createLink(@RequestBody LinksModel link) {
        try {
            LinksModel createdLink = linksService.createLink(link);
            return new ResponseEntity<>(createdLink, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            // Spring will automatically map ResponseStatusException to HTTP status
            // You can add more specific logging here if needed
            throw e; // Re-throw to let Spring handle it
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinksModel> updateLink(@PathVariable UUID id, @RequestBody LinksModel link) {
        try {
            LinksModel updatedLink = linksService.updateLink(id, link);
            if (updatedLink != null) {
                return new ResponseEntity<>(updatedLink, HttpStatus.OK);
            }
            // If service returns null for not found, throw explicit exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found for update.");
        } catch (ResponseStatusException e) {
            throw e; // Re-throw to let Spring handle it
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable UUID id) {
        boolean deleted = linksService.deleteLink(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LinksModel>> getLinksByUserId(@PathVariable String userId) {
        try {
            List<LinksModel> links = linksService.getLinksByUserId(userId);
            if (!links.isEmpty()) {
                return new ResponseEntity<>(links, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Or HttpStatus.NOT_FOUND if you prefer
        } catch (ResponseStatusException e) {
            throw e; // Re-throw to let Spring handle it
        }
    }
}