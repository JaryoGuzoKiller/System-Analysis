package midterm;
import genDevs.modeling.*;
import GenCol.*;
import simView.*;

public class proc2 extends ViewableAtomic
{
  
	protected job prime;
	protected double processing_time;

	public proc2()
	{
		this("proc", 20);
	}

	public proc2(String name, double Processing_time)
	{
		super(name);
    
		addInport("in");
		addOutport("out");
		
		processing_time = Processing_time;
	}
  
	public void initialize()
	{
		prime = new job("none",0);
		
		holdIn("passive", INFINITY);
	}

	public void deltext(double e, message x)
	{
		Continue(e);
		if (phaseIs("passive"))
		{
			for (int i = 0; i < x.getLength(); i++)
			{
				if (messageOnPort(x, "in", i))
				{
					prime =(job) x.getValOnPort("in", i);
					
					holdIn("busy", processing_time);
				}
			}
		}
	}
  
	public void deltint()
	{
		if (phaseIs("busy"))
		{
		
			if(isPrime(prime._num))
				System.out.println(prime._num+"은 소수 입니다!");
			else
				System.out.println(prime._num+"은 소수가 아닙니다!");
			holdIn("passive", INFINITY);
		}
	}

	public message out()
	{
		message m = new message();
		if (phaseIs("busy"))
		{
			m.add(makeContent("out", new job("prime"+prime._num,prime._num)));
		}
		return m;
	}

	public String getTooltipText()
	{
		return
		super.getTooltipText()
		+ "\n" + "job: " + prime.getName();
	}
	public static boolean isPrime(int num) {
		if(num==0||num==1)
			return false;
		 for(int i=2; i*i<=num; i++){
	            if(num % i == 0) 
	            	return false;
	        }
	        return true;
	}
}


