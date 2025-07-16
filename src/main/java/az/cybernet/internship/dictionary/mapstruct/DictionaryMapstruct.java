package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryRequest;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DictionaryMapstruct {

    Dictionary toModel(DictionaryRequest request);

    DictionaryResponse toResponse(Dictionary item);
    List<DictionaryResponse> toResponses(List<Dictionary> list);
}

