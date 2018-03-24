package lab3;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PoLson
 */
public class ConsumatorThread extends Thread{
    private LinkedList<Integer> coada;
    private Object coadaLibera,coadaPlina;
    ConsumatorThread(LinkedList<Integer> coada, Object coadaLibera, Object coadaPlina){
         this.coada = coada;
         this.coadaLibera = coadaLibera;
         this.coadaPlina= coadaPlina;
    }
    public void run(){
         while(true){
             int lungimeCoada;
             synchronized(coada){
                 lungimeCoada = coada.size();
             }
              while (lungimeCoada == 0){
                  try{
                    synchronized (coadaLibera) {
                        coadaLibera.wait();
                    }
                  }catch (Exception exception)
                  {
                      System.out.println(exception);
                  }
                }
              synchronized(coada){
                 if(coada.size() > 0)
                 {
                     coada.remove();
                 }
                 for(int i=0;i<coada.size(); i++){
                     System.out.print(coada.get(i)+" ");
                 }
                 System.out.print("\n");  
             }
            synchronized (coadaPlina) {
                coadaPlina.notify();
            }
            try {
                sleep(200);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
         }
    }    
}