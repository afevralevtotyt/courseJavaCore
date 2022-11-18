package Interface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface TaskType {
    enum Repeatable {ONCE, DAILY, WEEKLY, MONTHLY, ANNUAL}

    enum Type {WORK, PERSONAL}

    String taskTimeNextDate();


}
