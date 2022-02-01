//package uz.pdp.springsecurity.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import uz.pdp.springsecurity.entity.User;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User, Integer> {
//
//    boolean existsByUsername(String username);
//    Optional<User> findByUsername(String username);
//    boolean existsByUsernameAndIdNot(String userName, Integer id);
//    List<User> findAllByRole_Id(Integer role_id);
//
//
////    -------------------------------------------------------------------//
//
//    List<User> findAllByBusiness_Id(Integer business_id);
//    Optional<User> findByBusiness_IdAndId(Integer business_id, Integer id);
//    @Query(value = "delete from users u where u.business_id = ?1 and id = ?2",nativeQuery = true)
//    void deleteByBusiness_IdAndId(Integer business_id, Integer id);
//
//    List<User> findAllByBranch_Id(Integer branch_id);
//    Optional<User> findByBranch_IdAndId(Integer branch_id, Integer id);
//
//    List<User> findAllByBusiness_IdAndBranch_Id(Integer business_id, Integer branch_id);
//    Optional<User> findByBusiness_IdAndBranch_IdAndId(Integer business_id, Integer branch_id, Integer id);
}
