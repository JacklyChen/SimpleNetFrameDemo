package mi.simplenet.org.base;


import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;

/**
 * 请求结果类,继承自BasicHttpResponse,将结果存储在rawData中.
 * Created by JW.Xuan on 2016/12/5 14:33.
 * 邮箱：mifind@sina.com
 */
public class Response extends BasicHttpResponse {
    public byte[] rawData = new byte[0];

    public Response(StatusLine statusline) {
        super(statusline);
    }

    public Response(StatusLine statusline, ReasonPhraseCatalog catalog, Locale locale) {
        super(statusline, catalog, locale);
    }

    public Response(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }

    @Override
    public void setEntity(HttpEntity entity) {
        super.setEntity(entity);
        rawData = entityToBytes(getEntity());
    }

    public byte[] getRawData() {
        return rawData;
    }

    public int getStatusCode() {
        return getStatusLine().getStatusCode();
    }

    public String getMessage() {
        return getStatusLine().getReasonPhrase();
    }

    /**
     * Reads the contents of HttpEntity into a byte[].
     */
    private byte[] entityToBytes(HttpEntity entity) {
        try {
            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
