package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
}
