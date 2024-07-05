package ebkus.util;



import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sapto
 */
public class DoublePropertyEditor extends PropertyEditorSupport {

    private final NumberFormat sdf;
    private static final Logger logger = LoggerFactory
            .getLogger(DoublePropertyEditor.class);

    public DoublePropertyEditor() {
        this.sdf = NumberFormat.getNumberInstance(new Locale("id"));

    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {
            //logger.info(" nf =====> " + sdf.parse(text).doubleValue());
            setValue(sdf.parse(text).doubleValue());
        } catch (ParseException ex) {
            // throw new IllegalArgumentException(new StringBuilder(" Wajib diisi ").toString(), ex);
            //   throw new IllegalArgumentException(new StringBuilder(" Harus diisi ").append(ex.getMessage()).toString(), ex);
        }
    }

    @Override
    public String getAsText() {
        final Double hasil = (Double) getValue();
        if (hasil != null) {
          //  return hasil % 1 == 0 ? String.format("%.2f", hasil) : this.sdf.format(hasil);
            return   String.format("%.2f", hasil);
        } else {
            return "";
        }

        // return (value != null ? this.sdf.format(value) : "");
    }
}
