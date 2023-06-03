package studycafe.studycaferenewal.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        return LocalDateTime.parse(text, formatter);
        return null;
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        DateTimeFormatter formatter;

        LocalDateTime now = LocalDateTime.now();
        if (dateTime.toLocalDate().equals(now.toLocalDate())) {
            formatter =  DateTimeFormatter.ofPattern("HH:mm");
        } else {
            formatter=  DateTimeFormatter.ofPattern("yy.MM.dd");
        }

        return dateTime.format(formatter);

    }
}
