import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class DataProvider {
    String path = "C:\\Users\\witko\\OneDrive\\Pulpit\\MailingTool\\Mailing List.xls";    //excel file
    String textPath = "C:\\Users\\witko\\OneDrive\\Pulpit\\MailingTool\\Mail Content.txt";  //txt file

    public List<String> companiesProvider() throws IOException, BiffException {

        File f = new File(path); //must be xls format
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(0);
        int row = s.getRows();
        List<String> myCompaniesList = new ArrayList<String>();

        for (int i = 1; i <= row - 1; i++) {
            String c = s.getCell(0, i).getContents();
            myCompaniesList.add(c);
        }

        System.out.println("Companies: " + myCompaniesList);
        return myCompaniesList;
    }

    public List<String> blackListProvider() throws IOException, BiffException {

        File f = new File(path); //must be xls format
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(1);
        int row = s.getRows();
        List<String> myBlackList = new ArrayList<String>();

        for (int i = 1; i <= row - 1; i++) {
            String c = s.getCell(0, i).getContents();
            myBlackList.add(c);
        }

        System.out.println("Black List: " + myBlackList);
        return myBlackList;
    }

    public List<String> domainsProvider() throws IOException, BiffException {

        File f = new File(path); //must be xls format
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(2);
        int row = s.getRows();
        List<String> myDomains = new ArrayList<String>();

        for (int i = 1; i <= row - 1; i++) {
            String c = s.getCell(0, i).getContents();
            myDomains.add(c);
        }

        System.out.println("Domains: " + myDomains);
        return myDomains;
    }


    public String loginProvider() throws IOException, BiffException {

        File f = new File(path);
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(3);
        String login = s.getCell(1, 0).getContents();

        return login;
    }

    public String passwordProvider() throws IOException, BiffException {

        File f = new File(path);
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(3);
        String password = s.getCell(1, 1).getContents();

        return password;
    }

    public StringBuilder sb;

    private void reader(){
        this.sb = new StringBuilder();
        File file = new File(textPath);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) {
                sb.append(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    //<tile>
    public String titleProvider() {
        reader();
        String str = sb.toString();
        char[] chars = str.toCharArray();
        StringBuilder mTitle = new StringBuilder();

        for (int i = 0; i != sb.length(); i++) {
            if (chars[i] == '<') {
                while (chars[i] != '>') {
                    mTitle.append(chars[i]);
                    i++;
                }
                mTitle.deleteCharAt(0);
            }
        }

        String mailTitle = mTitle.toString();
        return mailTitle;
    }

    //to get spaces use ;
    public String textProvider () {
        reader();
        String str = sb.toString();
        char[] chars = str.toCharArray();
        StringBuilder mText = new StringBuilder();

        for (int i = 0; i != sb.length(); i++) {

            if (chars[i] == '<') {
                while (chars[i] != '>') {
                    i++;
                }
            }

            if (chars[i] == ';') {
                chars[i] = '\n';
            }
            mText.append(chars[i]);
        }
        mText.deleteCharAt(0);

        String mailText = mText.toString();
        return mailText;
    }
}


