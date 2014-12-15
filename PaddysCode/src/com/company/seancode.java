package com.company;
class	seancode

{
    public static void main(String[] args)
    {
        //varibles
        char	upperlist;
        char	lowerlist;
        char	currentchar;
        int		words;
        String	currentword;
        String	line;
        int	space;
        int	index;

        System.out.print("enter line of words ");
        line = EasyIn.getString();
        index=0;
        space= line.indexOf(" ");
        words=0;
        currentword=" ";
        currentchar= 'i';
        while(space >-1)

        {

            currentword = line.substring(0,space);
            for(index=0;index<currentword.length();index++)
            {
                currentchar=line.charAt(index);
                if(currentchar >='A' &&currentchar <='Z')
                {
                    System.out.println(currentword+",");
                    words++;
                }

                else if(currentchar>='a' &&currentchar<='z')
                {
                    System.out.println(currentword+",");
                    words++;
                }
            }
            line=line.substring(space+1);
            currentword=line.substring(space+1);
            space= line.indexOf(" ") ;

        }
        //   if(currentword >='A' &&currentword <='Z' &&currentword<='a' &&currentword>='z')
        {
            System.out.println(currentword+",");
            words=words+1;
        }
        //   if(<='a' &&>='z')
        System.out.println("the number of vaild words is "+words);



    }
}