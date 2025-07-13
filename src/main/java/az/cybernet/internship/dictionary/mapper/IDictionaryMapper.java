package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IDictionaryMapper {

    List<DictionaryEntity> getDictionaries(@Param("id") String id,
                                           @Param("value") String value,
                                           @Param("isActive") Boolean isActive,
                                           @Param("limit") Integer limit);
}

//    DictionaryEntity findById(String id);
//
//    void insert(DictionaryEntity dictionaryEntity);
//
//    void update(DictionaryEntity dictionaryEntity);
//
//    void softDelete(@Param("id") String id, @Param("timestamp") String timestamp);
//
//    void restore(@Param("id") String id);
//}
