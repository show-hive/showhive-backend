package com.showhive;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Time;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationToTimeConverter implements AttributeConverter<Duration, Time> {

    @Override
    public Time convertToDatabaseColumn(Duration duration) {
        if (duration == null) return null;

        long seconds = duration.getSeconds();

        int HOUR_MINUTE = 60;
        int HOUR_SECOND = 3600;

        long hours = seconds / HOUR_SECOND;
        int minutes = (int) ((seconds % HOUR_SECOND) / HOUR_MINUTE);
        int secs = (int) (seconds % HOUR_MINUTE);
        return Time.valueOf(String.format("%02d:%02d:%02d", hours, minutes, secs));
    }

    @Override
    public Duration convertToEntityAttribute(Time time) {
        if (time == null) return null;
        return Duration.ofMillis(time.getTime());
    }
}
