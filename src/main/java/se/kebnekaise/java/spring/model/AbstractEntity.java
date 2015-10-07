package se.kebnekaise.java.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue
    Long id;

    protected AbstractEntity() {
    }


    public Long getId() {
        return id;
    }
}
