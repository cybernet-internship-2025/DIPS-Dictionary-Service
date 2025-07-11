package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.model.dto.DictionaryCreateUpdateDto;
import az.cybernet.internship.dictionary.model.dto.DictionaryResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-11T10:20:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class DictionaryServiceMapperImpl implements DictionaryServiceMapper {

    @Override
    public DictionaryResponseDto toDto(DictionaryEntry dictionaryEntry) {
        if ( dictionaryEntry == null ) {
            return null;
        }

        DictionaryResponseDto dictionaryResponseDto = new DictionaryResponseDto();

        dictionaryResponseDto.setId( dictionaryEntry.getId() );
        dictionaryResponseDto.setValue( dictionaryEntry.getValue() );
        dictionaryResponseDto.setDescription( dictionaryEntry.getDescription() );
        dictionaryResponseDto.setIsActive( dictionaryEntry.getIsActive() );
        dictionaryResponseDto.setCreatedAt( dictionaryEntry.getCreatedAt() );
        dictionaryResponseDto.setUpdatedAt( dictionaryEntry.getUpdatedAt() );
        dictionaryResponseDto.setDeletedAt( dictionaryEntry.getDeletedAt() );

        return dictionaryResponseDto;
    }

    @Override
    public List<DictionaryResponseDto> toDtoList(List<DictionaryEntry> dictionaryEntries) {
        if ( dictionaryEntries == null ) {
            return null;
        }

        List<DictionaryResponseDto> list = new ArrayList<DictionaryResponseDto>( dictionaryEntries.size() );
        for ( DictionaryEntry dictionaryEntry : dictionaryEntries ) {
            list.add( toDto( dictionaryEntry ) );
        }

        return list;
    }

    @Override
    public DictionaryEntry toEntity(DictionaryCreateUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        DictionaryEntry dictionaryEntry = new DictionaryEntry();

        dictionaryEntry.setValue( dto.getValue() );
        dictionaryEntry.setDescription( dto.getDescription() );

        return dictionaryEntry;
    }

    @Override
    public void updateEntityFromDto(DictionaryCreateUpdateDto dto, DictionaryEntry entity) {
        if ( dto == null ) {
            return;
        }

        entity.setValue( dto.getValue() );
        entity.setDescription( dto.getDescription() );
    }
}
