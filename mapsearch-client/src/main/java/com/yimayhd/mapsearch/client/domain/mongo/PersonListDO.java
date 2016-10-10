package com.yimayhd.mapsearch.client.domain.mongo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class PersonListDO implements Serializable{

        List<Person> personList;

        public List<Person> getPersonList() {
                return personList;
        }

        public void setPersonList(List<Person> personList) {
                this.personList = personList;
        }
}
