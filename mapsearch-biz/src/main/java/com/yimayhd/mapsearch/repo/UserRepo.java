package com.yimayhd.mapsearch.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.repo.domain.InnerUser;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.domain.UserDOQuery;
import com.yimayhd.user.client.result.BasePageResult;
import com.yimayhd.user.client.service.UserService;

/**
 * Created by menhaihao on 15/5/21.
 *
 */
public class UserRepo {
    @Autowired
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserRepo.class);

    public List<UserDO> findUserByUserName(String nickName){
    	if(StringUtils.isBlank(nickName)){
    		return null;
    	}
    	UserDOQuery query = new UserDOQuery();
    	query.setNickname(nickName);
    	BasePageResult<UserDO> basePageResult = userService.findByConditionNoPage(query);
    	return basePageResult.getList();
    }
    
    public List<InnerUser> getUserByIds(List<Long> userIds) {
    	List<InnerUser> userList = new ArrayList<InnerUser>();
        if (CollectionUtils.isEmpty(userIds)) {
            return userList;
        }
        try {
            List<UserDO> userDOList = userService.getUserInfoList(userIds);
            if(!CollectionUtils.isEmpty(userDOList)){
            	for(UserDO userDO : userDOList){
            		userList.add(new InnerUser(userDO));
            	}
            }
        } catch (Exception e) {
            log.error("userService.getUserInfoList exception,userIds:"+ JSON.toJSONString(userIds),e);
        }
        return userList;
    }

    public Map<Long,InnerUser> getUserByIdList(List<Long> userIds) {
    	Map<Long,InnerUser> map = new HashMap<Long,InnerUser>();
        if (CollectionUtils.isEmpty(userIds)) {
        	return map;
        }
        try {
            List<UserDO> userDOList = userService.getUserInfoList(userIds);
            if(!CollectionUtils.isEmpty(userDOList)){
            	for(UserDO userDO : userDOList){
            		map.put(userDO.getId(), new InnerUser(userDO));
            	}
            }
        } catch (Exception e) {
            log.error("userService.getUserInfoList exception,userIds:"+ JSON.toJSONString(userIds),e);
        }
        return map;
    }

    public InnerUser getUserById(long userId) {
        try {
            UserDO user = userService.getUserDOById(userId);
            if (user == null) {
                return null;
            }
            return new InnerUser(user);
        } catch (Exception e) {
            log.error("userService.getUserById(userId) exception,userId:" + userId, e);
        }
        return null;
    }
}
