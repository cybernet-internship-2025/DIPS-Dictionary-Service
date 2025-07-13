package az.cybernet.internship.dictionary.repository;


import az.cybernet.internship.dictionary.mapper.IDictionaryMapper;
import az.cybernet.internship.dictionary.model.DictionaryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class DictionaryRepository {
    private final IDictionaryMapper mapper;

    public DictionaryRepository(IDictionaryMapper mapper) {
        this.mapper = mapper;
    }

    public List<DictionaryEntity> getDictionaries(String id, String value, Boolean isActive, Integer limit) {
        return mapper.getDictionaries(id, value, isActive, limit);
    }
//
//    public DictionaryEntity findById(String id) {
//        return az.cybernet.internship.dictionary.mapper.findById(id);
//    }
//
//    public void insert(DictionaryEntity dictionaryEntity) {
//        az.cybernet.internship.dictionary.mapper.insert(dictionaryEntity);
//    }
//
//    public void update(DictionaryEntity dictionaryEntity) {
//        az.cybernet.internship.dictionary.mapper.update(dictionaryEntity);
//    }
//
//    public void softDelete(String id, String timestamp) {
//        az.cybernet.internship.dictionary.mapper.softDelete(id, timestamp);
//    }
//
//    public void restore(String id) {
//        az.cybernet.internship.dictionary.mapper.restore(id);
//    }
}
