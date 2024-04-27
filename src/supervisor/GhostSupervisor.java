package supervisor;

import characters_classes.CyanGhost;
import characters_classes.OrangeGhost;
import characters_classes.PinkGhost;
import characters_classes.RedGhost;

public class GhostSupervisor implements Runnable{
    protected RedGhost r;
    protected CyanGhost c;
    protected PinkGhost p;
    protected OrangeGhost o;
    public GhostSupervisor(RedGhost r, CyanGhost c, PinkGhost p, OrangeGhost o){
        this.r=r;
        this.c=c;
        this.p=p;
        this.o=o;
    }
    public void setAllStatus(int n){
        r.setStatus(n);
        c.setStatus(n);
        p.setStatus(n);
        o.setStatus(n);
    }
    public int getAStatus(){
        return (r.getStatus());
    }
    public void run(){
        while(true){
            if(getAStatus()==1){
                System.out.println("scatter");
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException ignored) {}
                System.out.println("finescatter");
                setAllStatus(0);
            }else if(getAStatus()==0){
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException ignored) {}
                setAllStatus(1);
            }
        }


    }

}
