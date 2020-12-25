package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.jaboderzhateli.gradework.models.Message;

import java.util.List;

@RepositoryRestResource
public interface MessagesRepository extends JpaRepository<Message, Long> {

    List<Message> findByChannel_Id(Long channelId);

    List<Message> findLastByAuthor_IdAndChannel_Id(Long authorId, Long channelId);
//    List<Message> findLastMessagesForChannelsByUserId(Long userId);
}