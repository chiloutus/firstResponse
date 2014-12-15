package com.company;

/**
 * Created by GaryPC on 10/12/2014.
 */
class	lab51

{
    public static void main(String[] args)
    {
        //varibles
        char	currentchar;
        int		words;
        String	currentword;
        String	line;
        int	space;
        int	index;
        boolean allOk = false;


        //input
        System.out.print("enter line of words ");
        line = EasyIn.getString();
        space= line.indexOf(" ");
        words=0;
        currentchar= 'i';

        while(line.length() > 0)

        {
            //we grab the word
            space = line.indexOf(" ") ;
            //if the index of the next space is not -1 then we continue as normal
            if(space != -1) {
                currentword = line.substring(0, space);
            }
            //if it -1 then we need to grab the last word and then set line to "" so its length is 0, breaking the loop
            else{
                currentword = line;
                line = "";
            }


            for(index=0;index<currentword.length();index++)
            {
                //flag boolean here that gets set to false if we find a non alphabetical character
                allOk = true;
                currentchar=currentword.charAt(index);
                if (!((currentchar >='A' && currentchar <='Z') || (currentchar>='a' &&currentchar<='z')))
                {
                    allOk = false;
                }
            }
            //check is the flag still true, if it increment word count and print. We set the flag to false 'cause it prevents strings of length 0 being seen as words
            if(allOk){
                words++;
                System.out.print(currentword+",");
                allOk = false;

            }

            //cut the line
            line= line.substring(space+1);


        }
        //print
        System.out.println("\n" + "the number of vaild words is: "+words);



    }
}