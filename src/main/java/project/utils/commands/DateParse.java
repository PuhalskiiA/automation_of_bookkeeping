package project.utils.commands;

import project.utils.exceptionsForCommands.IncorrectDateException;

import java.sql.Date;

public class DateParse {
    public static Date getDate(String date) {
        int month = 0;
        int day = 0;
        int year = 0;

        String[] parseDate = date.split("/");

        try {
            month = Integer.parseInt(parseDate[0]);
            day = Integer.parseInt(parseDate[1]);
            year = Integer.parseInt(parseDate[2]);
        }
        catch (NumberFormatException exception) {
            throw new NumberFormatException("Value must be integer");
        }

        if (month > 0 && month <= 12 && day > 0 && day <= 31 && year > 2000 && year < 2100) {
            year -= 1900;
            Date nDate = new Date(year, month, day);

            return nDate;
        }
        else {
            throw new IncorrectDateException("Incorrect date");
        }
    }
}
