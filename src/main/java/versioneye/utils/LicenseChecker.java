package versioneye.utils;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 11/4/12
 * Time: 1:45 AM
 *
 */
public class LicenseChecker {

    public static final String MIT_1_1 = "Permission is hereby granted, free of charge, to any person obtaining" +
            "a copy of this software and associated documentation files (the" +
            "\"Software\"), to deal in the Software without restriction, including" +
            "without limitation the rights to use, copy, modify, merge, publish," +
            "distribute, sublicense, and/or sell copies of the Software, and to" +
            "permit persons to whom the Software is furnished to do so, subject to" +
            "the following conditions:";
    public static final String MIT_1_2 = "The above copyright notice and this permission notice shall be" +
            "included in all copies or substantial portions of the Software.";
    public static final String MIT_1_3 = "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND," +
            "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF" +
            "MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND" +
            "NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE" +
            "LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION" +
            "OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION" +
            "WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.";

    private HttpUtils httpUtils;
    private LogUtils logUtils;

    public String checkLicenseOnGitHub(String uri){
        try{
            if (uri != null &&
                    (uri.startsWith("http://github.com") || uri.startsWith("https://github.com") ||
                     uri.startsWith("http://www.github.com") || uri.startsWith("https://www.github.com") ) ){
                String body = getLicenseText(uri);
                if ( isMit(body) ) {
                    return "MIT";
                }
            }
        } catch (Exception ex) {
            logUtils.addError("error in checkLicenseOnGitHub" + uri + "", ex.toString(), null);
        }
        return null;
    }

    private String getLicenseText(String uri) throws Exception{
        String rawUrl = uri.replace("github.com", "raw.github.com");
        if (rawUrl.contains("http:")){
            rawUrl = rawUrl.replaceFirst("http:", "https:");
        }
        if (rawUrl.contains("www.")){
            rawUrl = rawUrl.replaceFirst("www.", "");
        }

        String LICENSE = rawUrl + "/master/LICENSE";
        int code = httpUtils.getResponseCode(LICENSE);
        if (code == 200)
            return httpUtils.getHttpResponse(LICENSE);

        String LICENSEtxt = rawUrl + "/master/LICENSE.txt";
        code = httpUtils.getResponseCode(LICENSEtxt);
        if (code == 200)
            return httpUtils.getHttpResponse(LICENSEtxt);

        String LICENSEmd = rawUrl + "/master/LICENSE.md";
        code = httpUtils.getResponseCode(LICENSEmd);
        if (code == 200)
            return httpUtils.getHttpResponse(LICENSEmd);

        String LicenseTxt = rawUrl + "/master/License.txt";
        code = httpUtils.getResponseCode(LicenseTxt);
        if (code == 200)
            return httpUtils.getHttpResponse(LicenseTxt);

        String MIT_LICENSE = rawUrl + "/master/MIT-LICENSE";
        code = httpUtils.getResponseCode(MIT_LICENSE);
        if (code == 200)
            return httpUtils.getHttpResponse(MIT_LICENSE);

        String MIT_LICENSETxt = rawUrl + "/master/MIT-LICENSE.txt";
        code = httpUtils.getResponseCode(MIT_LICENSETxt);
        if (code == 200)
            return httpUtils.getHttpResponse(MIT_LICENSETxt);

        String COPYING = rawUrl + "/master/COPYING";
        code = httpUtils.getResponseCode(COPYING);
        if (code == 200)
            return httpUtils.getHttpResponse(COPYING);

        return null;
    }

    private boolean isMit(String body){
        return body != null && body.contains(MIT_1_2) && body.contains(MIT_1_2) && body.contains(MIT_1_3);
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public void setLogUtils(LogUtils logUtils) {
        this.logUtils = logUtils;
    }

}
