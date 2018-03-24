package lab3;

import java.util.LinkedList;


/**
 *
 * @author PoLson
 */
public class ProducatorThread extends Thread 
{
     private Generator generator;
     private LinkedList<Integer> coada;
     private Object coadaLibera,coadaPlina;

     ProducatorThread(LinkedList<Integer> coada, Object coadaLibera, Object coadaPlina){
            this.coada = coada;
            generator = new Generator(10);
            this.coadaLibera = coadaLibera;
            this.coadaPlina = coadaPlina;
     }
     public void run(){
         while(true){
             int element = (int) generator.next();
             while (coada.size() == 10){
                 try{
                    synchronized (coadaPlina) {
                        coadaPlina.wait();
                    }                  
                 }catch (Exception exception)
                  {
                      System.out.println(exception);
                  }
             }
             synchronized(coada){
                 if(coada.size() < 10)
                 {
                     coada.add(element);
                 }
                 for(int i=0;i<coada.size(); i++){
                       System.out.print(coada.get(i)+" ");
                }
                 System.out.print("\n");
             } 
            synchronized (coadaLibera) {
                coadaLibera.notify();
            }

              try {
                 sleep(100);
             } catch (Exception e) {
                 System.out.print(e);
             }
         }
     }
}