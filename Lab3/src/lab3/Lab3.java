package lab3;

import java.util.LinkedList;

public class Lab3 {
    private static LinkedList<Integer> coada;
    private static Object coadaLibera,coadaPlina;
    public static void main(String[] args) {
       coadaLibera = new Object();
       coadaPlina = new Object();
       coada = new LinkedList<Integer>();
       Thread thread1 = new ProducatorThread(coada,  coadaLibera,coadaPlina);
       Thread thread2 = new ProducatorThread(coada,  coadaLibera,coadaPlina);
       Thread thread3 = new ConsumatorThread(coada,  coadaLibera,coadaPlina);
       Thread thread4 = new ConsumatorThread(coada,  coadaLibera,coadaPlina);       
       thread1.start();
       thread2.start();
       thread3.start();
       thread4.start();
       try{
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
       }
       catch(Exception e){
           System.out.print(e);
       }
       
    }
    
}