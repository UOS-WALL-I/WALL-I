package NPC.walli.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "member")
public class Member {

    @Transient
    public static final String SEQUENCE_NAME = "member_sequence";

    @Id
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private List<String> subjectList = new ArrayList<String>();
}
