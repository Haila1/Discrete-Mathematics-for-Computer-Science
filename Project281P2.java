import java.util.*;
  import static java.lang.Math.*;
    public class Project281P2{
     public static Scanner input = new Scanner(System.in) ;
      public static void main(String[]args) {
      
      long num ; int ite;long S_miller,E_miller,S_fermat, E_fermat;
      System.out.println("Enter the candidate prime number:");
      num=input.nextLong();
      System.out.println("Enter the certainty parameter:");
      ite=input.nextInt();
      
      S_miller= System.nanoTime();
      System.out.println("Using the Miller-Rabin primality test the numbers is: "+millerRabinIsPrime(num,ite));
      E_miller= System.nanoTime()-S_miller;
      System.out.println("time: "+E_miller+" nano seconds.");
      
      System.out.println("-----------------------------------");
      
      S_fermat= System.nanoTime();
      System.out.println("Using the Fermat primality test the numbers is: "+Fermat(num,ite));
      E_fermat= System.nanoTime()-S_fermat;
      System.out.println("time: "+E_fermat+" nano seconds.");
      }
      
       public static String millerRabinIsPrime(long num , int ite){
       
       if( num == 2 || num == 3) 
       return "prime" ;
       else if ( num == 1 || num % 2 == 0  )
       return "composite" ;
       
       else{
       int r = 0 ;
       while((num-1) % Math.pow(2,r+1) == 0) //The loop will check if dividing (num-1 / 2*r) is intger(reminder = 0 )
        r++;
        
       long d = (num-1) /(long) Math.pow(2,r);
       
       //num-1=a^d*(2^r)
       while( ite != 0 ){

       long random_num = (long) ((Math.random() * ((num-2) - 2)) + 2);//random number from [2,(num-2)]
       long sigma=0;
       
       sigma=modExp1(random_num , d , num );//random^d mod num (r is zero) to check the first element
       if( sigma==1|| sigma==(num-1)) 
         return "probably prime";
         
       int s;//counter->r
       //trying the remaining elements
       for(s=0;s<r;s++){
       sigma= modExp1(sigma , (long)2 , num ); //((sigma^(d))^2) mod num
       
       if(sigma==1)
            return "composite";
       if(sigma==(num-1))
         break;
       }
       if(r==s) return "composite";// num will be composite if sigma==1 or we reached the end of the loop and sigma didnt equal 1 or n-1
       ite--;
       }//end while
       return "probably prime";// we reach this when sigma=num-1 or anything else
      }//end else 
      
      }
      
         
         public static String Fermat(long n,int ite){
         if(n == 1){ // 1 isn’t prime
         return "composite";
         }
         
         for(int i=0;i<ite;i++){
         // random int between 1 and n-1 ( inclusive )
         long a = (long) ((Math.random() * (n - 1)) + 1);
         
         if(modExp1(a,n-1,n) != 1){//checking the last element a^d*2^d mod n or a^(n-1) mod n
         return "composite"; // n is composite 
         }
         }
         
         return "probably prime"; // n is probably prime
         }
         
         static long modExp1(long b,long e,long n){
         long result=0;
         
         if(e==0)
            return 1;
         
         else if(e%2==0){
            result=modExp1(b, e/2, n);
            return ((result * result)%n);
         }//if
         else{
            result=modExp1(b, e-1, n);
            
         }//else 
         return  (b * result)%n;
        
         }//end method1
      
}