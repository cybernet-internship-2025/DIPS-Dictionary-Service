package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {

    void insert(Dictionary item);

    void update(Dictionary item);

    void softDelete(@Param("id") UUID id);
    List<Dictionary> findByCategoryId(@Param("categoryId") UUID categoryId);


    void restore(@Param("id") UUID id);

    Optional<Dictionary> findById(@Param("id") UUID id);
    List<Dictionary> filter(
            @Param("id") UUID id,
            @Param("value") String value,
            @Param("isActive") Boolean isActive,
            @Param("limit") Integer limit
    );
    //insert → yeni DictionaryItem əlavə edir (is_active true)

//    update → olan item-ı dəyişir
//
//    softDelete → is_active = false, deleted_at = now()
//
//    restore → is_active = true, deleted_at = null
//
//    findById → 404 və 400 üçün vacibdir
//
//    filter → GET endpoint üçün dinamik filtrasiya


}

