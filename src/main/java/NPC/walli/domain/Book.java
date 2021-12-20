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
@Document(collection = "book")
public class Book {
    @Transient
    public static final String SEQUNCE_NAME = "book_sequence";

    @Id
    private Long id;

    private String name;
}
