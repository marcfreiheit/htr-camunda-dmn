package de.marcfreiheit.htr.conditiontables.conditiontables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConditionTableRepository extends JpaRepository<ConditionTable, UUID> {
}
