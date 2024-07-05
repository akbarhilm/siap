package ebkus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
public class SipkdHelpers {

    private static final Logger logger = LoggerFactory.getLogger(SipkdHelpers.class);
    private static final NumberFormat sdf = NumberFormat.getNumberInstance(new Locale("id"));

    public static String splitString(final String param, final String regex, final int pickup) {
        if (StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {
            return StringUtils.split(param, regex)[pickup];
        } else {
            return param;

        }
    }

    public static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = br.readLine();
            if (!line.isEmpty()) {
                resultStringBuilder.append(line);
            }
            br.close();
        }
        return resultStringBuilder.toString();
    }

    public static List readFromInputStreamList(InputStream inputStream)
            throws IOException {
        List<String> list = new ArrayList<>();
        String item;
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((item = br.readLine()) != null) {
                list.add(item);
            }
            br.close();
        }
        return list;
    }

    public static int getIntFromString(String param) {
        int hasil = 0;
        if (param != null && StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {

            try {
                hasil = Integer.parseInt(param);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return hasil;
    }

    public static BigDecimal getBigDecimalFromString(String param) {
        logger.debug("param = " + param);
        if (param != null && StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {
            final String param2 = StringUtils.replace(param, "+", "").trim();
            DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(new Locale("id"));
            nf.setParseBigDecimal(true);
            return (BigDecimal) nf.parse(param2, new ParsePosition(0));

        } else {
            return new BigDecimal(0);
        }

    }

    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return regionMatches(cs1, false, 0, cs2, 0, Math.max(cs1.length(), cs2.length()));
    }

    static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart,
            CharSequence substring, int start, int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, ((String) substring), start, length);

        } else {
            return cs.toString().regionMatches(ignoreCase, thisStart, substring.toString(), start, length);

        }
    }

    public static String substringNullSafe(String str, int start, int end) {
        if (str == null) {
            return null;
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    public static String getIpAddr(HttpServletRequest request) {
        /* String ip = request.getHeader("X-Real-IP");
         if (null != ip && !StringUtils.isEmpty(ip)
         && StringUtils.equalsIgnoreCase(ip, "unknown")) {
         return ip;
         }
         ip = request.getHeader("X-Forwarded-For");
         if (null != ip && !StringUtils.isEmpty(ip)
         && StringUtils.equalsIgnoreCase(ip, "unknown")) {
         // get first ip from proxy ip
         int index = ip.indexOf(',');
         if (index != -1) {
         return ip.substring(0, index);
         } else {
         return ip;
         }
         }*/
        return request.getRemoteAddr();
    }

    public static String formatSqlDatePropertyEditor(Date value) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(value);
    }

    public static String formatSqlDatePropertyEditor1(Date value) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(value);
    }

    public static String formatBigDecimalPropertyEditor(BigDecimal value) {
        final NumberFormat sdf = NumberFormat.getNumberInstance(new Locale("id"));
        return sdf.format(value);
    }

    public static boolean compareDateWithCurrent(final String tgl) {
        boolean status = false;
        try {
            final Calendar calendar = new GregorianCalendar();
            final String inputStr = tgl + "-" + calendar.get(Calendar.YEAR);
            final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date inputDate = dateFormat.parse(inputStr);

            if (inputDate.compareTo(new java.util.Date(System.currentTimeMillis())) >= 0) {
                status = true;
            }
        } catch (ParseException ex) {

        }
        return status;
    }

    public static Date getDateFromString(String param) {
        logger.debug("param = " + param);
        if (param != null && StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return new Date(sdf.parse(param).getTime());
            } catch (ParseException ex) {
                return null;
            }

        } else {
            return null;
        }

    }

    public static String getStringDateFormatFromString(String param, String formatOut, String formatIn) {
        logger.debug("param = " + param);
        if (param != null && StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat(formatIn);
                final SimpleDateFormat sdfout = new SimpleDateFormat(formatOut);
                return sdfout.format(sdf.parse(param));
            } catch (ParseException ex) {
                return null;
            }

        } else {
            return null;
        }

    }
    
    public static List<String> getDjpService(ServletContext servletContext) throws IOException {
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/djp.txt");
        List<String> listServices = ebkus.util.SipkdHelpers.readFromInputStreamList(inputStream);
        return listServices;
    }


    public static void main(String[] args) {
        logger.error(" = " + SipkdHelpers.substringNullSafe("05/03/2015", 3, 5));
    }
}
