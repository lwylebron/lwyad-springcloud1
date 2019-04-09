package lwy.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //利用lombok自动生成有参和无参的构造函数
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private  Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
