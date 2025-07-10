package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DictionaryItemRepository extends JpaRepository<DictionaryItem, UUID> {
}
