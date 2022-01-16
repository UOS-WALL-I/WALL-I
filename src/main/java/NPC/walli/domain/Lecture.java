package NPC.walli.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lecture")
public class Lecture {
    @Id
    private ObjectId id;

    private String subject;

    private String title;

    private String url;

    private ObjectId memberId;
}
