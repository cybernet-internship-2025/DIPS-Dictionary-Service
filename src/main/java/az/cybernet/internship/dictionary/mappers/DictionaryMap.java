package az.cybernet.internship.dictionary.mappers;

import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;
import az.cybernet.internship.dictionary.entity.Dictionary;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictionaryMap {
    List<DictionaryResp> toDto(List<Dictionary> dictionary);
}
