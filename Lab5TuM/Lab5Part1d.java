/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5part1d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author maitu
 */
public class Lab5Part1d {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inData = null;
        char CType;
        String Title = "", Series ="";
        int SNum;
        String Media1 = "" , Media2 = "" , Media3 = "";
        int MYear1 = 0 , MYear2 = 0, MYear3 = 0 ;
        char BkCov = ' ';
        double BkPr = 0.0;
        boolean dataValid = true;        
        String Book = "", Movie = "", TV = "";        
        String tempD;
        Scanner input = new Scanner(System.in);
        int counter = 1;
        
        /*String[] Titles = new String[80];
        int[] SNums = new int[80];
        String [] SNames = new String[80];
        double[] Prices = new double[80];
        */
        
        String[][] names = new String [15][3];
        int[][] years = new int[15][3];
        double[] BkPrices = new double[15];
        String[] BookCoverType = new String [15];
        int[] seriesNum = new int[15];
        String[] linedata = new String[8];
        
        
        //initialize array
        for (int k=0; k<15 ;k++)
        {
            names[k][0]="";
            names[k][1]="";
            names[k][2]="";
            
            years[k][0]=-1;
            years[k][1]=-1;
            years[k][2]=-1;
            
            BkPrices[k]=-1.0;
            BookCoverType[k]="";
            seriesNum[k]=-1;
          
        }
        for( int k = 0; k < 8; k++)
        {
            linedata[k]="";
        }
        
        boolean eva = false;
        System.out.println("Enter file name: ");

        while(!eva)
        {
            Scanner sc = new Scanner(System.in);
            String dataFile = sc.next();
            File inputData = new File (dataFile);

            try
            {
                inData = new Scanner(inputData);
                eva = true;
            }
            catch (FileNotFoundException fnfe)
            {
                inData = new Scanner(System.in);
                System.out.println("Input File " + dataFile + " was not found. Please Enter Correct file name: " );
            }  
        }


                
        
        
        String inString ="";
   
        
        do
        {           
            dataValid = true;
            inString = inData.nextLine();
            Scanner data = new Scanner(inString);
            tempD = data.next();
            CType = tempD.charAt(0);
            if ( CType != 'F' && CType != 'S' && CType != 'E')
            {
                dataValid = false;
            }
                       
            data.useDelimiter("[0-9]");
            Title = data.next();
            data.reset();
            SNum = data.nextInt();
            
            data.useDelimiter(":");
            Series = data.next();
            data.reset();   
            data.next(); //remove the colon :
            
            String strBMT = data.nextLine();
            //System.out.println(strBMT);
            String[] strBMTarray =strBMT.split("[ ]+");
            
   
            for (int i=0; i<strBMTarray.length; i++)
            {
            if (strBMTarray[i].equals("Book"))
            { 
                try
                {
                years[counter-1][0] = Integer.parseInt(strBMTarray[i+1]);
                }
                catch (NumberFormatException ime)
                {
                       dataValid = false;     
                }
                BookCoverType[counter-1]=strBMTarray[i+2];
               // System.out.println(Double.parseDouble(strBMTarray[i+3]));
                if(Double.parseDouble(strBMTarray[i+3]) < 0)
                {   
                    //System.out.println("Book price is less than 0.0");
                    BkPrices[counter-1] = 0;
                }  
                else
                {
                BkPrices[counter-1] = Double.parseDouble(strBMTarray[i+3]);   
                }
               
                //BkPrices[counter-1] = Double.parseDouble(strBMTarray[i+3]);
                //System.out.println("*******"+years[counter-1][0]);
                
            }
            else if (strBMTarray[i].equals( "Movie"))
            {
             years[counter-1][1] = Integer.parseInt(strBMTarray[i+1]);
                //System.out.println("******"+years[counter-1][1]);
  
            }
            else if (strBMTarray[i].equals("TV"))
            {
             years[counter-1][2] = Integer.parseInt(strBMTarray[i+1]);
             //System.out.println("******"+years[counter-1][2]);
  
            }
           
            }
            
           

            
            names[counter-1][0]= Title;
            names[counter-1][1]=Series;
            names[counter-1][2]= Character.toString(CType);
            seriesNum [counter-1]=SNum;
            BkPrices[counter-1]=BkPr;
            counter++;
          
            
        }
        while ( inData.hasNext());
        
       
       // printarray(names,years,seriesNum);
        //searchArray(names);
        String[] titles = new String[15];
        for (int i=0; i<15;i++)
        {
            titles[i]=names[i][0];
        }
        System.out.println("\nBefore add titles");
        sortArray(titles);
        printTitles(titles,titles.length);
    
        addTitle(titles,titles.length);
        
        
    }
    
    public static void printData(char CType, String Title, int SNum, String Series, String Media1, int MYear1, char BkCov, double BkPr, String Media2, int MYear2, String Media3, int MYear3, boolean dataValid)
    {
        if(dataValid)
        {
            System.out.printf("%10s",CType);
            System.out.printf("%40s", Title);
            System.out.printf("%5s",SNum);
            System.out.printf("%45s",Series);
            System.out.printf("%20s",Media1);
            System.out.printf("%20s",(MYear1 > 0 ? MYear1 : " "));
            System.out.printf("%20s", BkCov);
            System.out.printf("%20s", (BkPr));
            System.out.printf("%20s",Media2);
            System.out.printf("%20s",(MYear2 > 0 ? MYear2 : " "));
            System.out.printf("%20s",Media3);
            System.out.printf("%20s",(MYear3 > 0 ? MYear3 : " "));
        }
        else
        {
            System.out.printf("Error was found.");
        }
        
    }
    public static void orderingData(String[] Tittle, int[] SNum, String[] SNames, double[]prices )
{
for ( int i=0;i<80;i++)
{
    int CURseries = i+1;
    for (int j = 0; j<80;j++)
    {
      if (SNum[j] == CURseries)
    {
        int foundindex = j+1;
        System.out.println("Series number " +CURseries+" found at index = "+foundindex+" Tittle is "+Tittle[j]+" seires name is "+ SNames[j] +" costs "+prices[j]);  
    }
}
}

    
        
    }
    public static void printarray(String[][] names, int[][] years,int[] seriesNum)
    {
        System.out.printf("%45s%15s%15s%15s%15s\n","Title","SeriesNum","Book","Movie","TV");
        for (int k=0; k<15;k++)
        {
            if(names[k][0] != "")
            {
                System.out.printf("%45s",names[k][0]);
                System.out.printf("%15s",seriesNum[k]);
                System.out.printf("%15s",(years[k][0] > 0 ? years[k][0] : " " ));
                System.out.printf("%15s",(years[k][1] > 0 ? years[k][1] : " " ));
                System.out.printf("%15s",(years[k][2] > 0 ? years[k][2] : " " ));
                
            }
            System.out.println("\n");
        }
        
    }
    public static int searchArray (String[][]names)
    {
        System.out.println("Please enter one title you want to search for");  
        Scanner sc = new Scanner(System.in);
        String searchString = sc.nextLine();
        int foundrow = -1;
        for (int i=0; i<15;i++)
        {
            if ((names[i][0]).trim().compareTo(searchString) == 0)
            {   foundrow = i+1;
                System.out.println(searchString + " was found at line "+ foundrow);
                
            }
        }
        if (foundrow == -1)
        {
            System.out.println(searchString+" was not found in the array");
        }
        return foundrow;     
    }
     public static void sortArray0 (String[]titles)
    {
        String pr = "";
        for (int i = 0; i < titles.length-1; i++)
            for (int pass = 0; pass < titles.length-1; pass++)
            { 
                if (titles[pass].compareTo(titles[pass+1]) > 0)
                {   // swap                    
                    pr = titles[pass];
                    titles[pass] = titles[pass+1];
                    titles[pass+1] = pr;   
                          
                }
            }
    }
    public static void sortArray (String[]titles)
    {  
        System.out.println(titles.length);
        String pr = "";
        for (int i = 0; i < titles.length-1; i++)
            for (int pass = 0; pass < titles.length-1; pass++)
            { 
                if (titles[pass].compareToIgnoreCase(titles[pass+1]) > 0)
                {   // swap                    
                    pr = titles[pass];
                    titles[pass] = titles[pass+1];
                    titles[pass+1] = pr;   
                          
                }
            }
    }
    public static void printTitles(String[]titles, int counter)
    {
        for (int i =0; i < counter; i++)
        {
            System.out.println(titles[i]);
        }
    }
            
    
    public static int addTitle(String[] Titles, int count)
    {
        System.out.println("Please enter the title that you want to add");
        Scanner sc = new Scanner(System.in);
        String addedTitle = sc.nextLine();
        
        for (int h=0;h<Titles.length;h++)
        {
            if (Titles[h].trim().equals(addedTitle.trim()) )
            {
                System.out.println("The title you want to add has already existed");
                return count;
         
            }
        }
        sortArray0(Titles);
        System.out.println("After add titles");
        if (!Titles[Titles.length-1].trim().equals("~"))
                {
                    System.out.println("The array is full, cannot add more titles");
                    return count;
                }
        int index= -1;
        boolean found = false; 
        for (int i=0; i<15; i++)
        {
            if (Titles[i].trim().compareTo(addedTitle) > 0 ) 
            { 
                if (found == false)
                {
                    index = i;
                    found = true; 
                }


            }

        }
        //move row 1 down for new entry
        for (int l=Titles.length-1;l>index;l--)    
        {
          Titles[l] = Titles[l-1];  
        }
        Titles[index] = addedTitle;
        printTitles(Titles,Titles.length);
        //System.out.println(index);
        return count+1;
    }
}
    
    
    

