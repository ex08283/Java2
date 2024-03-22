package be.kdg.logging;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SmallLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        LocalDateTime ldt = LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        String date = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ";
        String mssg = "melding: \"" + MessageFormat.format(record.getMessage(), record.getParameters()) + "\"";
        String level = "Level: " + record.getLevel() + " ";

        return date + level + mssg + "\n";
    }
}
