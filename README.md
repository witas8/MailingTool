# MailingTool
Program for sending multiple emails. Tool dedicated for Business Developers.

To send emails it is necessary to fill the excel file called Mailing List. There are 4 separeted sheets - "Companies" where are receivers' addresses, "Black List" where are addresses that emails cannnot be delivered to, "Domains" where are email domains that we do not want to send emails and "User" where are login and password. The domains and the black list parts are separated to avoide the situation that we send an email to the general address, but we have a contact with a particular person. The emails will not be delivered to any receivers if their domains are the same as those in the domain sheet ex. - gmail.com. Provide domain witout @.

To create the text of an email it is needed to go to "Mail Content" txt file. There you will be asked to provide the subject in the <> bruckets as <Your title text>. Next, the mail content can be written, however it is needed to put semicolon ";" if you want to have a new line. Don't worry the semmicolum will not be added to the final email text content.
  
Creare the folder with these two files - excel xls (not xlsx) and txt. Pass the path to the variable "path" in the "DataProvider" class.

The mail text example: 
<The cooperation proporsal>
Hello,
my name is Mikolaj and I saw that Java is your field of interests.;
You will not believe, but it is my too! Please tell me how did you start your Java adventure.;
;
Best Regards,
Mikolaj
