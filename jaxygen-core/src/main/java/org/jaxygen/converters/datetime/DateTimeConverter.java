/*
 * Copyright 2013 imfact02.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jaxygen.converters.datetime;

import java.util.Calendar;
import java.util.Date;
import org.jaxygen.dto.datetime.DateDTO;
import org.jaxygen.dto.datetime.TimeDTO;
import org.jaxygen.dto.datetime.TimestampDTO;

/**
 *
 * @author ak
 */
public class DateTimeConverter {
    public static TimeDTO calendarToTime(Calendar calendar) {
        TimeDTO t = new TimeDTO();
        t.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        t.setMinute(calendar.get(Calendar.MINUTE));
        t.setSec(calendar.get(Calendar.SECOND));
        t.setTimeZone(calendar.getTimeZone().getID());
        return t;
    }
    
    public static DateDTO calendarToDate(Calendar calendar) {
        DateDTO d = new DateDTO();
        d.setYear(calendar.get(Calendar.YEAR));
        d.setMonthOfYear(calendar.get(Calendar.MONTH));
        d.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        return d;
    }
    
    public static DateDTO dateDTOToDate(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendarToDate(calendar);
    }
    
    public static TimestampDTO calendarToTimestamp(Calendar calendar) {
        TimestampDTO t = new TimestampDTO();
        t.setTime(calendarToTime(calendar));
        t.setDate(calendarToDate(calendar));
        return t;
    }
    
    public static Calendar timestampToCalendar(TimestampDTO ts) {
      Calendar c = Calendar.getInstance();
      c.set(Calendar.YEAR, ts.getDate().getYear());
      c.set(Calendar.MONTH, ts.getDate().getMonthOfYear());
      c.set(Calendar.DAY_OF_MONTH, ts.getDate().getDayOfMonth());
      
      c.set(Calendar.HOUR_OF_DAY, ts.getTime().getHour());
      c.set(Calendar.MINUTE, ts.getTime().getMinute());
      c.set(Calendar.SECOND, ts.getTime().getSec());
      return c;
    }
    
     public static Calendar dateToCalendar(DateDTO ts) {
      Calendar c = Calendar.getInstance();
      c.set(Calendar.YEAR, ts.getYear());
      c.set(Calendar.MONTH, ts.getMonthOfYear());
      c.set(Calendar.DAY_OF_MONTH, ts.getDayOfMonth());
      return c;
    }
     
     public static Date dateDTOToDate(DateDTO ts) {
      Calendar c = Calendar.getInstance();
      c.set(Calendar.YEAR, ts.getYear());
      c.set(Calendar.MONTH, ts.getMonthOfYear());
      c.set(Calendar.DAY_OF_MONTH, ts.getDayOfMonth());
      return c.getTime();
    }
}
