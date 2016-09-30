package com.yimayhd.mapsearch.client.service;


import com.yimayhd.mapsearch.client.domain.mongo.*;

/**
 *
 */
public interface MongoLbsService {

    /**
     * 获取附近的结果
     * @param updatePersonDTO UpdatePersonDTO
     * @return UpdatePersonResult
     */
    UpdatePersonResult batchUpdatePerson(UpdatePersonDTO updatePersonDTO);

    /**
     * 获取附近的结果
     * @param personQuery PersonQuery
     * @return PersonResult
     */
    PersonResult getPersonList(PersonQuery personQuery);


}
