package com.javatechie.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.apache.logging.log4j.ThreadContext;

import java.io.Serializable;
import java.util.Date;
@Data
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 5552150055173519341L;

    private int code;

    private String message;

    //	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+7")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "GMT+7")
    private Date timestamp;

    private String uuid;
    private long duration;
    private String path;

    private String platform;
    private String version;
    private String host;

    private T data;
    public ResponseData() {
        this.timestamp = new Date();
    }

    public ResponseData<T> success(T data, HttpServletRequest http) {
        this.data = data;
        this.code = 0;
        this.message = "Success!";
        this.path = ThreadContext.get("Origin");
        this.uuid = ThreadContext.get("uuid");
        long start = System.currentTimeMillis();
        this.duration = System.currentTimeMillis() - start;
        this.platform=ThreadContext.get("Sec-Ch-Ua-Platform");
        this.version=ThreadContext.get("User-Agent");
        this.host=ThreadContext.get("Host");
        return this;
    }

    public ResponseData<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        this.path = ThreadContext.get("path");
        this.uuid = ThreadContext.get("uuid");
        long start = Long.parseLong(ThreadContext.get("startTime"));
        this.duration = System.currentTimeMillis() - start;
        return this;
    }
}
