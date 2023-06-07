package com.olekhv.socialnetwork.repository;

import com.olekhv.socialnetwork.model.attachment.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, String> {
}
