package com.olekhv.socialnetwork.repository;

import com.olekhv.socialnetwork.model.userCredential.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
    Optional<UserCredential> findByEmail(String email);
}
