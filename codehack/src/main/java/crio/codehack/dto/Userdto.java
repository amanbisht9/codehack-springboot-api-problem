package crio.codehack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
    private String userId;
    private String userName;
    
    public Userdto(String userId) {
        this.userId = userId;
    }

}
