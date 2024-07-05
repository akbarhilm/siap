 
package ebkus.util;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
public class BigDecimalPropertyEditor extends PropertyEditorSupport {

    private final NumberFormat sdf;
    private static final Logger logger = LoggerFactory
            .getLogger(BigDecimalPropertyEditor.class);

    public BigDecimalPropertyEditor() {
        this.sdf = NumberFormat.getNumberInstance(new Locale("id"));

    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {
            setValue(new BigDecimal(sdf.parse(text).doubleValue()));
        } catch (ParseException ex) {
            throw new IllegalArgumentException(new StringBuilder(" Wajib diisi ").toString(), ex);
            //   throw new IllegalArgumentException(new StringBuilder(" Harus diisi ").append(ex.getMessage()).toString(), ex);
        }
    }

    @Override
    public String getAsText() {
        final BigDecimal hasil = (BigDecimal) getValue();
        if (hasil != null) {
            //  return hasil % 1 == 0 ? String.format("%.2f", hasil) : this.sdf.format(hasil);
            return sdf.format(hasil);//String.format("%.2f", hasil);
        } else {
            return "";
        }


    }

    public static void main(String[] args) {
        BigDecimalPropertyEditor bp = new BigDecimalPropertyEditor();
        logger.error( bp.sdf.format(20500014000L));
    }
}
