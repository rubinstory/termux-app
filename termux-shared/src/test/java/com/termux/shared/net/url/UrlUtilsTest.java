package com.termux.shared.net.url;

import junit.framework.TestCase;

import java.net.URL;

public class UrlUtilsTest extends TestCase {
    /**
     * Purpose: Check the right form(success)
     * Input: base: "https://github.com", destination: "termux/termux-app"
     * Expected: "https://github.com/termux/termux-app"
     */
    public void testJoinUrlRight() {
        String base = "https://github.com";
        String destination = "termux/termux-app";
        assertEquals(base + "/" + destination, UrlUtils.joinUrl(base, destination, false));
    }

    /**
     * Purpose: Check the wrong form
     * Input: base: "github.com", destination: "termux/termux-app"
     * Expected: null
     */
    public void testJoinUrlWrongForm() {
        String base = "github.com";
        String destination = "termux/termux-app";
        assertNull(UrlUtils.joinUrl(base, destination, false));
    }

    /**
     * Purpose: Check the unknown protocol
     * Input: base: "unknownProtocol://github.com", destination: "termux/termux-app"
     * Expected: null
     */
    public void testJoinUrlUnknownProtocol() {
        String base = "unknownProtocol://github.com";
        String destination = "termux/termux-app";
        assertNull(UrlUtils.joinUrl(base, destination, false));
    }

    /**
     * Purpose: Check the empty or null url string
     * Input: "", null
     * Expected: null
     */
    public void testGetUrlEmptyOrNull() {
        assertNull(UrlUtils.getUrl(""));
        assertNull(UrlUtils.getUrl(null));
    }

    /**
     * Purpose: Check the right form(success)
     * Input: "https://github.com"
     * Expected: URL("https://github.com")
     */
    public void testGetUrl() {
        String url = "https://github.com";
        URL result = UrlUtils.getUrl(url);
        assertNotNull(result);
        assertEquals(result.toString(), url);
    }

    /**
     * Purpose: Check URI generic syntax
     * Input: "https://username:password@host:1234/path1/path2?query1=1&query2=2#fragment"
     * Expected:
     *      protocol        https
     *      user_info       username:password
     *      host            host
     *      port            1234
     *      authority       username:password@host:1234
     *      path            /path1/path2
     *      query           query1=1&query2=2
     *      file            /path1/path2?query1=1&query2=2
     *      fragment, ref   fragment
     * https://en.wikipedia.org/wiki/URL
     */
    public void testGetUrlPart() {
        String genericURI = "https://username:password@host:1234/path1/path2?query1=1&query2=2#fragment";

        String protocol = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.PROTOCOL);
        String user_info = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.USER_INFO);
        String host = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.HOST);
        String port = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.PORT);
        String authority = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.AUTHORITY);
        String path = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.PATH);
        String query = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.QUERY);
        String file = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.FILE);
        String ref = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.REF);
        String fragment = UrlUtils.getUrlPart(genericURI, UrlUtils.UrlPart.FRAGMENT);

        assertEquals(protocol, "https");
        assertEquals(user_info, "username:password");
        assertEquals(host, "host");
        assertEquals(port, "1234");
        assertEquals(user_info + "@" + host + ":" + port, authority);
        assertEquals(path, "/path1/path2");
        assertEquals(query, "query1=1&query2=2");
        assertEquals(path + "?" + query, file);
        assertEquals(fragment, ref, "fragment");
    }

    /**
     * Purpose: Check the url with some missing parts
     * Input: "http://[1111:1111:1:2::4]/imgDir/someImg.png"
     * Expected:
     *      protocol        http
     *      user_info       null
     *      host            [1111:1111:1:2::4]
     *      port            -1
     *      authority       [1111:1111:1:2::4]
     *      path            /imgDir/someImg.png
     *      query           null
     *      file            /imgDir/someImg.png
     *      fragment, ref   null
     */
    public void testGetUrlPartMissingParts() {
        String someUrl = "http://[1111:1111:1:2::4]/imgDir/someImg.png";

        String protocol = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.PROTOCOL);
        String user_info = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.USER_INFO);
        String host = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.HOST);
        String port = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.PORT);
        String authority = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.AUTHORITY);
        String path = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.PATH);
        String query = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.QUERY);
        String file = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.FILE);
        String ref = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.REF);
        String fragment = UrlUtils.getUrlPart(someUrl, UrlUtils.UrlPart.FRAGMENT);

        assertEquals(protocol, "http");
        assertNull(user_info);
        assertEquals(host, authority, "[1111:1111:1:2::4]");
        assertEquals(port, "-1");
        assertEquals(path, file, "/imgDir/someImg.png");
        assertNull(query);
        assertNull(fragment, ref);
    }

    /**
     * Purpose: Case when urls are empty or null
     * Input: ("", ""), (null, null), (null, "")
     * Expected:
     *      ("", ""), (null, null): true
     *      (null, "")            : false
     */
    public void testAreUrlsEqualEmptyOrNull() {
        assertTrue(UrlUtils.areUrlsEqual("", ""));
        assertTrue(UrlUtils.areUrlsEqual(null, null));
        assertFalse(UrlUtils.areUrlsEqual(null, ""));
    }

    /**
     * Purpose: Case when same url with different way of expression
     * Input: ("http://github.com",     "https://github.com")
     *        ("https://github.com",    "https://github.com/")
     *        ("github.com",            "https://www.github.com/")
     * Expected: true
     */
    public void testAreUrlsEqual() {
        String httpUrl = "http://github.com";
        String httpsUrl = "https://github.com";
        assertTrue(UrlUtils.areUrlsEqual(httpUrl, httpsUrl));

        String noSlashUrl = "https://github.com";
        String slashUrl = "https://github.com/";
        assertTrue(UrlUtils.areUrlsEqual(noSlashUrl, slashUrl));

        String pureUrl = "github.com";
        String wwwUrl = "https://www.github.com/";
        assertTrue(UrlUtils.areUrlsEqual(pureUrl, wwwUrl));
    }

}
