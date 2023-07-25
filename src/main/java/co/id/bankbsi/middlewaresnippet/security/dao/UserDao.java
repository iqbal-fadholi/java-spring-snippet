package co.id.bankbsi.middlewaresnippet.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.id.bankbsi.middlewaresnippet.security.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String userName);
    
}
