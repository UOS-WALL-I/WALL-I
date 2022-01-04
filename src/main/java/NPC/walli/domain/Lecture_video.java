package NPC.walli.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.InputStream;

@Data
@Document(collection = "lecture")
public class Lecture_video {
    @Id
    private Long id;

    private String title;

    private String tagging;

    private InputStream stream;
}
