package ebkus.util;



import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author sapto
 */
public class SqlDatePropertyEditor extends PropertyEditorSupport {

    public static final String DEFAULT_BATCH_PATTERN = "dd/MM/yyyy";
    private final SimpleDateFormat sdf;

    /**
     * uses default pattern dd/MM/yyyy for date parsing.
     */
    public SqlDatePropertyEditor() {
        this.sdf = new SimpleDateFormat(SqlDatePropertyEditor.DEFAULT_BATCH_PATTERN);
    }

    
    public SqlDatePropertyEditor(String pattern) {
        this.sdf = new SimpleDateFormat(pattern);
    }

   
    @Override
    public void setAsText(String text) {

        try {
            setValue(new Date(this.sdf.parse(text).getTime()));
        } catch (ParseException ex) {
           // throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
          //  ex.printStackTrace();
        }
    }

   
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null  ? this.sdf.format(value) : "");
    }
}
