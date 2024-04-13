package br.com.kanban.kanban.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("FEATURE")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feature_id;

    public Long getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(Long feature_id) {
        this.feature_id = feature_id;
    }

}