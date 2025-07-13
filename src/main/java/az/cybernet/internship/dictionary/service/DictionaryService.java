package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DictionaryService {

    List<DictionaryResponse> getDictionaries(String id, String value, Boolean isActive, Integer limit);


    public void saveOrUpdate(DictionaryRequest request);


}



//    private final DictionaryRepository az.cybernet.internship.dictionary.repository;
//
//    public DictionaryService(DictionaryRepository az.cybernet.internship.dictionary.repository) {
//        this.az.cybernet.internship.dictionary.repository = az.cybernet.internship.dictionary.repository;
//    }
//
//    public List<DictionaryResponse> getDictionaries() {
//        return az.cybernet.internship.dictionary.repository.getDictionaries(id, value, isActive, limit)
//                .stream()
//                .map(this::toResponse)
//                .collect(Collectors.toList());
//    }
//
//    public void saveOrUpdate(DictionaryRequest request) {
//        if (request.getValue() == null || request.getValue().isBlank()) {
//            throw new IllegalArgumentException("Value must not be empty");
//        }
//
//        DictionaryEntity existing = null;
//        if (request.getId() != null) {
//            existing = az.cybernet.internship.dictionary.repository.findById(request.getId());
//            if (existing == null) {
//                throw new IllegalArgumentException("No dictionary entry found for id: " + request.getId());
//            }
//        }
//
//        DictionaryEntity dict = new DictionaryEntity();
//        dict.setId(request.getId() != null ? request.getId() : "invoice_status_" + request.getValue().toLowerCase());
//        dict.setCategory("Invoice Status");
//        dict.setValue(request.getValue());
//        dict.setDescription(request.getDescription());
//        dict.setIsActive(true);
//
//        if (existing != null) {
//            az.cybernet.internship.dictionary.repository.update(dict);
//        } else {
//            az.cybernet.internship.dictionary.repository.insert(dict);
//        }
//    }
//
//    public void softDelete(String id) {
//        DictionaryEntity entry = az.cybernet.internship.dictionary.repository.findById(id);
//        if (entry == null) {
//            throw new IllegalArgumentException("DictionaryEntity entry not found for deletion.");
//        }
//        az.cybernet.internship.dictionary.repository.softDelete(id, Instant.now().toString());
//    }
//
//    public void restore(String id) {
//        DictionaryEntity entry = az.cybernet.internship.dictionary.repository.findById(id);
//        if (entry == null) {
//            throw new IllegalArgumentException("DictionaryEntity entry does not exist.");
//        }
//        if (Boolean.TRUE.equals(entry.getIsActive())) {
//            throw new IllegalArgumentException("DictionaryEntity entry is already active.");
//        }
//        az.cybernet.internship.dictionary.repository.restore(id);
//    }
//
//    private DictionaryResponse toResponse(DictionaryEntity dictionary) {
//        DictionaryResponse response = new DictionaryResponse();
//        response.setId(dictionary.getId());
//        response.setCategory(dictionary.getCategory());
//        response.setValue(dictionary.getValue());
//        response.setDescription(dictionary.getDescription());
//        response.setIsActive(dictionary.getIsActive());
//        return response;
//    }

