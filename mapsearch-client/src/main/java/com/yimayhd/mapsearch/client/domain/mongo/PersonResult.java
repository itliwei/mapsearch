package com.yimayhd.mapsearch.client.domain.mongo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class PersonResult {
    /**
     * 查询条件
     */
    PersonQuery personQuery;
    /**
     * 查询的结果
     */
    List<PersonInfo> personInfoList;

    public PersonQuery getPersonQuery() {
        return personQuery;
    }

    public void setPersonQuery(PersonQuery personQuery) {
        this.personQuery = personQuery;
    }

    public List<PersonInfo> getPersonList() {
        return personInfoList;
    }

    public void setPersonList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }
}
