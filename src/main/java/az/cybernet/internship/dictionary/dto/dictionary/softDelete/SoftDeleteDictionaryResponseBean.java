package az.cybernet.internship.dictionary.dto.dictionary.softDelete;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SoftDeleteDictionaryResponseBean {
    String message;
}
