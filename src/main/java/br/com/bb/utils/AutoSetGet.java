package br.com.bb.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class AutoSetGet {
    
    public static boolean allNonNull(Object obj)
    {
        for (Method method: obj.getClass().getMethods())
        {
            if (method.getName().startsWith("get"))
            {
                try {
                    if (method.invoke(obj, null) == null)
                    {
                        return false;
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

}
