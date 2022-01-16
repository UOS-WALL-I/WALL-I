package NPC.walli.repository;

import NPC.walli.domain.Lecture_video;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface LecturevideoRepository extends MongoRepository<Lecture_video, String> {



}
