package com.showhive.repository.command;

import com.showhive.document.SessionSeatDocument;
import com.showhive.mapper.SessionSeatMapper;
import com.showhive.performance_seat.domain.SessionSeatDomain;
import com.showhive.performance_seat.reposotory.command.SessionSeatCommandRepository;
import com.showhive.repository.SessionSeatCommandMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SessionSeatCommandRepositoryImpl implements SessionSeatCommandRepository {
    private final SessionSeatCommandMongoRepository  sessionSeatCommandMongoRepository;
    private final SessionSeatMapper sessionSeatMapper;

    @Override
    public void save(SessionSeatDomain seat) {
        SessionSeatDocument sessionSeat = sessionSeatMapper.toEntity(seat);
        sessionSeatCommandMongoRepository.save(sessionSeat);
    }
}
