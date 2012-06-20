package three.musketeers.namespace;

import java.util.ArrayList;
import java.util.Random;
import android.graphics.Point;
import java.lang.Math;
public class MCTS  {
	public double najvec = -50000000;
	public void SimulirajRandom(vozlisce vs, int igr)
	{
		if(igr == 2)
			if(vs.getNasledniki().size()>0)
			{
				Random generator = new Random();
				int randomIndex = generator.nextInt(vs.getNasledniki().size());
				vs.getPolozaj()[vs.getNasledniki().get(randomIndex).getX1()][vs.getNasledniki().get(randomIndex).getY1()] = 0;
				vs.getPolozaj()[vs.getNasledniki().get(randomIndex).getX2()][vs.getNasledniki().get(randomIndex).getY2()] = 1;		
				
			}
		else
			{
				Random generator = new Random();
				int randomIndex = generator.nextInt(vs.getNasledniki().size());
				vs.getPolozaj()[vs.getNasledniki().get(randomIndex).getX1()][vs.getNasledniki().get(randomIndex).getY1()] = 0;
				vs.getPolozaj()[vs.getNasledniki().get(randomIndex).getX2()][vs.getNasledniki().get(randomIndex).getY2()] = 2;					
			}
			
	}
	
	
	public vozlisce Preveri(vozlisce st, int igralec)
	{
		if(st.getNasledniki().size()==0)
		{
			if(igralec == 2)
			{
				return Simulacija(st, igralec);
			}
			else	
			{						
				return Simulacija(st, igralec);
			}
		}
		
		else
		{
			Random gen = new Random();
			int randomIndex = gen.nextInt(st.getNasledniki().size());
			
			if(igralec == 1)
			{	
				if(st.getOcena() > 50)
				{
					int indeks = 0;
					for(int i=0; i<st.getNasledniki().size(); i++)
					{
						double si = st.getNasledniki().get(i).getOcena(); 			//število pozitivnih simulacij naslednik
						double ni = st.getNasledniki().get(i).getSt_obiskov();		//število obiskov naslednika
						
						double C = 1.5;
						double np = st.getSt_obiskov(); 	//število obiskov oèeta
						
						double vrednost = (si + ni) + C *(Math.log(np)/ni); 
						if(vrednost > najvec)
						{
							najvec = vrednost;
							indeks = i;
						}
						
					}
					Preveri(st.getNasledniki().get(indeks), 2);
					return st.getNasledniki().get(indeks);
				}
				else
				{
					//st.getNasledniki().get(randomIndex) = enacba
					Preveri(st.getNasledniki().get(randomIndex), 2);
					return st.getNasledniki().get(randomIndex);
				}
			}
			
			if(st.getOcena() > 50)
			{
				int indeks = 0;
				for(int i=0; i<st.getNasledniki().size(); i++)
				{
					double si = st.getNasledniki().get(i).getOcena(); 			//število pozitivnih simulacij naslednik
					double ni = st.getNasledniki().get(i).getSt_obiskov();		//število obiskov naslednika
					
					double C = 1.5;
					double np = st.getSt_obiskov(); 	//število obiskov oèeta
					
					double vrednost = (si + ni) + C *(Math.log(np)/ni); 
					if(vrednost > najvec)
					{
						najvec = vrednost;
						indeks = i;
					}
					

				}
				Preveri(st.getNasledniki().get(indeks), 1);
				return st.getNasledniki().get(indeks);
				
			}
			else
			{
				Preveri(st.getNasledniki().get(randomIndex), 1);
				return st.getNasledniki().get(randomIndex);
			}
		}		
	}
	
	
	
	public vozlisce Simulacija(vozlisce st, int igralec)
	{
				Random generator = new Random();
				
				vozlisce tmp = null;
				
				if(igralec == 2)
					{
						tmp = this.Simuliraj(st/*.getNasledniki().get(randomIndex)*/, false); //simuliranje igre, vrne zadnje vozlišèe					
						if(tmp.preveriZmago(tmp.getMusketerji())==2)
						{
							nastaviOcenoOcetu(tmp, 1, 1);
						}
						
						else
						{
							nastaviOcenoOcetu(tmp, -1, 1);
						}
					}
					
				else
				{
					 tmp = this.Simuliraj(st, true);				
					 if(tmp.preveriZmago(tmp.getMusketerji())==1)
					 {
						 nastaviOcenoOcetu(tmp, 1, 1);
					 }
				
					 else
					 {
						 nastaviOcenoOcetu(tmp, -1, 1);
					 }
				}
		return st;	
	}
	
	public vozlisce Simuliraj(vozlisce voz, boolean bebe) //izgradi drevo dokler ni veè na voljo potez, vrne ZADNJI list
	{	
		Random generator = new Random();
		vozlisce zac = new vozlisce(voz.getPolozaj(), bebe, voz.getMusketerji());
		
		if(zac.getNasledniki().size()==0)
		{				
			return voz;			
		}
			
		int randomIndex = generator.nextInt(zac.getNasledniki().size());	
		voz.setNasledniki(zac.getNasledniki()); //dodajanje naslednika v root
		
		for(vozlisce el : voz.getNasledniki())
		{
			el.setOce(voz);
		}

		return Simuliraj(zac.getNasledniki().get(randomIndex), !bebe);
	}
	
	
	public void nastaviOcenoOcetu(vozlisce voz,int oc,int ob)
	{	
		if(voz.getOce()==null)
		{
			return;
		}
		
		voz.setOcena(voz.getOcena()+oc);
		voz.setSt_obiskov(voz.getSt_obiskov()+ob);
		if(oc==1)
		{
			oc=-1;
		}
		else
		{
			oc=1;
		}
		nastaviOcenoOcetu(voz.getOce(), oc, ob);
	}
	
	
	
	public void Simuliraj2(vozlisce voz,int igr)
	{
		SimulirajRandom(voz,igr);
		if(voz.getNasledniki().size()==0)
		{
			return;
		}
		if(igr == 2)
			Simuliraj2(new vozlisce(voz.getPolozaj(),false,voz.getMusketerji()),1);
		else
			Simuliraj2(new vozlisce(voz.getPolozaj(),true,voz.getMusketerji()),2);
		
	}

		
	}		
