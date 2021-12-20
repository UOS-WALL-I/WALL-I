package NPC.walli.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "lecture")
public class Lecture {
    @Transient
    public static final String SEQUENCE_NAME = "lecture_sequence";

    @Id
    private Long id;

    private String name;
}
