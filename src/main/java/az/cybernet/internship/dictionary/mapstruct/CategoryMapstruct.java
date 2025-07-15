package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryRequestBean;
import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface CategoryMapstruct {
    CategoryDto toDto(Category category);
    Category toModel(CategoryDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateCategoryRequestBeanToCategory(UpdateCategoryRequestBean request, @MappingTarget Category category);

    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Category fromCreateCategoryRequestBeanToCategory(CreateCategoryRequestBean request);
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    CreateCategoryResponseBean fromCategoryToCreateCategoryResponseBean(Category category);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    UpdateCategoryResponseBean fromCategoryToUpdateCategoryResponseBean(Category category);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    RestoreCategoryResponseBean fromCategoryToRestoreCategoryResponseBean(Category category);

    @Named("formatDateTime")
    static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
