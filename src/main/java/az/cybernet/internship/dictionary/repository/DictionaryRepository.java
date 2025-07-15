package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryEntry;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Optional;

@Mapper

public interface DictionaryRepository {

    List<DictionaryEntry> findAll();
    Optional<DictionaryEntry> findById(Long id);
    void insert(DictionaryEntry entry);
    void update(DictionaryEntry entry);
    void softDelete(Long id);
    void restore(Long id);
}
