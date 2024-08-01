package uy.com.bse.reportes.ws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DownloadResolver {
    public void resolve(HttpServletRequest request, HttpServletResponse response);
    
    public void handleError(HttpServletRequest request, HttpServletResponse response);
}
