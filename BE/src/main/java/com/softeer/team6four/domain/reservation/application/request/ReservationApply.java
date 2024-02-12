package com.softeer.team6four.domain.reservation.application.request;

import com.google.firebase.database.annotations.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationApply {
    private @NotNull Long carbobId;
    private @NotNull LocalDateTime startDateTime;
    private @NotNull LocalDateTime endDateTime;
}