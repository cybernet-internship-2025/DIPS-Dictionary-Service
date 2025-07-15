package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getByCategoryId.GetByCategoryIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getById.GetDictionaryByIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.softDelete.SoftDeleteDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.softDelete.SoftDeleteDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryResponseBean;
import az.cybernet.internship.dictionary.error.NotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryMapstruct;
import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import az.cybernet.internship.dictionary.service.impl.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryMapper dictionaryMapper;
    private final DictionaryMapstruct dictionaryMapstruct;

    public List<GetByCategoryIdResponseBean> getByCategoryId(UUID categoryId) {
        return dictionaryMapper
                .findByCategoryId(categoryId)
                .stream()
                .map(dictionaryMapstruct::fromDictionaryToGetByCategoryIdResponseBean)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreateDictionaryResponseBean createDictionary(CreateDictionaryRequestBean request) {
        if (request.getId() == null) {
            request.setId(UUID.randomUUID());
        }
        Optional<Dictionary> optionalItem = dictionaryMapper.findById(request.getId());
        Dictionary dictionary = dictionaryMapstruct.fromCreateDictionaryRequestBeanToDictionary(request);
        dictionary.setCreatedAt(LocalDateTime.now());
        
        if (optionalItem.isPresent()) {
            dictionaryMapper.update(dictionary);
        } else {
            dictionary.setIsActive(true);
            dictionary.setDeletedAt(null);
            dictionaryMapper.insert(dictionary);
        }
        CreateDictionaryResponseBean response = dictionaryMapstruct.fromDictionaryToCreateDictionaryResponseBean(dictionary);
        response.setMessage("Dictionary with ID " + dictionary.getId() + " successfully created or updated");
        return response;
    }

    public List<FilterDictionaryResponseBean> filterDictionary(FilterDictionaryRequestBean dictionaryRequest) {
        return dictionaryMapper
                .filter(dictionaryRequest)
                .stream()
                .map(dictionaryMapstruct::fromDictionaryToFilterDictionaryResponseBean)
                .collect(Collectors.toList());
    }

    public GetDictionaryByIdResponseBean getById(UUID id) {
        Dictionary dictionary = dictionaryMapper.findById(id)
                .orElseThrow(() -> new NotFoundException("Dictionary not found"));

        GetDictionaryByIdResponseBean response = dictionaryMapstruct.fromDictionaryToGetDictionaryByIdResponseBean(dictionary);
        response.setMessage("Dictionary given by id " + id + ":");

        return response;
    }

    @Transactional
    public RestoreDictionaryResponseBean restoreDictionary(RestoreDictionaryRequestBean request) {
        Optional<Dictionary> dictionary = dictionaryMapper.findByIdNotActive(request.getId());

        if (dictionary.isEmpty()) {
            throw new NotFoundException("Dictionary not found!");
        }

        dictionaryMapper.restore(request.getId());

        RestoreDictionaryResponseBean response = dictionaryMapstruct.fromDictionaryToRestoreDictionaryResponseBean(dictionary.get());
        response.setMessage("Dictionary restored successfully!");

        return response;
    }

    @Override
    public SoftDeleteDictionaryResponseBean softDelete(SoftDeleteDictionaryRequestBean request) {
        if (dictionaryMapper.findById(request.getId()).isEmpty()) {
            throw new NotFoundException("Dictionary not found!");
        }

        dictionaryMapper.softDelete(request.getId());

        return SoftDeleteDictionaryResponseBean.builder()
                .message("Dictionary with ID " + request.getId() + " deleted!")
                .build();
    }

    @Override
    public UpdateDictionaryResponseBean updateDictionary(UpdateDictionaryRequestBean request) {
        Dictionary dictionary=dictionaryMapper.findById(request.getId())
                .orElseThrow(()->new NotFoundException("Dictionary not found!"));

        dictionaryMapstruct.fromUpdateDictionaryRequestBeanToDictionary(request,dictionary);

        dictionaryMapper.update(dictionary);

        UpdateDictionaryResponseBean response = dictionaryMapstruct.fromDictionaryToUpdateDictionaryResponseBean(dictionary);
        response.setMessage("Dictionary updated successfully!");

        return response;
    }

    @Override
    public void changeActivityStatusByCategoryId(UUID categoryId, Boolean activityStatus) {
        dictionaryMapper.changeActivityStatusByCategoryId(categoryId,activityStatus);
    }
}