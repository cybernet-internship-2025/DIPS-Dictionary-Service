package az.cybernet.internship.dictionary.mapper;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Mapper
public interface CategoryMapper {

    List<CategoryDto> getAll();

    Optional<CategoryWithItemsDto> getByIdWithItems(@Param("id") UUID id);
}

