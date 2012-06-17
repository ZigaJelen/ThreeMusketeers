package three.musketeers.namespace;

import android.graphics.Point;

public class AlfaBeta {
	
	public final static int[][] NIL = null;
	public final static int INF = 999999;
//	private PotezaClass pot = new PotezaClass();
	private final Heuristic h = new Heuristic();
	public PotezaClass AlphaBeta(vozlisce P, boolean ig, int globina, int alpha, int beta) {
		PotezaClass pot = new PotezaClass();

		if(P.getNasledniki().size() == 0 || globina == 0)
		{
			pot.Ocena = h.getValue(P.getMusketerji().toArray(new Point[P.getMusketerji().size()]));
			pot.Poteza = null;
			return pot;
		}

		if(ig) // true = MAX
			pot.Ocena = -INF;
		else
			pot.Ocena = INF;
		pot.Poteza = null;
		for(vozlisce el : P.getNasledniki())
		{
			vozlisce voztmp = new vozlisce(el.getPolozaj(), ig, el.getMusketerji());
			PotezaClass temp = AlphaBeta(voztmp, !ig, globina-1, alpha, beta);
			if(ig && temp.Ocena > pot.Ocena)
			{
				pot.Ocena = temp.Ocena;
				pot.Poteza = el;
				if(pot.Ocena > alpha)
					alpha = pot.Ocena;
			}
			else if(!ig && temp.Ocena < pot.Ocena)
			{
				pot.Ocena = temp.Ocena;
				pot.Poteza = el;
				if(pot.Ocena < beta)
					beta = pot.Ocena;
			}
			if(alpha >= beta)
				return pot;
		}
		return pot;

	}

}
