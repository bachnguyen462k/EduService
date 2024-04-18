package com.javatechie.model.request;

import com.javatechie.enums.Action;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private Action status;
    private String id;
}
