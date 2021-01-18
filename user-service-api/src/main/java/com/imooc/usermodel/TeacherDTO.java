package com.imooc.usermodel;

/**
 * @Author liuzike
 * @Date 1/8/21
 **/
public class TeacherDTO extends UserInfo{

    private String intro;

    private Integer stars;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
