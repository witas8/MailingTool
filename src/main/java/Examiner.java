import java.util.ArrayList;
import java.util.List;

public class Examiner {

    public void coldMailing() throws Exception {
        DataProvider dataProvider = new DataProvider();
        List<String> companies = dataProvider.companiesProvider();
        List<String> blackList = dataProvider.blackListProvider();
        List<String> endings = dataProvider.domainsProvider();

        List<String> copy = new ArrayList<String>(companies);

        companies.removeAll(blackList);

        List<String> helper = new ArrayList<String>();
        for (String address : companies) {
            if(IsBlocked(address, endings))  //whole mail addressed; mails' endings
            {
                helper.add(address);
            }
        }
        companies.removeAll(helper);

        if(copy.equals(companies)) {
            System.out.println("WELL DONE: Cold mailing set is clear! \n");
        }
        else{
            System.out.println("\nWARNING: Forbidden addresses!");
            System.out.println("Cleared list: " + companies +"\n");
        }


        MailBuilder.sendMail(companies);
    }


    public static boolean IsBlocked(String address, List<String> BlackListEndings)
    {
        for(String forbiddenEnding : BlackListEndings)
        {
            if(address.endsWith(forbiddenEnding))
            {
                return true;
            }
        }
        return false;
    }
}
