package az.cybernet.internship.dictionary.dao.repository;

import az.cybernet.internship.dictionary.dao.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.model.enums.DictionaryStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DictionaryRepository extends CrudRepository<DictionaryEntity, Long> {

    Optional<DictionaryEntity> findByIdAndStatusNot(Long id, DictionaryStatus status);

}