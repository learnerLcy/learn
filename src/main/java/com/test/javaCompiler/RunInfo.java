package com.test.javaCompiler;

import lombok.Data;

/**
 * @ClassName:RunInfo
 * @Description:
 * @Author:lvchunyang
 * @Date:13:16
 **/
@Data
public class RunInfo {
    //true:代表超时
    private Boolean timeOut;

    private Long compilerTakeTime;
    private String compilerMessage;
    private Boolean compilerSuccess;

    private Long runTakeTime;
    private String runMessage;
    private Boolean runSuccess;

}
