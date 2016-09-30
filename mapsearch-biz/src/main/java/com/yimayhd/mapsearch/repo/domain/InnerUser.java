package com.yimayhd.mapsearch.repo.domain;

import java.util.Calendar;
import java.util.Date;

import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.enums.Gender;

/**
 * Created by menhaihao on 15/7/15.
 *
 */
public class InnerUser {
    private UserDO user;

//    private UserTag userTag;
    public InnerUser(UserDO user) {
        this.user = user;
    }

    public String getAvatar() {
        return user.getAvatar();
    }

    public String getNick() {
        String nick = user.getNickname();
        return nick;
    }

    public long getId() {
        return user.getId();
    }

    public String getName(){
    	return user.getName();
    }
    
    public String getGender() {
        Gender gender = Gender.getGenderByValue(user.getGender());
        if (gender == null) {
            return com.yimayhd.mapsearch.client.enums.Gender.INVALID_GENDER.name();
        }
        return com.yimayhd.mapsearch.client.enums.Gender.getByNameWithDefault(gender.name()).name();
    }
    
    public int getAge(){
    	return getAge(user.getBirthday());
    }
    
    public String getSignature(){
    	return user.getSignature();
    }
    
    /**
     *  1、获取当前时间
        2、判断出生日期是否小于当前时间，如果大于，则引发一场
        3、从当前时间中取出年、月、日；从出生日期中取出年、月、日，年份相减
        4、然后做具体判断
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay){
        int age = 0;
        if(birthDay != null){
            //获取当前系统时间
            Calendar cal = Calendar.getInstance();
            //如果出生日期大于当前时间，则抛出异常
            if (cal.before(birthDay)) {
                throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
            }
            //取出系统当前时间的年、月、日部分
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            //将日期设置为出生日期
            cal.setTime(birthDay);
            //取出出生日期的年、月、日部分
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            //当前年份与出生年份相减，初步计算年龄
            age = yearNow - yearBirth;
            //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
            if (monthNow <= monthBirth) {
                //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) age--;
                }else{
                    age--;
                }
            }
        }
        return age;
    }

}
