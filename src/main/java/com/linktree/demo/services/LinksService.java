package com.linktree.demo.services;

import com.linktree.demo.models.LinksModel;
import com.linktree.demo.models.UserModel; // Import UserModel
import com.linktree.demo.repositories.LinksRepository;
import com.linktree.demo.repositories.UserRepository; // Import UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinksService {

    @Autowired
    private LinksRepository linksRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    // Helper method to validate if a user exists
    private void validateUserExists(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID cannot be null or empty.");
        }
        Optional<UserModel> user = userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found.");
        }
    }

    public List<LinksModel> getAllLinks() {
        return linksRepository.findAll();
    }

    public Optional<LinksModel> getLinkById(UUID id) {
        return linksRepository.findById(id);
    }

    public LinksModel createLink(LinksModel link) {
        validateUserExists(link.getUserId()); // Validate the user ID
        return linksRepository.save(link);
    }

    public LinksModel updateLink(UUID id, LinksModel updatedLink) {
        if (!linksRepository.existsById(id)) {
            return null; // Or throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found.");
        }
        // Ensure the ID from path is used
        updatedLink.setId(id);
        validateUserExists(updatedLink.getUserId()); // Validate the user ID for update
        return linksRepository.save(updatedLink);
    }

    public boolean deleteLink(UUID id) {
        if (linksRepository.existsById(id)) {
            linksRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<LinksModel> getLinksByUserId(String userId) {
        validateUserExists(userId); // Validate the user ID before fetching links
        return linksRepository.findByUserId(userId);
    }
}