package io.project.app.server.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author armdev
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ImportantInfo implements Serializable {

    private String message;

}
