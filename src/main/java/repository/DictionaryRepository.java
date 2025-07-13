package repository;


import mapper.IDictionaryMapper;
import model.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DictionaryRepository {
    private final IDictionaryMapper mapper;

    public DictionaryRepository(IDictionaryMapper mapper) {
        this.mapper = mapper;
    }

    public List<Dictionary> findAll(String id, String value, Boolean isActive, Integer limit) {
        return mapper.findAll(id, value, isActive, limit);
    }

    public Dictionary findById(String id) {
        return mapper.findById(id);
    }

    public void insert(Dictionary dictionary) {
        mapper.insert(dictionary);
    }

    public void update(Dictionary dictionary) {
        mapper.update(dictionary);
    }

    public void softDelete(String id, String timestamp) {
        mapper.softDelete(id, timestamp);
    }

    public void restore(String id) {
        mapper.restore(id);
    }
}
