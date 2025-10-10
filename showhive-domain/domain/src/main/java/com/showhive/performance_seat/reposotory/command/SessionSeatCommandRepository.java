package com.showhive.performance_seat.reposotory.command;

import com.showhive.performance_seat.domain.SessionSeatDomain;

public interface SessionSeatCommandRepository {
    void save(SessionSeatDomain seat);
}
