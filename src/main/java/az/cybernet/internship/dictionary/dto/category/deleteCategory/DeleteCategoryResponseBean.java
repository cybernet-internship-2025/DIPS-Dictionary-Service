package az.cybernet.internship.dictionary.dto.category.deleteCategory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeleteCategoryResponseBean {
    String message;
}
