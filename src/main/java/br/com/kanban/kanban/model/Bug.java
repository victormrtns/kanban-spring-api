package br.com.kanban.kanban.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("BUG")
public class Bug extends Card {
    private Long bug_id;

    public Long getBug_id() {
        return bug_id;
    }

    public void setBug_id(Long bug_id) {
        this.bug_id = bug_id;
    }

}
