/*
 * CSCI1130 Java Exercise
 *
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and
 * regulations on honesty in academic work, and of the disciplinary
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 *
 * University Guideline on Academic Honesty:
 *   http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *   https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 * Section     : CSCI 1130 A 
 * Student Name: Rishika Bajaj
 * Student ID  : 1155138397
 * Date        : 02.11.20
 */
package quadraticquiz;
import javax.swing.JOptionPane;
import java.util.Random; 

/**
 *
 * @author Rishika Bajaj
 */
public class QuadraticQuiz { 
    
    public static void main(String[] args) {
        
        PanelDisplay score = new PanelDisplay();
        score.getScore();
        String title;
        int point = 0;
        
        for(int counter = 1; counter <= 3; counter++){ //passing objects for trial questions
            
            title = "Trial"+Integer.toString(counter);
            if(counter==1){ 
                
                QuadraticQuestion t1 = new QuadraticQuestion(title,-2,2);
                t1.getUserInputAnswer();
                
            }
            
            if(counter==2){
                
                QuadraticQuestion t2 = new QuadraticQuestion(title,-1);
                t2.getUserInputAnswer();
                
            }
            
            if(counter==3){
                
                QuadraticQuestion t3 = new QuadraticQuestion(title,5,0);
                t3.getUserInputAnswer();
                
            }
        }
        
        for(int counter =1; counter<=6; counter++){
            
            title = 'Q'+Integer.toString(counter);
            Random rand = new Random();
            int numOfRoots =  rand.nextInt(3);                                   //generates random number between [0,2]
            int root1= rand.nextInt(11) - 5;                                     //generating random root
            int root2= rand.nextInt(11) - 5;                                     //generating random root
            int root= rand.nextInt(11) - 5;                                      //generating random root
            
            if (numOfRoots ==2){            
                QuadraticQuestion q1 = new QuadraticQuestion(title, root1, root2); //calling constructor
                q1.getUserInputAnswer();
                
                if((q1.checkAnswer()==true)){
                    point++;                                                     //increasing score for correct answer
                score.setScore(point);
                }           
            }
            
            if (numOfRoots ==1){
                QuadraticQuestion q2 = new QuadraticQuestion(title, root);        //calling constructor
                q2.getUserInputAnswer();
                
                if((q2.checkAnswer()==true)){
                    point++;                                                     //increasing score for correct answer
                score.setScore(point);
                }
            } 
            
            if (numOfRoots ==0){
                QuadraticQuestion q3 = new QuadraticQuestion(title);             //calling constructor
                q3.getUserInputAnswer();
                
                if((q3.checkAnswer()==true)){
                    point++;                                                     //increasing score for correct answer
                  score.setScore(point);
                }
            }
            if(counter==6){
                System.out.println("The End");                                   //intimating user the quiz is over after 6 questions
            }
        }    
    }
}

class QuadraticQuestion {
    int numOfRoots;
    int root1, root2;                                                           
    int B, C; 
    String choice = " ";
    String type;
    String equation;
    
    public QuadraticQuestion(String title, int r1, int r2){
        
        this.numOfRoots=2; 
        this.root1= r1;
        this.root2= r2;
        this.type = title;
        
        if((r1<=5)&&(r1>=-5)&&(r2<=5)&&(r2>=-5)){ //to check if the roots are in valid range
            
            this.B = -(r1+r2);                    //calculating the value of coefficients
            this.C = r1*r2;
        }
        
        else {
            
            this.root1= 1;                  //assigning default values to coefficients if out of range
            this.root2= 2;
            this.B = -3;
            this.C = 2;
            
        }
    }
    
    public QuadraticQuestion(String title, int r){
        
        this.numOfRoots=1; 
        this.root1= r;
        this.root2= r;
        this.type = title;
        
        if((r<=5)&&(r>=-5)){                            //check if the roots are in valid range
           
            this.B = -(2*r);                            //calculating the value of coefficients
            this.C = (r*r);
        }
        
        else {
            
            this.root1= 1;                              //assigning default values to coefficients if out of range
            this.root2= 1;
            this.B = -2;
            this.C = 1;
            
        }
    }
    
    public QuadraticQuestion(String title){ 
        
        this.numOfRoots=0;
        this.type = title;
        int D=0,e = 0,f = 0;
        Random rand = new Random();
        D = e*e - 4*f;
        if(D>=0){                                       //generates random values until B and C satisfy (B*B - 4*(1)*C)< 0
        e =(int)(rand.nextInt(21)-10); 
        f =(int)(rand.nextInt(50)+1);
        }
        this.B = e; 
        this.C = f; 
            
    }
    
    public String getUserInputAnswer() {                       //used to get the input from users after the equation is created and displayed 
        
        String b =  Integer.toString(this.B);
        String c =  Integer.toString(this.C);
        if((this.B==0)||(this.C==0)){
            if((this.B==0) && (this.C==0)){
                this.equation = "x^2 = 0";                          //creating equations based on different values of B and C
            }
            else{
                if(this.B==0){
                    this.equation = "x^2"+c+" = 0";
                }
                if(this.C==0){
                    this.equation = "x^2"+b+"x = 0";
                }    
            }    
        }
        else{
            if(this.B>0){
                if(this.C>0){
                    this.equation = "x^2+"+b+"x+"+c+" = 0";
                }
                else{
                    this.equation = "x^2+"+b+"x"+c+" = 0";
                }
            }
            if(this.B<0){
                if(this.C>0){
                    this.equation= "x^2"+b+"x+"+c+" = 0";
                }
                else{
                    this.equation="x^2"+b+"x"+c+" = 0";
                }

            }
        }
        if (this.choice != null){
            System.out.println(this.printString());
        }
        this.choice = JOptionPane.showInputDialog(type+": "+equation+", how many real roots?","<type [0-2] here>");
        null_(this.choice);
        return this.choice;    
    }
    
     public void null_(String string){                    //used to repeat function if the user presses cancel or close in dialogue box
        while (this.choice == null){
             getUserInputAnswer();
        }
     } 
    
    
    public boolean checkAnswer(){
        String answer = Integer.toString(this.numOfRoots);   //checks if the answer by user is correct
        boolean correct = false;
        if(choice.equals(answer)){
            correct = true;
        }
        return correct;
    }
    
    public String printString() {
        String num = Integer.toString(this.numOfRoots);        //prints the equation, number of roots and the real roots in the console
        if (this.numOfRoots==2){
            String root_1 = Integer.toString(this.root1);
            String root_2 = Integer.toString(this.root2);
            return(this.equation+", "+num+" real roots: "+root_1+", "+root_2);
        }
        else if (this.numOfRoots==1){
            String root_1 = Integer.toString(this.root1);
            return(this.equation+", "+num+" real root: "+root_1);
        }
        else{ 
            return(this.equation+", "+num+" real roots."); 
        }
    }
}

//class PanelDisplay has been added in the source file 




