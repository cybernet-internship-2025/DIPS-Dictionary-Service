package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getByCategoryId.GetByCategoryIdRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.getByCategoryId.GetByCategoryIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getById.GetDictionaryByIdRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.getById.GetDictionaryByIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.softDelete.SoftDeleteDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.softDelete.SoftDeleteDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryResponseBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

public interface DictionaryService {
    List<GetByCategoryIdResponseBean> getByCategoryId(GetByCategoryIdRequestBean request);
    CreateDictionaryResponseBean createDictionary(CreateDictionaryRequestBean request);
    List<FilterDictionaryResponseBean> filterDictionary(FilterDictionaryRequestBean request);
    GetDictionaryByIdResponseBean getById(GetDictionaryByIdRequestBean request);
    RestoreDictionaryResponseBean restoreDictionary(RestoreDictionaryRequestBean request);
    SoftDeleteDictionaryResponseBean softDelete(SoftDeleteDictionaryRequestBean request);

    UpdateDictionaryResponseBean updateDictionary(UpdateDictionaryRequestBean request);
    void changeActivityStatusByCategoryId(@Param("categoryId") UUID categoryId,@Param("activityStatus") Boolean activityStatus);

}
