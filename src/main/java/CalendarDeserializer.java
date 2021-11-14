import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarDeserializer extends JsonDeserializer<Calendar> {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{

        String date = p.getText();

        try {
            Date date1 = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            return calendar;
        }catch (Exception e){
            throw  new IOException(e);
        }
    }
}
