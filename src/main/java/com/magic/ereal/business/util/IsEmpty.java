package com.magic.ereal.business.util;


import java.lang.reflect.Field;

/**
 * 判断是否为空
 * @author lzh
 * @create 2017/5/5 9:34
 */
public class IsEmpty {

    /**
     * 判断是否为空 实体参数 利用反射
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static<T> boolean isEmptyToEntity(T t) throws IllegalAccessException {
        if (null == t) {
            return false;
        }
        Field[] fs = t.getClass().getDeclaredFields();
        for (int i = 0 ; i < fs.length ; i ++) {
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            //判断是否为空
            if (null == f.get(t) || "".equals(f.get(t))) {
                return false;
            }
        }
        return true;
    }


}
