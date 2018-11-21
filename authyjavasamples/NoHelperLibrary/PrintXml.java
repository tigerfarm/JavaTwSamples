/*
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

public class PrintXml {

    public void pretty(String xmlData) {
        int si;
        int ei;
        int noDataItem;
        int endDataItem;
        int nextDataItem;
        int responseItem;
        String tabString = "  ";
        int tabStringLength = tabString.length();
        String indentString = "";

        // System.out.println("++ Print: " + xmlData); 
        si = xmlData.indexOf("<", 0);
        ei = xmlData.indexOf(">", 0);
        if (si < 0 || ei < 0) {
            System.out.println("-- Error: response is Not XML data.");
            System.out.println("+ Response text: " + xmlData + "...");
            return;
        }
        while (ei > 0) {
            noDataItem = xmlData.indexOf("/>", si);
            responseItem = xmlData.indexOf("<?", si);
            endDataItem = xmlData.indexOf("</", si);
            nextDataItem = xmlData.indexOf("<", ei + 1);
            // System.out.println("si="+si+", ei="+ei+", endDataItem="+endDataItem + ", nextDataItem="+nextDataItem); 
            if (ei == noDataItem + 1) {
                // Case, no data in the item: <soapenv:Header/> 
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            } else if (responseItem == 0) {
                // Case, response heading: <?xml version='1.0' encoding='UTF-8'?> 
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            } else if (si == endDataItem) {
                // Case, data item: ></v2:values> 
                indentString = indentString.substring(0, indentString.length() - tabStringLength);
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            } else if (ei == nextDataItem - 1) {
                // Case, data item: <soapenv:Body><v2:runReport> 
                System.out.println(indentString + xmlData.substring(si, ei + 1));
                indentString += tabString;
            } else {
                // Case, data item: <v2:attributeFormat>csv</v2:attributeFormat> 
                ei = xmlData.indexOf(">", nextDataItem);
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            }
            si = ei + 1;
            ei = xmlData.indexOf(">", si);
        }
    }

    public static void main(String[] args) throws Exception {

        final String THECLASSNAME = "PrintXml";

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        System.out.println("++ Print the SOAP XML request string.");
        String soapEnvBody = ""
                + "<soap:findFlight>"
                + "<destination>LAX</destination>"
                + "</soap:findFlight>";
        String namespacePrefix = "soap";
        String xmlRequest = ""
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:" + namespacePrefix + "=\"http://tigerfarmpress.com/" + "\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + soapEnvBody
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        PrintXml doPrintXml = new PrintXml();
        doPrintXml.pretty(xmlRequest);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }

}
