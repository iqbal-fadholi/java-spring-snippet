package co.id.bankbsi.middlewaresnippet.repository.database.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.bankbsi.middlewaresnippet.repository.database.entity.EntityName;

public interface ExampleJpaRepository extends JpaRepository<EntityName, String> {
}
