package NPC.walli.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "member")
public class Member {

    @Id
    private ObjectId id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private String phoneNumber;

    private List<String> subjectList;
}
