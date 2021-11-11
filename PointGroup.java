import java.util.*;

public class test {
	     public static Integer dist(Integer a, Integer b, Integer c, Integer d)
	    { 
	        return (a-c)*(a-c)+(b-d)*(b-d);
	    }  
	     public static Boolean valid(List<Integer> firstPoints, List<Integer> secondPoints, int i1, int j1, int k1, int i2, int j2, int k2)
	    {
	        int dx1 = firstPoints.get(2*i1) - firstPoints.get(2*j1);
	        int dx2 = firstPoints.get(2*i1) - firstPoints.get(2*k1);
	        int dy1 = firstPoints.get(2*i1+1) - firstPoints.get(2*j1+1);
	        int dy2 = firstPoints.get(2*i1+1) - firstPoints.get(2*k1+1);
	        int det1 = dx1*dy2 - dx2*dy1;
	        dx1 = secondPoints.get(2*i2) - secondPoints.get(2*j2);
	        dx2 = secondPoints.get(2*i2) -  secondPoints.get(2*k2);
	        dy1 = secondPoints.get(2*i2+1) - secondPoints.get(2*j2+1);
	        dy2 = secondPoints.get(2*i2+1) - secondPoints.get(2*k2+1);
	        int det2 = dx1*dy2 - dx2*dy1;
	        return (det1*det2 >= 0);
	                        
	    }
	    public static Integer findGroups(List<Integer> firstPoints, List<Integer> secondPoints) {
	   	ArrayList<Integer> res = new ArrayList<Integer>(); 
	     	ArrayList<ArrayList<Integer> > tab = new ArrayList<ArrayList<Integer> >();
	   	ArrayList<ArrayList<Integer> > tab2 = new ArrayList<ArrayList<Integer> >();
		   
	   	for(int i= 0 ;i<10500;i++)
	   	    {
	            ArrayList<Integer> tmp = new ArrayList<Integer>();
		    ArrayList<Integer> tmp2 = new ArrayList<Integer>();
	   	    tab.add(tmp);
	   	    tab2.add(tmp2);
	   	    }
	        int n = firstPoints.size();
	        for(int i=0; i<n/2;i++)
	       	   {
	           res.add(-1);
	           res.add(-1);
	           tab.get(secondPoints.get(2*i)*101+secondPoints.get(2*i+1)).add(i);
	           tab2.get(firstPoints.get(2*i)*101 +firstPoints.get(2*i+1)).add(i);
	           } 
	       
	        
	       //same points
	        int pris=0;
	        int T = 0;
	        Random rand = new Random();
	        int k = 1;
	        int t = 0;
	        int cnt = 0; 
	      
	    	   for(int i=0; i<n/2;i++)
		       {
		       int xi = firstPoints.get(2*i);
		       int yi = firstPoints.get(2*i+1); 
		       for(int j=0; j<n/2;j++)
		       	  {
		          int xj = secondPoints.get(2*j);
		          int yj = secondPoints.get(2*j+1);
		          if(xi == xj && yi == yj)
		          {
		            	if(res.get(i) == -1 && res.get(n/2 + j) == -1)
		                {
		                    res.set(i, k);
		               		res.set(n/2 + j, k);
		                    cnt++;
		                }
		               
		               
		          }
		          }
		       }
		if(cnt <5)
		{	
		    k=1;
		    for(int i=0; i<n;i++)
		    {
		     res.set(i,-1);
		    }
		}else
		   k++;
		int repeat = 1;
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for(int i=0; i<n/2;i++)
		     lst.add(i);
		while(repeat<3) 
		   {
		   Collections.shuffle(lst);
		   int mx = 0;
		   int indj = -1;
		   int indi = -1;
		   ArrayList<Integer> hold = new ArrayList<Integer>();
		   int turn = 0;
		   for(int z = 0; z < n/2; z +=1)
		       {
			  int i = 2*lst.get(z);
			  if(res.get(i/2) != -1)
				continue;
			  if(turn==0)
			  {
			  //System.out.println("  "+indi);
			  mx = 0;
			  }
			   int x1 = firstPoints.get(i);
			   int y1 = firstPoints.get(i+1);
			   for(int rt = 0; rt <4; rt++)
			     {
			     for(int j = 0; j<n ; j+=2)
			       {
			       if(res.get(n/2 + j/2) != -1)
				   continue;
			       int cur = 0;
			       int x2 = secondPoints.get(j);
			       int y2 = secondPoints.get(j+1);
			       int dx = x2 - x1;
			       int dy = y2 - y1;
			       ArrayList<Integer> temp = new ArrayList<Integer>();
			       ArrayList<Integer> vis = new ArrayList<Integer>();
			       ArrayList<Integer> ist = new ArrayList<Integer>();

				 for(int f= 0 ;f<1002;f++)
				{
				    vis.add(-1);
				    // ist.add(0);
				}
			       ArrayList<Integer> co = new ArrayList<Integer>();
			       ArrayList<Integer> si = new ArrayList<Integer>();
			       co.add(1);
			       co.add(0);
			       co.add(-1); 
			       co.add(0);
			       si.add(0);
			       si.add(1);
			       si.add(0);
			       si.add(-1);

				   for(int l = 0; l<n ;l+=2)
			       {
				   if(l!=i && res.get(l/2) == -1)
				   {
				   int nx = firstPoints.get(l) +dx;
				   int ny = firstPoints.get(l+1)+dy;
				   if(rt<4)
				   {
					   int tx = nx - x2;
					   int ty = ny - y2;
					   nx = (tx*co.get(rt) - ty*si.get(rt));
					   ny = (tx*si.get(rt) + ty*co.get(rt));
					   nx += x2;
					   ny += y2;

				   }

				   if(nx <101 && nx >0 && ny <101 && ny >0 &&tab.get(nx*101+ny).size()>0)
				   {
				       int it = 0;
				       //int p = tab.get(nx*101+ny).get(0);
				       int p = tab.get(nx*101+ny).get(rand.nextInt(tab.get(nx*101+ny).size()));
				       //System.out.println(tab.get(nx*101+ny).size());
				       //int p = tab.get(nx*101+ny).get(0);
				      /* if(it < tab.get(nx*101+ny).size())
					p = tab.get(nx*101+ny).get(it);
				      */ 
				     /*  int lim = tab.get(nx*101+ny).size();
				      if(tab.get(nx*101+ny).size() > 1)
					   lim = 1;
				       while(it+1 < lim && (res.get(p +n/2) != -1 && vis.get(p)!=-1))
				       { 
					   it++;
					   p = tab.get(nx*101+ny).get(it);
				       }*/
				       //ist.set(nx*101+ny,it+1);
					   if(res.get(p +n/2) == -1 && vis.get(p)==-1)
				       {
					    vis.set(p, 1);
					   temp.add(l/2);
					   temp.add(p);
					   cur++;
				       }

				   }
				   }

			       }
			       if(cur > mx)
			       {
				   mx = cur;
				   indj = j;
				   indi = i;
				   hold = temp;
				   //System.out.println("  "+indi);
			       }
			       }


			   }
			   if(mx>5 -2*repeat && turn ==3-repeat)
			   {
				  System.out.println("         "+indi+"  "+i+"  "+mx);
			       res.set(indi/2,k);
			       res.set(n/2 +indj/2, k);
			       for(int s = 0; s<hold.size();s+=2)
			       {
				   res.set(hold.get(s),k);
				   res.set(hold.get(s+1)+n/2,k);
			       }
			       k++;
			       pris+=hold.size()/2+1;
			   }
				   turn++;
				 if(turn ==3-repeat+1)
					 turn=0;







		       }
	   repeat++;
	}
	        
	    	  
		         
	         
	         System.out.println(" "+pris+" "+k);
	         int rem = k;
	        
	        for(int i=0; i<n/2;i++)
	       {
	           if(res.get(i) == -1) 
	           {res.set(i,k);
	            if(t==0)
	            	k++;
	            t = 1-t;
	           }
	       }
	        t=0;
	        k=rem;
	         for(int i=n/2; i<n;i++)
	       {
	           if(res.get(i) == -1) 
	           {res.set(i,k);
	             if(t==0)
	            	k++;
	            t = 1-t;
	           }
	       }
	        
	        
	        return k;
	    }
	    
	    
	public static void main(String[] args) {
		Random rand = new Random();
		double s = 0.0;
		for(int i = 0; i<10;i++)
		{
			System.out.println(i);
			ArrayList<Integer> first = new ArrayList<Integer>(); 
			ArrayList<Integer> second = new ArrayList<Integer>(); 
			for(int k = 0; k<2000;k++)
			{
				//System.out.println("	"+k);
				first.add(rand.nextInt(100)+1);
				second.add(rand.nextInt(100)+1);
			}
			int r = findGroups(first, second);
			s += (1000-r+1)/1000.0;
			
		}
		System.out.print(s/10);

	}

}
