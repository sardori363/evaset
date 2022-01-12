package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
