package NPC.walli.repository;

import NPC.walli.domain.Lecture_video;
import NPC.walli.domain.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface LecturevideoRepository extends  MongoRepository<Lecture_video, title> {



}
