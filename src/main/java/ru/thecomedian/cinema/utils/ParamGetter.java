package ru.thecomedian.cinema.utils;

/**
 * Класс для получения типизированных данных из объекта
 */
public class ParamGetter {

    /**
     * Получить Long значение из объекта
     *
     * @param o - объект
     */
    public static Long getLongParam(Object o) {
        Long result = null;
        if (o != null) {
            result = Long.parseLong(o.toString());
        }
        return result;
    }

    /**
     * Получить Integer значение из объекта
     *
     * @param o - объект
     */
    public static Integer getIntegerParam(Object o) {
        Integer result = null;
        if (o != null) {
            result = Integer.parseInt(o.toString());
        }
        return result;
    }

    /**
     * Получить String значение из объекта
     *
     * @param o - объект
     */
    public static String getStringParam(Object o) {
        String result = null;
        if (o != null) {
            result = o.toString();
        }
        return result;
    }

}
