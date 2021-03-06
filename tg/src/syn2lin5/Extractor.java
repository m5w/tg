package syn2lin5;

import rt.util.DB;
import syn2lin5.Decoder.Path;
import is2.data.IFV;
import is2.data.Instances;
import is2.data.Long2IntInterface;
import is2.data.MFO;


final public class Extractor {
	
	public static short s_rel,s_pos, s_word,s_type,s_dir,s_dist,s_feat,s_child;

	private static int s_question;

	final public MFO mf;
	final private Long2IntInterface li;

	final MFO.Data4 d0 = new MFO.Data4();
	final MFO.Data4 dl1 = new MFO.Data4();
	final MFO.Data4 dl2 = new MFO.Data4();
	final MFO.Data4 dr = new MFO.Data4();
	final MFO.Data4 drrw = new MFO.Data4();
	final MFO.Data4 drrww = new MFO.Data4();
	final MFO.Data4 drrrww = new MFO.Data4();
	
	final MFO.Data4 drw = new MFO.Data4();
//	final MFO.Data4 drw = new MFO.Data4();
	final MFO.Data4 dwwp = new MFO.Data4();
	final MFO.Data4 dwwwp = new MFO.Data4();
	final MFO.Data4 dw = new MFO.Data4();
	final MFO.Data4 dwp = new MFO.Data4();

	final MFO.Data4 dlf = new MFO.Data4();

	public Extractor(MFO m, Long2IntInterface l2i) { 
		mf=m;
		li =l2i;
	}

	public static void init(MFO mf) {
		s_rel = MFO.getFeatureBits(REL);
		s_pos = MFO.getFeatureBits(POS);
		s_word = MFO.getFeatureBits(WORD);
		s_type = MFO.getFeatureBits(TYPE);
		s_dir = MFO.getFeatureBits(DIR);
	
		s_dist = MFO.getFeatureBits(DIST);
		s_feat = MFO.getFeatureBits(Pipe.FEAT);
		
		s_question =mf.getValue(Pipe.WORD, "?");
	}

	public void init(){
		d0.a0 = s_type;d0.a1 = s_pos;d0.a2 = s_pos;d0.a3 = s_pos;d0.a4 = s_pos;d0.a5 = s_pos;d0.a6 = s_pos;d0.a7 = s_pos;
		dl1.a0 = s_type;dl1.a1 = s_rel; dl1.a2 = s_pos;dl1.a3 = s_pos; dl1.a4 = s_pos; dl1.a5 = s_pos; dl1.a6 = s_pos; dl1.a7 = s_pos;	
		dl2.a0 = s_type;dl2.a1 = s_rel;dl2.a2 = s_word;dl2.a3 = s_pos;dl2.a4 = s_pos;dl2.a5 = s_pos;dl2.a6 = s_pos;dl2.a7 = s_pos;
		dwp.a0 = s_type; 	dwp.a1 = s_rel; 	dwp.a2 = s_word; 	dwp.a3 = s_pos; 	dwp.a4 = s_pos; dwp.a5 = s_pos; dwp.a6 = s_pos; dwp.a7 = s_pos;
		dwwp.a0 = s_type; dwwp.a1 = s_rel; dwwp.a2 = s_word; dwwp.a3 = s_word; dwwp.a4 = s_pos; dwwp.a5 = s_pos;dwwp.a6 = s_pos;
		dwwwp.a0 = s_type; dwwwp.a1 = s_rel; dwwwp.a2 = s_word; dwwwp.a3 = s_word; dwwwp.a4 = s_word; dwwwp.a5 = s_pos;dwwwp.a6 = s_pos;
		dlf.a0 = s_type;dlf.a1 = s_rel; dlf.a2 = s_pos;dlf.a3 = s_pos; dlf.a4 = s_feat; dlf.a5 = s_feat; dlf.a6 = s_pos; dlf.a7 = s_pos;	
		dr.a0  = s_type;dr.a1 = s_rel; dr.a2 = s_rel;dr.a3 = s_rel; dr.a4 = s_rel; dr.a5 = s_rel; dr.a6 = s_rel; dr.a7 = s_rel;	
		drrw.a0  = s_type;drrw.a1 = s_rel; drrw.a2 = s_rel;drrw.a3 = s_word; drrw.a4 = s_word; drrw.a5 = s_word; drrw.a6 = s_pos; drrw.a7 = s_pos;	
		drrww.a0  = s_type;drrww.a1 = s_rel; drrww.a2 = s_rel;drrww.a3 = s_word; drrww.a4 = s_word; drrww.a5 = s_pos; drrww.a6 = s_pos; drrww.a7 = s_pos;	
		drw.a0  = s_type;drw.a1 = s_rel; drw.a2 = s_rel;drw.a3 = s_rel; drw.a4 = s_word; drw.a5 = s_pos; drw.a6 = s_pos; drw.a7 = s_pos;	
		drrrww.a0  = s_type;drrrww.a1 = s_rel; drrrww.a2 = s_rel;drrrww.a3 = s_word; drrrww.a4 = s_rel; drrrww.a5 = s_rel; drrrww.a6 =s_rel; drrrww.a7 = s_rel;	

	}

	public static final String REL = "REL";
	private static final String END = "END";
	private static final String STR = "STR";
	private static final String LA = "LA";
	private static final String RA = "RA";

	private static int ra;
	private static int la;
	private static int s_str;
	private static int s_end;
	private static int s_stwrd;

	protected static final String TYPE = "TYPE";
	private static final String CHAR = "C";
	private static final String DIR = "D";
	public static final String POS = "POS";
	protected static final String DIST = "DIST";
	private static final String MID = "MID";

	private static final String _0 = "0";
	private static final String _4 = "4";
	private static final String _3 = "3";
	private static final String _2 = "2";
	private static final String _1 = "1";
	private static final String _5 = "5";
	private static final String _10 = "10";

	private static  int di0, d4,d3,d2,d1,d5,d10;


	private static final String WORD = "WORD";

	private static final String STWRD = "STWRD";
	private static final String STPOS = "STPOS";

	




	private static int nofeat;

	

	/**
	 * Initialize the features.
	 * @param maxFeatures
	 */
	static public void initFeatures(MFO mf) {

		mf.register(POS, MID);
		s_str = mf.register(POS, STR);
		s_end = mf.register(POS, END);

		mf.register(TYPE, POS);

		s_stwrd=mf.register(WORD,STWRD);
		mf.register(POS,STPOS);

		la = mf.register(DIR, LA);
		ra = mf.register(DIR, RA);

	
		nofeat=mf.register(Pipe.FEAT, "NOFEAT");

		for(int k=0;k<58;k++) {
			mf.register(TYPE, "F"+k);
		}

	

		di0=mf.register(DIST, _0);
		d1=mf.register(DIST, _1);
		d2=mf.register(DIST, _2);
		d3=mf.register(DIST, _3);
		d4=mf.register(DIST, _4);
		d5=mf.register(DIST, _5);
		//		d5l=mf.register(DIST, _5l);
		d10=mf.register(DIST, _10);
	}
	
	public static void switchOff(String features) {
		
		if (features==null) return;

		
	}


	
	/**
	 * @param gs
	 * @param w1
	 * @param w2
	 * @param label
	 * @param inW2 
	 * @param inW1 
	 * @param f
	 * @param paths 
	 * @param distPath 
	 * @param dist2 
	 */
	public void extractFeaturesX(Instances is, int i, int w1, int w2, int label,  IFV f, Path[][] paths) {
		
		
		short[] pos = is.gpos[i];
		
	
		int f1 = is.glemmas[i][w1], f2= is.glemmas[i][w2];
		
		int hP = is.heads[i][w1]==-1?s_str:pos[is.heads[i][w1]];
		
		int c1 = is.feats[i][w1]!=null&&is.feats[i][w1].length>0?is.feats[i][w1][0]:s_end;
		int c2 = is.feats[i][w2]!=null&&is.feats[i][w2].length>0?is.feats[i][w2][0]:s_end;

		int hF = is.heads[i][w1]==-1?s_stwrd:is.glemmas[i][is.heads[i][w1]];

		int headInRel = is.heads[i][w1]==-1?-1:is.labels[i][is.heads[i][w1]];

		
		int p1 =pos[w1],p2 = pos[w2];

		int childs1=0;
		int childs2=0;
		for(int k=0;k<pos.length;k++) {
			if(is.heads[i][k]==w1) childs1++;
			if(is.heads[i][k]==w2) childs2++;
		}
		
		long l;		

		int h1 = is.heads[i][w1]==w2?1:is.heads[i][w2]==w1?2:3;
		
		

		int l1=is.labels[i][w1], l2=is.labels[i][w2];
		
		int pred= (is.predicat[i][w1]+1)+(is.predicat[i][w2]+1)*4+h1*8;
				
		int n=1;


		drw.v0 = n++; drw.v1 = label; drw.v2 = l1; drw.v3 = l2; drw.v4=pred;	l= mf.calc5(drw); f.add(li.l2i(l)); 		
		drrww.v0 = n++; drrww.v1 = label; drrww.v2 = l2; drrww.v3 = f1;  drrww.v4=pred;  l= mf.calc5(drrww); f.add(li.l2i(l)); 	
		drrww.v0 = n++; drrww.v1 = label; drrww.v2 = l2; drrww.v3 = f2;  drrww.v4=pred;	l= mf.calc5(drrww); f.add(li.l2i(l)); 		
		drrww.v0 = n++; drrww.v1 = label; drrww.v2 = l1; drrww.v3 = f1;  drrww.v4=pred;	l= mf.calc5(drrww); f.add(li.l2i(l)); 		
		drrww.v0 = n++; drrww.v1 = label; drrww.v2 = l1; drrww.v3 = f2;  drrww.v4=pred;	l= mf.calc5(drrww); f.add(li.l2i(l)); 		


		
		dwwp.v0 = n++; 	dwwp.v1 = label; dwwp.v2 = f1; dwwp.v3 = f2; dwwp.v4 = pred; l= mf.calc5(dwwp); f.add(li.l2i(l)); 		
		
		dwwp.v0 = n++; 	dwwp.v1 = label; dwwp.v2 = p1; dwwp.v3 = p2;dwwp.v4 = c1; dwwp.v5 = pred; l= mf.calc6(dwwp); f.add(li.l2i(l)); 	
		dwwp.v0 = n++; 	dwwp.v1 = label; dwwp.v2 = p1; dwwp.v3 = p2;dwwp.v4 = c2; dwwp.v5 = pred; l= mf.calc6(dwwp); f.add(li.l2i(l)); 	


		dl1.v0= n++; dl1.v1=label; dl1.v2=p1; dl1.v3=p2; dl1.v4=hP; dl1.v5=pred;  l= mf.calc6(dl1); f.add(li.l2i(l));  	
     
		dr.v0= n++; dr.v1=l1; dr.v2=l2; dr.v3=hP;  dr.v4=pred;   l= mf.calc5(dr);  f.add(li.l2i(l)); 
	
		drrw.v0= n++; drrw.v1=l1; drrw.v2=l2; drrw.v3=f1; drrw.v4=p2; drrw.v5=pred;   l= mf.calc6(drrw); f.add(li.l2i(l));     
		drrw.v0= n++; drrw.v1=l1; drrw.v2=l2; drrw.v3=f2; drrw.v4=p1; drrw.v5=pred;   l= mf.calc6(drrw); f.add(li.l2i(l));
    
	    
		//headInRel
        drw.v0= n++; drw.v1=l1; drw.v2=l2; drw.v3=headInRel; drw.v4=f1;drw.v5=p2; drw.v6=pred; l= mf.calc7(drw); f.add(li.l2i(l));
    	
        drw.v0= n++;                                         drw.v4=f2;drw.v5=p1; drw.v6=pred;  l= mf.calc7(drw); f.add(li.l2i(l)); 		
        drw.v0= n++;                                         drw.v4=hF;drw.v5=p1; drw.v6=p2; drw.v7=pred;      l= mf.calc8(drw); 		
        drw.v0= n++;                                         drw.v4=hP;drw.v5=p1; drw.v6=p2; drw.v7=pred; l= mf.calc8(drw); f.add(li.l2i(l));		
 
        
        drw.v0= n++; drw.v1=l1; drw.v2=l2;  drw.v3=headInRel; drw.v4=hP;drw.v5=p1; drw.v6=childs2;drw.v7=pred;  l= mf.calc8(drw); f.add(li.l2i(l));  		


        
        int[] children1 =  morph2.Extractor.getChildren2(w1, is, i);
        
        if (children1.length==1) {
          	int fc1 = is.glemmas[i][children1[0]];
        	dwwp.v0 = n; 	dwwp.v1 = l2; dwwp.v2 = f1; dwwp.v3 = fc1;dwwp.v4 = p2;  dwwp.v5 = pred; l= mf.calc6(dwwp); f.add(li.l2i(l)); 	
        	
        }
        n++;
        int[] children2 =  morph2.Extractor.getChildren2(w2, is, i);
        
        if (children2.length==1) {
          	int fc2 = is.glemmas[i][children2[0]];

         	dwwp.v0 = n; 	dwwp.v1 = l1; dwwp.v2 = f2; dwwp.v3 = fc2;dwwp.v4 = p1;  dwwp.v5 = pred; l= mf.calc6(dwwp); f.add(li.l2i(l)); 	
        	
        }
        n++;
        
        if (paths[w1]!=null&&paths[w1][0].path.length>1) {
        	int pce = is.gpos[i][paths[w1][0].path[paths[w1][0].path.length-1]];
  
          	int fc1 = is.glemmas[i][paths[w1][0].path[0]];
          	
        	int fce = is.glemmas[i][paths[w1][0].path[paths[w1][0].path.length-1]];
            
        	dwwp.v0 = n; 	dwwp.v1 = label; dwwp.v2 = f1; dwwp.v3 = fc1;dwwp.v4 = p2;  dwwp.v5 = pce; dwwp.v6 = pred; l= mf.calc7(dwwp); f.add(li.l2i(l)); 	
        	dwwp.v0 = n+1; 	dwwp.v1 = label; dwwp.v2 = f1; dwwp.v3 = fce;dwwp.v4 = p2;  dwwp.v5 = pce; dwwp.v6 = pred; l= mf.calc7	(dwwp); f.add(li.l2i(l)); 	
   
        }
      
        n+=2;
        if (paths[w2]!=null&&paths[w2][0].path.length>1) {
        //	int pc1 = is.gpos[i][paths[w2][0].path[0]];
        	int pce = is.gpos[i][paths[w2][0].path[paths[w2][0].path.length-1]];
        	
          	int fc1 = is.glemmas[i][paths[w2][0].path[0]];
        	int fce = is.gpos[i][paths[w2][0].path[paths[w2][0].path.length-1]];
 
        	
          	dwwp.v0 = n; 	dwwp.v1 = label; dwwp.v2 = fc1; dwwp.v3 = f2;dwwp.v4 = p1;  dwwp.v5 = pce; dwwp.v6 = pred; l= mf.calc7(dwwp); f.add(li.l2i(l)); 	
          	dwwp.v0 = n+1; 	dwwp.v1 = label; dwwp.v2 = fce; dwwp.v3 = f2;dwwp.v4 = p1;  dwwp.v5 = 1; dwwp.v6 = pred; l= mf.calc7(dwwp); f.add(li.l2i(l)); 	

        	
        }
        n+=3;

                
	}

	

	/**
	 * @param is
	 * @param inst
	 * @param w1
	 * @param w2
	 * @param i2 
	 * @param f
	 */
	public void extractTrigrams(Instances is, int i, int w1, int w2, int w3, int i2, IFV f) {
	
		int p1 = is.gpos[i][w1],p2 = is.gpos[i][w2],p3 = is.gpos[i][w3];	
		int f1 = is.glemmas[i][w1] ,f2 = is.glemmas[i][w2],f3 = is.glemmas[i][w3]; //w2
		int l1 = is.labels[i][w1],l2= is.labels[i][w2], l3=is.labels[i][w3];
	
		int h = is.heads[i][w1];
		int hP = h==-1?-1:is.gpos[i][h];
		int hL = h==-1?-1:is.glemmas[i][h];
		
		int h1=0;
		if(is.heads[i][w1]==w2||is.heads[i][w3]==w2) { h1=2;
		} else if(is.heads[i][w2]==w1||is.heads[i][w3]==w1) { h1=1;
		} else if(is.heads[i][w1]==w3||is.heads[i][w2]==w3) { h1=3;} 
	
		
		int n=35;
		
		int pred= (is.predicat[i][w1]+1)+(is.predicat[i][w2]+1)*4+(is.predicat[i][w3]+1)*8;
		
		long l; 


		
		dl1.v0 = n++; dl1.v1 = h1; dl1.v2 = p1; dl1.v3 = p2; dl1.v4 = p3 ; dl1.v5 = pred ; l= mf.calc6(dl1); f.add(li.l2i(l)); f.add(li.l2i(dl1.calcs(s_pos,i2,l)));		

		dl1.v0 = n++; dl1.v1 = h1; dl1.v2 = p1; dl1.v3 = p2; dl1.v4 = pred; l= mf.calc5(dl1); f.add(li.l2i(l)); f.add(li.l2i(dl1.calcs(s_pos,i2,l)));		
		dl1.v0 = n++; dl1.v1 = h1; dl1.v2 = p1; dl1.v3 = p3; dl1.v4 = pred; l= mf.calc5(dl1); f.add(li.l2i(l)); f.add(li.l2i(dl1.calcs(s_pos,i2,l)));		
		dl1.v0 = n++; dl1.v1 = h1; dl1.v2 = p2; dl1.v3 = p3; dl1.v4 = pred; l= mf.calc5(dl1); f.add(li.l2i(l)); f.add(li.l2i(dl1.calcs(s_pos,i2,l)));		

		dwwwp.v0 = n++; dwwwp.v1 = h1; dwwwp.v2 = f1; dwwwp.v3 = f3;  l= mf.calc4(dwwwp); f.add(li.l2i(l)); 	f.add(li.l2i(dwwwp.calcs(s_pos,i2,l)));		
		dwwwp.v0 = n++; dwwwp.v1 = h1; dwwwp.v2 = f2; dwwwp.v3 = f3;  l= mf.calc4(dwwwp); f.add(li.l2i(l)); 	f.add(li.l2i(dwwwp.calcs(s_pos,i2,l)));		
		dwwwp.v0 = n++; dwwwp.v1 = h1; dwwwp.v2 = f1; dwwwp.v3 = f2;  l= mf.calc4(dwwwp); f.add(li.l2i(l)); 	f.add(li.l2i(dwwwp.calcs(s_pos,i2,l)));		


		drw.v0 = n++;  drw.v1 = l1; drw.v2 = l2; drw.v3 = l3;  drw.v4=hP; drw.v5= p1; drw.v6= pred; l= mf.calc7(drw); f.add(li.l2i(l)); f.add(li.l2i(drw.calcs(s_pos,i2,l)));			
		drw.v0 = n++;                                          drw.v4=hL; drw.v5= p1; drw.v6= pred; l= mf.calc7(drw); f.add(li.l2i(l)); f.add(li.l2i(drw.calcs(s_pos,i2,l)));			
		drw.v0 = n++;                                          drw.v4=f2; drw.v5= p1; drw.v6= pred; l= mf.calc7(drw); f.add(li.l2i(l)); f.add(li.l2i(drw.calcs(s_pos,i2,l)));			
		drw.v0 = n++;                                          drw.v4=f1; drw.v5= p2; drw.v6= pred; l= mf.calc7(drw); f.add(li.l2i(l)); f.add(li.l2i(drw.calcs(s_pos,i2,l)));			

		drw.v0 = n++;                                          drw.v4=f2; drw.v5= p1; drw.v6= pred; l= mf.calc7(drw); f.add(li.l2i(l)); f.add(li.l2i(drw.calcs(s_pos,i2,l)));			
		
	}

	/**
	 * Extract the global features
	 * @param is the sentences 
	 * @param i the id of a sentneces in the sentneces list
	 * @param p a path 
	 * @param f the features or score
	 */
	public void extractGlobal(Instances is, int i, int[] path, IFV f) {
		
				
		
		int head =-1;
		// get head
		for(int w1 : path) {
			for(int w2 : path) {
				if (is.heads[i][w1] ==w2) {
					head=w2;
					break;
				}
			}
			if (head !=-1) break;
		}
		
		
		// pos of the head 		
		int ph = is.gpos[i][head]==-1?s_str:is.gpos[i][head];
		
		
	
	
		// lemma of the head
		int fH = is.glemmas[i][head];
//		int relToHeadOfHead =  is.labels[i][head];
		
		// does the sentence have a question mark
		long l;
		int questionMark=0;
		for(int k=0;k<is.length(i);k++) {
			if (is.glemmas[i][k]==s_question) {
				questionMark=1;
				break;
			}
		}
		int n=45;

//		if (true)return ;

		
//		drrw.v0=n++; drrw.v1=is.labels[i][path[0]]; drrw.v2=is.labels[i][path[path.length-1]];drrw.v3=fH;
//		drrw.v4=is.glemmas[i][path[path.length-1]]; drrw.v5=is.glemmas[i][path[path.length-2]]; drrw.v6=questionMark; 
//		l= mf.calc7(drrw); f.add(li.l2i(l));  	

		
	
		dr.v0=n++; dr.v1=is.labels[i][path[0]]; dr.v2=is.labels[i][path[path.length-1]];dr.v3=ph;
		dr.v4=is.labels[i][path[path.length-1]]; dr.v5=is.labels[i][path[path.length-2]]; dr.v6=questionMark; 
		l= mf.calc7(dr); f.add(li.l2i(l));  	

		
		
//		drrw.v0=n++; drrw.v1=is.labels[i][path[0]]; drrw.v2=is.labels[i][path[path.length-1]];drrw.v3=ph;
//		drrw.v4=is.labels[i][path[path.length-1]]; drrw.v5=is.labels[i][path[path.length-2]]; drrw.v6=questionMark; 
//		l= mf.calc7(drrw); f.add(li.l2i(l));  	

		
		drrrww.v0=n++; drrrww.v1=is.labels[i][path[0]]; drrrww.v2=is.labels[i][path[path.length-1]];drrrww.v3=fH;
		drrrww.v4=is.labels[i][path[path.length-1]]; drrrww.v5=is.labels[i][path[path.length-2]]; drrrww.v6=questionMark; 
		l= mf.calc7(drrrww); f.add(li.l2i(l));  	

		drrw.v0=n++; drrw.v1=is.labels[i][path[0]]; drrw.v2=is.labels[i][path[path.length-1]];
		drrw.v3=is.glemmas[i][path[path.length-1]]; drrw.v4=ph; drrw.v5=is.labels[i][path[path.length-2]]; drrw.v6=questionMark; 
		l= mf.calc7(drrw); f.add(li.l2i(l));  	

		
		/*
		if (true) return ;

	
		if(path.length>=2) {
			drw.v0=n++; drw.v1=is.labels[i][path[0]]; drw.v2=is.labels[i][path[path.length-1]];drw.v3=is.labels[i][path[path.length-2]];
			drw.v4=ph; drw.v5=is.gpos[i][path[0]]; drw.v6=is.gpos[i][path[path.length-1]]; drw.v7=posHH;//is.gpos[i][path[path.length-2]]; 
	        l= mf.calc8(drw); f.add(li.l2i(l));  	
	        
	        
		}


		
		if(path.length>3) {
			drw.v0=n++; drw.v1=is.labels[i][path[0]]; drw.v2=is.labels[i][path[1]];drw.v3=is.labels[i][path[2]];
			drw.v4=ph; drw.v5=is.gpos[i][path[0]]; drw.v6=is.gpos[i][path[path.length-1]];  
	        l= mf.calc7(drw); f.add(li.l2i(l));  	f.add(li.l2i(drw.calcs(s_pos,questionMark,l)));

	        drw.v0=n++; drw.v1=is.labels[i][path[0]]; drw.v2=is.labels[i][path[1]];drw.v3=is.labels[i][path[2]];
			drw.v4=fH; drw.v5=is.gpos[i][path[0]]; drw.v6=is.gpos[i][path[path.length-1]]; drw.v7=posHH;  
	        l= mf.calc8(drw); f.add(li.l2i(l));  	f.add(li.l2i(drw.calcs(s_pos,questionMark,l)));
	           
	   
		}
		

		
		
		if(path.length>4) {
			int len = path.length;
			dl1.v0 =n++;dl1.v1 = relToHeadOfHead; dl1.v2 = nodesBefore;
			dl1.v3 = is.gpos[i][path[0]]; dl1.v4 = is.gpos[i][path[1]]; dl1.v5 = is.gpos[i][path[2]]; dl1.v6 = is.gpos[i][path[3]];
			dl1.v7 = is.gpos[i][path[len-1]]; 
			l= mf.calc8(dl1); f.add(li.l2i(l)); 

			dl1.v0 =n++;dl1.v1 = relToHeadOfHead; dl1.v2 = nodesBefore;
			dl1.v3 = is.gpos[i][path[0]]; dl1.v4 = is.gpos[i][path[len-4]];; dl1.v5 = is.gpos[i][path[len-3]]; dl1.v6 = is.gpos[i][path[len-2]];
			dl1.v7 =  is.gpos[i][path[len-1]]; 
			l= mf.calc8(dl1); f.add(li.l2i(l)); 
		}		
		
	*/
	

	
	}


}
