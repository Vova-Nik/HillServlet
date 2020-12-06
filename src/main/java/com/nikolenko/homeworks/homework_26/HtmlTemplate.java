package com.nikolenko.homeworks.homework_26;

public class HtmlTemplate {
    private final static String head =
            "<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "<meta charset=\"UTF-8\">" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" +
                    "<title>World Database</title>"+
                    "</head>";


    private final static String bodyBeforeContent = "<body>" +
            "    <div class=\"btn-container\">" +
            "        <div class=\"button\">" +
            "            <a class=\"ref\" href=\"/myApp/hello\">Main page</a>" +
            "        </div>" +
            "        <div class=\"button\">" +
            "            <a class=\"ref\" href=\"/myApp/city\">Cities</a>" +
            "        </div>" +
            "        <div class=\"button\">" +
            "            <a class=\"ref\" href=\"/myApp/country\">Countries</a>" +
            "        </div>" +
            "    </div>" +
            "    <div class=\"content\">";


    private final static String bodyAfterContent = " </div>" +
            "</body>" +
            "</html>";

    public static String getbodyBeforeContent() {
        return bodyBeforeContent;
    }

    public static String getbodyAfterContent() {
        return bodyAfterContent;
    }

    public static String getHead() {
        return head;
    }

    public static StringBuilder getBody(StringBuilder content) {
        return new StringBuilder(bodyBeforeContent).append(content).append(bodyAfterContent);
    }
}
