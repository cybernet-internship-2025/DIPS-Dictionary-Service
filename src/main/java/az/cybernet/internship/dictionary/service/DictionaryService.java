package az.cybernet.internship.dictionary.service;


import az.cybernet.internship.dictionary.model.request.UpdateDescriptionRequest;
import az.cybernet.internship.dictionary.model.response.DictionaryResponse;

public interface DictionaryService {
    DictionaryResponse getDictionary(Long id);
    void updateDescription ( Long id , UpdateDescriptionRequest Request );
    void deleteDictionary(Long id);


}
