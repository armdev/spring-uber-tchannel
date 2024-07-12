package io.project.app.client.tchannel;

import java.io.Serializable;

/**
 *
 * @author armdev
 */
public class ImportantInfo implements Serializable {

    public ImportantInfo() {
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
