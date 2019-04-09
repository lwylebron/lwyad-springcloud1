package lwy.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})   //使用在java类和方法上面
@Retention(RetentionPolicy.RUNTIME)  //运行时起作用
public @interface IgnoreResponseAdvice {
}
