package com.example.buenasnochesapi.models.dto.responses.status;

import java.io.Serializable;

public class ResponseMessageAndModel<T> extends ResponseMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private T operationPerformedIn;

    public ResponseMessageAndModel(){
        super();
    }

    public ResponseMessageAndModel(String message, T operationPerformedIn) {
        super(message);
        this.operationPerformedIn = operationPerformedIn;
    }

    public ResponseMessageAndModel(T operationPerformedIn) {
        this.operationPerformedIn = operationPerformedIn;
    }

    public T getOperationPerformedIn() {
        return operationPerformedIn;
    }

    public void setOperationPerformedIn(T operationPerformedIn) {
        this.operationPerformedIn = operationPerformedIn;
    }
}
