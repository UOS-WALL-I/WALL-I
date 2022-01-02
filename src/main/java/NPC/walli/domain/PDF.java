package NPC.walli.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document(collection = "pdf")
public class PDF {
    @Id
    private String id;

    private String title;

    private Binary file;
}
