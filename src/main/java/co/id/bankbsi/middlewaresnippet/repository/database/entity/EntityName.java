package co.id.bankbsi.middlewaresnippet.repository.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ToString
@Entity
public class EntityName {
    @Id
    private String Id;
    private String name;

}
