package az.cybernet.internship.dictionary.dto.dictionary.restore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestoreDictionaryRequestBean {
    UUID id;
}
