package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Attachment;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

    Optional<Attachment> findByName(String name);
}
