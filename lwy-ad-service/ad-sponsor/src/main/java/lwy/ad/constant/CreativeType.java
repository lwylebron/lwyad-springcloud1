package lwy.ad.constant;

import lombok.Getter;

@Getter
public enum CreativeType {

    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    TEXT(3,"文本");



    private int tppe;
    private String desc;

    CreativeType(int type,String desc){
        this.tppe = type;
        this.desc=desc;
    }
}
