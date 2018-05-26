package org.wade.mbus.model.enums;

public enum ValidateCodeType {
    /*
     * 缺省类型,一般用于系统测试
     */
    T_DEFAULT,

    /**
     * 英数类型,支持多线程调用, 速度快, 识别率一般
     */
    T_EN_XG,

    /**
     * 英数类型,不支持多线程, 速度慢, 识别率高
     */
    T_EN_HY,

    /**
     * 汉字类型,支持多线程调用, 速度快, 识别率一般
     */
    T_ZH_XG,
}
