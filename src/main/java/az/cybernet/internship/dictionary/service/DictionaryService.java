package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.entity.Dictionary;
import java.util.List;
import java.util.UUID;

public interface DictionaryService {

    List<Dictionary> findDictionaries(UUID id, String value, Boolean isActive, Integer limit);
}
