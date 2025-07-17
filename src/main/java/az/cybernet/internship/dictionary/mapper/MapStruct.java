package az.cybernet.internship.dictionary.mapper;
import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.mapstruct.Mapper;

import java.util.Dictionary;
import java.util.List;

@Mapper(componentModel = "Spring")
public interface MapStruct {
    DictionaryResponse mapToResponse(DictionaryEntry dictionaryEntry);
    DictionaryEntry buildEntry (DictionaryRequest dictionaryRequest);
    List<DictionaryResponse> mapToResponseList(List<DictionaryEntry> dictionaryEntries);



}
