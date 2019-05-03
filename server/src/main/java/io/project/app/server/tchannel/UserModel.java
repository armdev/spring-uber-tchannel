package io.project.app.server.tchannel;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author armdev
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserModel implements Serializable{

    private String email;
    private String password;
    private Integer age;

  
   

}
