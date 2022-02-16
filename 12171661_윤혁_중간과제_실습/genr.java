package midterm;
import simView.*;
import genDevs.modeling.*;
import GenCol.*;

public class genr extends ViewableAtomic
{
	protected job randNum;
	protected double int_arr_time;
	protected int num;
  
	public genr() 
	{
		this("genr", 40);
	}
  
	public genr(String name, double Int_arr_time)
	{
		super(name);
   
		addOutport("out");
		addInport("in");
    
		int_arr_time = Int_arr_time;
	}
  
	public void initialize()
	{
		randNum=new job("none",0);
		num=(int)Math.floor(Math.random()*50);	
		randNum._num=num;
		
		holdIn("active", int_arr_time);
	}
  
	public void deltext(double e, message x)
	{
		Continue(e);
		
	}

	public void deltint()
	{
		randNum=new job("none",0);
		if (phaseIs("active"))
		{
			num=(int)Math.floor(Math.random()*50);
			randNum._num=num;
			
			holdIn("active", int_arr_time);
		}
	}

	public message out()
	{
		message m = new message();
		m.add(makeContent("out", new job("randNum"+randNum._num ,randNum._num)));
		System.out.println("랜덤으로 생성된 숫자 : "+randNum._num);
		return m;
	}
  
	public String getTooltipText()
	{
		return
        super.getTooltipText()
        + "\n" + " int_arr_time: " + int_arr_time
        + "\n" + " num: " + num;
	}

}
