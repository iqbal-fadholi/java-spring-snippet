package co.id.bankbsi.middlewaresnippet.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.id.bankbsi.middlewaresnippet.security.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long>{

	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role findRoleByName(String theRoleName);
	
}
