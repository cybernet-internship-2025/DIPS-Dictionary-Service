package az.cybernet.internship.dictionary.dto.dictionary.filterDictionary;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterDictionaryRequestBean {
    String value;
    Boolean isActive;
    Integer offset;
    Integer limit;
}
