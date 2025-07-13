package mapper;


import model.Dictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IDictionaryMapper {

    List<Dictionary> findAll(@Param("id") String id,
                             @Param("value") String value,
                             @Param("isActive") Boolean isActive,
                             @Param("limit") Integer limit);

    Dictionary findById(String id);

    void insert(Dictionary dictionary);

    void update(Dictionary dictionary);

    void softDelete(@Param("id") String id, @Param("timestamp") String timestamp);

    void restore(@Param("id") String id);
}
