
package com.plants.homework;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author USER-M
 */

public class Plants extends ArrayList<Plant>{
    
    
    public void fileLoading() throws FileNotFoundException
    {
        
        Scanner reader = new Scanner(HomeWork.file);
       
        
        while(reader.hasNextLine()){
            String str= reader.nextLine();
            String arr[]=str.split(",");
            try{this.addPlant(arr[0].trim(), arr[3].trim(), arr[1].trim(), arr[2].trim());}
            catch(Exception e){}
        }
                
    }
    
    
    public void addPlant(String name, String date, String id, String size ){
        Date plantDate =null;
        long plantID=0;
        double plantSize=0;
        
        try{
        String dates[] = date.split("/");
        
        int year = Integer.parseInt(dates[2].trim());
        int month = Integer.parseInt(dates[1].trim());
        int day = Integer.parseInt(dates[0].trim());
        
        plantDate = new Date();
        
        plantDate.setYear(year);
        plantDate.setMonth(month);
        plantDate.setDate(day);
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter a vaild date."); 
            return;
        }
        
        try{
            plantID = Long.parseLong(id.trim());
            plantSize = Double.parseDouble(size.trim());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter a number without any characters in SIZE and NUMBER fields");
            return;
        }
        
        this.add(new Plant(name ,plantDate ,plantID ,plantSize));
        
    }
    
    //-------------------------------------------------------------------------------------------------
    //Search 
        public Plant search(String name, String date, String id, String size  ){
            
            this.trimToSize();
          
            for(int i=0 ; i<this.size() ; i++ ){
            
                
                if(name.equals("") && date.equals("") && id.equals("") && size.equals("")){
                   return null; 
                }
                else if(name.equals("") && date.equals("") && size.equals("")){
                    if(String.valueOf(this.get(i).getID()).equals(id))
                        return this.get(i);
                }    
                else if(name.equals("") && id.equals("") && size.equals("")){
                    if(this.get(i).getDate().equals(date))
                        return this.get(i);
                }
                else if( date.equals("") && id.equals("") && size.equals("")){
                    if(name.equals(this.get(i).getName()))
                        return this.get(i);
                }
                else if(name.equals("") && date.equals("") && id.equals("") ){
                    if( Double.parseDouble(size)==this.get(i).getSize())
                        return this.get(i);
                }
                //name.equals("") && date.equals("") && id.equals("") && size.equals("")
                //name and date equality check
                else if(  id.equals("") && size.equals("")){
                    if(name.equals(this.get(i).getName()) && date.equals(this.get(i).getDate()) )
                        return this.get(i);
                    
                }
                //name and id equality check
                  else if( date.equals("") &&  size.equals("")){
                    if(id.equals(String.valueOf(this.get(i).getID())) && name.equals(this.get(i).getName()) )
                        return this.get(i);
                }
                  //name and size equality check
                   else if(date.equals("") && id.equals("")){
                    if(name.equals(this.get(i).getName()) && Double.parseDouble(size)==this.get(i).getSize() )
                        return this.get(i);
                }
                   
                //date and id equality check
                else if(name.equals("") && size.equals("")){
                if(date.equals(this.get(i).getDate()) && id.equals(String.valueOf(this.get(i).getID())))
                    return this.get(i);
                }
                //date and size equality check
                else if(name.equals("") &&  id.equals("") ){
                      if(date.equals(this.get(i).getDate()) && Double.parseDouble(size)==this.get(i).getSize() )
                        return this.get(i);
                }
               //id and size equality check
                else if(name.equals("") && date.equals("") ){
                      if(Double.parseDouble(size)==this.get(i).getSize() && id.equals(String.valueOf(this.get(i).getID())))
                        return this.get(i);
                }
                //name and date and id check
                else if( size.equals("")){
                    if(
                    date.equals(this.get(i).getDate()) && name.equals(this.get(i).getName()) && String.valueOf(this.get(i).getID()).equals(id)
                    )
                        return this.get(i);
                    
                }
            
           
                else if( id.equals("") ){
                     if(
                    date.equals(this.get(i).getDate()) && name.equals(this.get(i).getName()) && Double.parseDouble(size)==this.get(i).getSize()
                    )
                        return this.get(i);
                }
                else if(name.equals("")){
                     if(
                    date.equals(this.get(i).getDate()) && id.equals(String.valueOf(this.get(i).getID())) && Double.parseDouble(size)==this.get(i).getSize()
                    )
                        return this.get(i);
                }
                else if(date.equals("")){
                           if(
                    name.equals(this.get(i).getName()) && id.equals(String.valueOf(this.get(i).getID())) && Double.parseDouble(size)==this.get(i).getSize()
                    )
                        return this.get(i);
                }
                else {
                   
                               if(
                    name.equals(this.get(i).getName()) && date.equals(this.get(i).getDate()) && Double.parseDouble(size)==this.get(i).getSize() && id.equals(String.valueOf(this.get(i).getID())) 
                    )
                        return this.get(i);
                }
                    
            }
            
            return null;
    }
    //-------------------------------------------------------------------------------------------------
        
    public void updateFile(){
        try{
            this.trimToSize();
            FileWriter writer = new FileWriter(HomeWork.file);
            for(int i =0 ; i <this.size(); i++){
                writer.write(
                this.get(i).getPlantData()+"\n"
                );
            }
            writer.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occured during updating.");
        }
    } 

    boolean notExist(String e) {
        if(this.isEmpty()) return true;
        for(Plant temp : this){
            System.out.println(temp.getID());
            if(String.valueOf(temp.getID()).equals(e)){
                return false;
            }
        }
        
        return true;
    }

  
    
}


 class Plant {
    private String name;
    private Date date;
    private long id;
    private double size;
    
    public Plant(String name ,Date date ,long id ,double size){  
      
        this.name=name;
        this.date = date;
        this.id = id;
        this.size = size;
    }

    public String getPlantData(){
        return this.getName()+" , "+this.getID()+" , "+this.getSize()+" , "+this.getDate();
    }
    
    
    public void setName(String n){
        name = n;
    }
    public void setDate(Date d){
        date = d;
    }
    public void setDate(String date){
         Date plantDate =null;
         
         try{
        String dates[] = date.split("/");
        
        int year = Integer.parseInt(dates[2].trim());
        int month = Integer.parseInt(dates[1].trim());
        int day = Integer.parseInt(dates[0].trim());
        
        plantDate = new Date();
        
        plantDate.setYear(year);
        plantDate.setMonth(month);
        plantDate.setDate(day);
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter a vaild date."); 
            return;
        }
         this.date=plantDate;
    }
    public void setID(long i) {
            
        id =i;
    }
    public void setSize(double s){
        size = s;
    }
    
    public String getName(){
        return name;
    }    
    public String getDate(){
        return date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
    }
    public long getID(){
        return id;
    }    
    public double getSize(){
        return size;
    }
    
}
