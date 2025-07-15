package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getByCategoryId.GetByCategoryIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getById.GetDictionaryByIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryResponseBean;
import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring")
public interface DictionaryMapstruct {

    Dictionary fromCreateDictionaryRequestBeanToDictionary(CreateDictionaryRequestBean requestBean);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    GetByCategoryIdResponseBean fromDictionaryToGetByCategoryIdResponseBean(Dictionary item);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    CreateDictionaryResponseBean fromDictionaryToCreateDictionaryResponseBean(Dictionary dictionary);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    FilterDictionaryResponseBean fromDictionaryToFilterDictionaryResponseBean(Dictionary dictionary);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    GetDictionaryByIdResponseBean fromDictionaryToGetDictionaryByIdResponseBean(Dictionary dictionary);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    RestoreDictionaryResponseBean fromDictionaryToRestoreDictionaryResponseBean(Dictionary dictionary);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    UpdateDictionaryResponseBean fromDictionaryToUpdateDictionaryResponseBean(Dictionary dictionary);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateDictionaryRequestBeanToDictionary(UpdateDictionaryRequestBean request, @MappingTarget Dictionary dictionary);
    @Named("formatDateTime")
    static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}